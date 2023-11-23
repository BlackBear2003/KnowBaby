package host.luke.knowbaby.chatai;

import com.alibaba.fastjson.JSON;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.util.SseHelper;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ChatAIEventSourceListener extends EventSourceListener {

  private static final Logger log = LoggerFactory.getLogger(ChatAIEventSourceListener.class);

  final SseEmitter sseEmitter;

  String last = "";

  Consumer<String> onComplete = s -> {
  };

  public ChatAIEventSourceListener(SseEmitter sseEmitter) {
    this.sseEmitter = sseEmitter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onOpen(EventSource eventSource, Response response) {

  }
  /**
   * {@inheritDoc}
   */
  @Override
  public void onEvent(EventSource eventSource, String id, String type, String data) {
    log.info("回答中：{}", data);
    if (data.equals("[DONE]")) {
      log.info("回答完成：" + last);
      onComplete.accept(last);
      SseHelper.complete(sseEmitter);
      return;
    }

    ChatCompletionResponse completionResponse = JSON.parseObject(data,
        ChatCompletionResponse.class); // 读取Json
    Message delta = completionResponse.getChoices().get(0).getDelta();
    String text = delta.getContent();
    if (text != null) {
      last += text;

      try {
        sseEmitter.send(delta);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void onClosed(EventSource eventSource) {
    SseHelper.complete(sseEmitter);
  }

  @Override
  public void onFailure(EventSource eventSource, Throwable t, Response response) {
    if (Objects.isNull(response)) {
      return;
    }
    ResponseBody body = response.body();
    if (Objects.nonNull(body)) {
      try {
        log.error("OpenAI  sse连接异常data：{}，异常：{}", body.string(), t);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      log.error("OpenAI  sse连接异常data：{}，异常：{}", response, t);
    }
    eventSource.cancel();
    SseHelper.complete(sseEmitter);
  }

  public void setOnComplete(Consumer<String> onComplete) {
    this.onComplete = onComplete;
  }
}
