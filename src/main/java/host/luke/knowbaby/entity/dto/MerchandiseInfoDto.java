package host.luke.knowbaby.entity.dto;

import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.MerchandiseCarousel;
import java.util.List;

public class MerchandiseInfoDto {

  private Merchandise merchandise;
  private List<MerchandiseCarousel> merchandiseCarouselList;

  public Merchandise getMerchandise() {
    return merchandise;
  }

  public void setMerchandise(Merchandise merchandise) {
    this.merchandise = merchandise;
  }

  public List<MerchandiseCarousel> getMerchandiseCarouselList() {
    return merchandiseCarouselList;
  }

  public void setMerchandiseCarouselList(
      List<MerchandiseCarousel> merchandiseCarouselList) {
    this.merchandiseCarouselList = merchandiseCarouselList;
  }
}
