package host.luke.knowbaby.service;

import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.MerchandiseAttr;
import host.luke.knowbaby.repository.MerchandiseAttrRepository;
import host.luke.knowbaby.repository.MerchandiseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseAttrService {

  @Autowired
  MerchandiseAttrRepository attrRepository;

  public List<MerchandiseAttr> findByMerchandiseId(long merchandiseId) {
    return attrRepository.findByMerchandiseId(merchandiseId);
  }

  public MerchandiseAttr save(MerchandiseAttr attr) {
    return attrRepository.save(attr);
  }

  public MerchandiseAttr findById(long id) {
    return attrRepository.findById(id).orElse(new MerchandiseAttr());
  }

  public void deleteById(long id) {
    attrRepository.deleteById(id);
  }


}
