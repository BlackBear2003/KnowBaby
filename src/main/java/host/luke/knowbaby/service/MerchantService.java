package host.luke.knowbaby.service;

import cn.hutool.core.bean.BeanUtil;
import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.vo.MerchantVo;
import host.luke.knowbaby.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

  @Autowired
  MerchantRepository merchantRepository;
  @Autowired
  QiniuService qiniuService;

  public Merchant findByUsername(String username) {
    return merchantRepository.findByUsername(username);
  }

  public Merchant save(Merchant merchant) {
    return merchantRepository.save(merchant);
  }

  public Merchant save(MerchantVo vo) {
    Merchant merchant = new Merchant();
    BeanUtil.copyProperties(vo, merchant);
    merchant.setCoverUrl(qiniuService.uploadImg(vo.getCoverBase64()));
    return save(merchant);
  }

  public Merchant findById(long id) {
    return merchantRepository.findById(id).orElse(new Merchant());
  }

  public void deleteById(long id) {
    merchantRepository.deleteById(id);
  }

}
