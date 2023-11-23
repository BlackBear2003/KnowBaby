package host.luke.knowbaby.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Merchandise {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "MerchantId")
  private long merchantId;
  @Basic
  @Column(name = "MerchandiseName")
  private String merchandiseName;
  @Basic
  @Column(name = "Targeting")
  private String targeting;
  @Basic
  @Column(name = "Strategy")
  private String strategy;
  @Basic
  @Column(name = "Price")
  private String price;
  @Basic
  @Column(name = "Description")
  private String description;
  @Basic
  @Column(name = "Color")
  private String color;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(long merchantId) {
    this.merchantId = merchantId;
  }

  public String getMerchandiseName() {
    return merchandiseName;
  }

  public void setMerchandiseName(String merchandiseName) {
    this.merchandiseName = merchandiseName;
  }

  public String getTargeting() {
    return targeting;
  }

  public void setTargeting(String targeting) {
    this.targeting = targeting;
  }

  public String getStrategy() {
    return strategy;
  }

  public void setStrategy(String strategy) {
    this.strategy = strategy;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Merchandise that = (Merchandise) o;
    return id == that.id && merchantId == that.merchantId && Objects.equals(merchandiseName,
        that.merchandiseName) && Objects.equals(targeting, that.targeting)
        && Objects.equals(strategy, that.strategy) && Objects.equals(price,
        that.price) && Objects.equals(description, that.description)
        && Objects.equals(color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchantId, merchandiseName, targeting, strategy, price, description,
        color);
  }
}
