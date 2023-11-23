package host.luke.knowbaby.controller;

import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import host.luke.knowbaby.auth.Status;
import host.luke.knowbaby.auth.UserInfo;
import host.luke.knowbaby.auth.UserInfoHolder;
import host.luke.knowbaby.chatai.ChatAIEventSourceListener;
import host.luke.knowbaby.entity.ChatSession;
import host.luke.knowbaby.entity.vo.UserChatVo;
import host.luke.knowbaby.service.ChatMessageService;
import host.luke.knowbaby.service.ChatSessionService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/chat")
public class ChatController {

  @Autowired
  ChatMessageService chatMessageService;
  @Autowired
  ChatSessionService chatSessionService;
  @Autowired
  ChatGPTStream chatGPTStream;

  @GetMapping(path = "/")
  @CrossOrigin(originPatterns = "*")
  public SseEmitter chat(@RequestParam long userId, @RequestParam long merchantId, @RequestParam String query) {
//    UserInfo userInfo = UserInfoHolder.getUserInfo();
//    if (Objects.isNull(userInfo)) {
//      return null;
//    }
//    if (!userInfo.getStatus().equals(Status.Customer.name())) {
//      return null;
//    }
    ChatSession chatSession = chatSessionService.findByCustomerIdAndMerchantId(
        userId, merchantId);
    if (Objects.isNull(chatSession)) {
      return null;
    }
    // 创建一个SseEmitter实例，用于将实时事件推送到客户端
    SseEmitter sseEmitter = new SseEmitter(-1L);
    // 沟通Gpt和前端的中间件
    ChatAIEventSourceListener listener = new ChatAIEventSourceListener(sseEmitter);
    // 制作历史对话
    Message message = Message.of(query);
    //    先查询动态的商家信息，制作成prompts
    List<Message> messages = chatMessageService.getPromptsByMerchantId(merchantId);
    //System.out.println(JSON.toJSONString(messages));
    //    然后查询正常的对话
    messages.addAll(chatMessageService.getGptMessagesBySessionId(chatSession.getId()));
    //    加入用户提问
    messages.add(message);

    // call back function
    listener.setOnComplete(msg -> {
      chatMessageService.save(chatSession.getId(), message);
      chatMessageService.save(chatSession.getId(), Message.ofAssistant(msg));
    });
    chatGPTStream.streamChatCompletion(messages, listener);

    return sseEmitter;
  }

}
