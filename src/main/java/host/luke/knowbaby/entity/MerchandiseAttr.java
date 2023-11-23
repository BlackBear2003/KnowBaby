package host.luke.knowbaby.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MerchandiseAttr {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "MerchandiseId")
  private long merchandiseId;
  @Basic
  @Column(name = "AttrJson")
  private String attrJson;
  @Basic
  @Column(name = "AttrUrl")
  private String attrUrl;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getMerchandiseId() {
    return merchandiseId;
  }

  public void setMerchandiseId(long merchandiseId) {
    this.merchandiseId = merchandiseId;
  }

  public String getAttrJson() {
    return attrJson;
  }

  public void setAttrJson(String attrJson) {
    this.attrJson = attrJson;
  }

  public String getAttrUrl() {
    return attrUrl;
  }

  public void setAttrUrl(String attrUrl) {
    this.attrUrl = attrUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MerchandiseAttr that = (MerchandiseAttr) o;
    return id == that.id && merchandiseId == that.merchandiseId && Objects.equals(attrJson,
        that.attrJson) && Objects.equals(attrUrl, that.attrUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchandiseId, attrJson, attrUrl);
  }
}
