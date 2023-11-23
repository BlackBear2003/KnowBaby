package host.luke.knowbaby.service;

import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.repository.MerchandiseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseService {

  @Autowired
  MerchandiseRepository merchandiseRepository;

  public List<Merchandise> findByMerchantId(long merchantId) {
    return merchandiseRepository.findByMerchantId(merchantId);
  }

  public Merchandise save(Merchandise merchandise) {
    return merchandiseRepository.save(merchandise);
  }

  public Merchandise findById(long id) {
    return merchandiseRepository.findById(id).orElse(new Merchandise());
  }

  public void deleteById(long id) {
    merchandiseRepository.deleteById(id);
  }

}
