package host.luke.knowbaby.service;

import com.plexpt.chatgpt.ChatGPT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration()
public class ChatAITest {

  @Test
  public void simpleChatTest() {
    ChatGPT chatGPT = ChatGPT.builder()
        .apiKey("sk-eLtomRLQmMAStt04YROYXoZ9JKWB3FphhID9kVfkZHxwDUDx")
        .apiHost("https://api.chatanywhere.com.cn") //反向代理地址
        .build()
        .init();

    String res = chatGPT.chat("写一段七言绝句诗，题目是：火锅！");
    System.out.println(res);
  }

}
