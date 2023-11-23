package host.luke.knowbaby.service;

import host.luke.knowbaby.entity.MerchandiseCarousel;
import host.luke.knowbaby.entity.vo.MerchandiseCarouselVo;
import host.luke.knowbaby.repository.MerchandiseCarouselRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseCarouselService {

  @Autowired
  MerchandiseCarouselRepository merchandiseCarouselRepository;
  @Autowired
  QiniuService qiniuService;

  public List<MerchandiseCarousel> findByMerchandiseId(long merchandiseId) {
    return merchandiseCarouselRepository.findByMerchandiseId(merchandiseId);
  }

  public MerchandiseCarousel create(MerchandiseCarouselVo dto) {
    MerchandiseCarousel carousel = new MerchandiseCarousel();
    carousel.setId(0);
    carousel.setContent(dto.getContent());
    carousel.setMerchandiseId(dto.getMerchandiseId());
    carousel.setImageUrl(qiniuService.uploadImg(dto.getBase64()));
    return merchandiseCarouselRepository.save(carousel);
  }

  public void delete(long carouselId) {
    merchandiseCarouselRepository.deleteById(carouselId);
  }

}
