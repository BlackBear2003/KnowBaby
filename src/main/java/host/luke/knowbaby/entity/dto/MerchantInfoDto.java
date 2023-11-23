package host.luke.knowbaby.entity.dto;

import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.MerchantCarousel;
import java.util.List;

public class MerchantInfoDto {

  private Merchant merchant;
  private List<MerchantCarousel> merchantCarouselList;

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public List<MerchantCarousel> getMerchantCarouselList() {
    return merchantCarouselList;
  }

  public void setMerchantCarouselList(
      List<MerchantCarousel> merchantCarouselList) {
    this.merchantCarouselList = merchantCarouselList;
  }
}
