package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.MerchandiseCarousel;
import host.luke.knowbaby.entity.MerchantCarousel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseCarouselRepository extends JpaRepository<MerchandiseCarousel, Long> {

  List<MerchandiseCarousel> findByMerchandiseId(long merchandiseId);
}
