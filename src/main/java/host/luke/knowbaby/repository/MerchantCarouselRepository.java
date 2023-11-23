package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.MerchantCarousel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantCarouselRepository extends JpaRepository<MerchantCarousel, Long> {

  List<MerchantCarousel> findByMerchantId(long merchantId);
}
