package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.PresetQnA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentQnARepository extends JpaRepository<PresetQnA, Long> {

  List<PresetQnA> findByMerchantId(long merchantId);

}
