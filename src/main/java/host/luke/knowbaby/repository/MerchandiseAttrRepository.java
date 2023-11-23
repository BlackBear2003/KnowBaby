package host.luke.knowbaby.repository;

import host.luke.knowbaby.entity.MerchandiseAttr;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseAttrRepository extends JpaRepository<MerchandiseAttr, Long> {

  List<MerchandiseAttr> findByMerchandiseId(long merchandiseId);
}
