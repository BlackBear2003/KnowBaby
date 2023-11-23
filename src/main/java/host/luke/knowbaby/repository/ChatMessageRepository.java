package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.ChatMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

  List<ChatMessage> findByChatSessionIdOrderByCreateTimeAsc(long chatSessionId);

  void removeAllByChatSessionId(long chatSessionId);

  List<ChatMessage> findByChatSessionIdOrderByCreateTime(long chatSessionId);
}
