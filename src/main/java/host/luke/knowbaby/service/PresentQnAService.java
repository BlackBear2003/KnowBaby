package host.luke.knowbaby.service;

import host.luke.knowbaby.entity.PresetQnA;
import host.luke.knowbaby.repository.PresentQnARepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresentQnAService {

  @Autowired
  PresentQnARepository presentQnARepository;

  public List<PresetQnA> findByMerchantId(long merchantId) {
    return presentQnARepository.findByMerchantId(merchantId);
  }

  public PresetQnA create(PresetQnA presetQnA) {
    return presentQnARepository.save(presetQnA);
  }

  public void deleteById(long id) {
    presentQnARepository.deleteById(id);
  }
}
