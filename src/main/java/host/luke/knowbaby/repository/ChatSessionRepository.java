package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.ChatSession;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {

  List<ChatSession> findByCustomerId(long customerId);

  List<ChatSession> findByMerchantId(long merchantId);

  ChatSession findByCustomerIdAndMerchantId(long customerId, long merchantId);

  boolean existsByCustomerIdAndMerchantId(long customerId, long merchantId);
}
