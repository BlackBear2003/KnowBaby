package host.luke.knowbaby.service;

import host.luke.knowbaby.entity.MerchantCarousel;
import host.luke.knowbaby.entity.vo.MerchantCarouselVo;
import host.luke.knowbaby.repository.MerchantCarouselRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantCarouselService {

  @Autowired
  MerchantCarouselRepository carouselRepository;
  @Autowired
  QiniuService qiniuService;

  public List<MerchantCarousel> findByMerchantId(long merchantId) {
    return carouselRepository.findByMerchantId(merchantId);
  }

  public MerchantCarousel create(MerchantCarouselVo dto) {
    MerchantCarousel carousel = new MerchantCarousel();
    carousel.setId(0);
    carousel.setContent(dto.getContent());
    carousel.setMerchantId(dto.getMerchantId());
    carousel.setImageUrl(qiniuService.uploadImg(dto.getBase64()));
    return carouselRepository.save(carousel);
  }

  public void delete(long carouseId) {
    carouselRepository.deleteById(carouseId);
  }
}
