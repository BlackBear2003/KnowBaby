package host.luke.knowbaby.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatSession {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "ChatId")
  private String chatId;
  @Basic
  @Column(name = "CustomerId")
  private long customerId;
  @Basic
  @Column(name = "MerchantId")
  private long merchantId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(long merchantId) {
    this.merchantId = merchantId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatSession that = (ChatSession) o;
    return id == that.id && customerId == that.customerId && merchantId == that.merchantId
        && Objects.equals(chatId, that.chatId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, chatId, customerId, merchantId);
  }
}
