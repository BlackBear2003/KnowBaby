package host.luke.knowbaby.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PresetQnA {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "MerchantId")
  private long merchantId;
  @Basic
  @Column(name = "Q")
  private String q;
  @Basic
  @Column(name = "A")
  private String a;

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

  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PresetQnA presetQnA = (PresetQnA) o;
    return id == presetQnA.id && merchantId == presetQnA.merchantId && Objects.equals(q,
        presetQnA.q) && Objects.equals(a, presetQnA.a);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchantId, q, a);
  }
}
