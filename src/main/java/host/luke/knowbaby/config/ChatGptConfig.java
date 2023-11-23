package host.luke.knowbaby.config;

import com.plexpt.chatgpt.ChatGPTStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatGptConfig {

  @Value("${chat-ai.api-host}")
  private String apiHost;
  @Value("${chat-ai.api-key}")
  private String apiKey;

  @Bean
  public ChatGPTStream chatGPTStream() {
    ChatGPTStream chatGPTStream = ChatGPTStream.builder()
        .timeout(50)
        .apiKey(apiKey)
        .apiHost(apiHost)
        .build()
        .init();

    return chatGPTStream;
  }

}
