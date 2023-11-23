package host.luke.knowbaby.service;

import com.plexpt.chatgpt.entity.chat.Message;
import host.luke.knowbaby.entity.ChatMessage;
import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.MerchandiseAttr;
import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.PresetQnA;
import host.luke.knowbaby.repository.ChatMessageRepository;
import host.luke.knowbaby.util.MessageUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatMessageService {

  @Autowired
  ChatMessageRepository messageRepository;
  @Autowired
  MerchantService merchantService;
  @Autowired
  PresentQnAService qnAService;
  @Autowired
  MerchandiseService merchandiseService;
  @Autowired
  MerchandiseAttrService merchandiseAttrService;

  public List<ChatMessage> findBySessionIdExceptPrompts(long sessionId) {
    return messageRepository.findByChatSessionIdOrderByCreateTimeAsc(sessionId);
  }

  @Transactional
  public void clearSession(long sessionId) {
    messageRepository.removeAllByChatSessionId(sessionId);
  }

  public List<Message> getGptMessagesBySessionId(long sessionId) {
    List<ChatMessage> chatMessageList = messageRepository.findByChatSessionIdOrderByCreateTime(sessionId);
    List<Message> gptMessageList = chatMsgList2GptMsgList(chatMessageList);
    return gptMessageList;
  }

  public ChatMessage save(long sessionId, Message message) {
    ChatMessage chatMessage = gptMsg2ChatMsg(sessionId, message, new Date());
    ChatMessage saved = messageRepository.save(chatMessage);
    return saved;
  }

  /**
   * Dynamic getting prompts by searching merchant's newest info from persistence
   * By setting Role to 'System' and sort on the top of messages, simulating using a knowledge-base
   *
   * <p>These would never record in persistence</p>
   * @param merchantId merchant id
   * @return prompts of the merchant, normally the num would be 3
   */
  public List<Message> getPromptsByMerchantId(long merchantId) {
    // basic
    List<Message> prompts = new ArrayList<>();
    prompts.add(Message.ofSystem(MessageUtil.generateBasicPrompt()));

    // merchant info
    Merchant merchant = merchantService.findById(merchantId);
    List<PresetQnA> qnAList = qnAService.findByMerchantId(merchantId);
    prompts.add(Message.ofSystem(MessageUtil.generateMerchantInfoPrompt(merchant, qnAList)));

    // merchandise info
    List<Merchandise> merchandises = merchandiseService.findByMerchantId(merchantId);
    StringBuilder merchandiseInfo = new StringBuilder();
    for (Merchandise m: merchandises) {
      List<MerchandiseAttr> attrs = merchandiseAttrService.findByMerchandiseId(m.getId());
      merchandiseInfo.append(MessageUtil.generateMerchandiseInfo(m, attrs));
    }
    prompts.add(Message.ofSystem(merchandiseInfo.toString()));
    return prompts;
  }

  Message chatMsg2GptMsg(ChatMessage chatMessage) {
    Message message = Message.of(chatMessage.getContent());
    message.setRole(chatMessage.getRole());
    return message;
  }

  ChatMessage gptMsg2ChatMsg(long sessionId, Message message, Date date) {
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setCreateTime(date);
    chatMessage.setContent(message.getContent());
    chatMessage.setChatSessionId(sessionId);
    chatMessage.setRole(message.getRole());
    return chatMessage;
  }

  List<Message> chatMsgList2GptMsgList(List<ChatMessage> chatMessageList) {
    List<Message> messageList = new ArrayList<>();
    for (ChatMessage chatMessage: chatMessageList) {
      messageList.add(chatMsg2GptMsg(chatMessage));
    }
    return messageList;
  }

  List<ChatMessage> gptMsgList2ChatMsgList(long sessionId, List<Message> messageList, Date date) {
    List<ChatMessage> chatMessageList = new ArrayList<>();
    for (Message message: messageList) {
      chatMessageList.add(gptMsg2ChatMsg(sessionId, message, date));
    }
    return chatMessageList;
  }

}
