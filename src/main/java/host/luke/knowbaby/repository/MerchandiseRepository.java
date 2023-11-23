package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.Merchandise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

  List<Merchandise> findByMerchantId(long merchantId);
}
