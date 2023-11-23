package host.luke.knowbaby.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Merchant {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "MerchantName")
  private String merchantName;
  @Basic
  @Column(name = "MainCategory")
  private String mainCategory;
  @Basic
  @Column(name = "ReturnAndExchangeRules")
  private String returnAndExchangeRules;
  @Basic
  @Column(name = "Introduction")
  private String introduction;
  @Basic
  @Column(name = "LogisticalArea")
  private String logisticalArea;
  @Basic
  @Column(name = "IsLogisticsSpecifiable")
  private Boolean isLogisticsSpecifiable;
  @Basic
  @Column(name = "Username")
  private String username;
  @Basic
  @Column(name = "Password")
  private String password;
  @Basic
  @Column(name = "Nickname")
  private String nickname;
  @Basic
  @Column(name = "Email")
  private String email;
  @Basic
  @Column(name = "CoverUrl")
  private String coverUrl;
  @Basic
  @Column(name = "InitialMsg")
  private String initialMsg;
  @Basic
  @Column(name = "BotName")
  private String botName;
  @Basic
  @Column(name = "ChatStyle")
  private String chatStyle;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  public String getMainCategory() {
    return mainCategory;
  }

  public void setMainCategory(String mainCategory) {
    this.mainCategory = mainCategory;
  }

  public String getReturnAndExchangeRules() {
    return returnAndExchangeRules;
  }

  public void setReturnAndExchangeRules(String returnAndExchangeRules) {
    this.returnAndExchangeRules = returnAndExchangeRules;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getLogisticalArea() {
    return logisticalArea;
  }

  public void setLogisticalArea(String logisticalArea) {
    this.logisticalArea = logisticalArea;
  }

  public Boolean getLogisticsSpecifiable() {
    return isLogisticsSpecifiable;
  }

  public void setLogisticsSpecifiable(Boolean logisticsSpecifiable) {
    isLogisticsSpecifiable = logisticsSpecifiable;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public String getInitialMsg() {
    return initialMsg;
  }

  public void setInitialMsg(String initialMsg) {
    this.initialMsg = initialMsg;
  }

  public String getBotName() {
    return botName;
  }

  public void setBotName(String botName) {
    this.botName = botName;
  }

  public String getChatStyle() {
    return chatStyle;
  }

  public void setChatStyle(String chatStyle) {
    this.chatStyle = chatStyle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Merchant merchant = (Merchant) o;
    return id == merchant.id && Objects.equals(merchantName, merchant.merchantName)
        && Objects.equals(mainCategory, merchant.mainCategory) && Objects.equals(
        returnAndExchangeRules, merchant.returnAndExchangeRules) && Objects.equals(
        introduction, merchant.introduction) && Objects.equals(logisticalArea,
        merchant.logisticalArea) && Objects.equals(isLogisticsSpecifiable,
        merchant.isLogisticsSpecifiable) && Objects.equals(username, merchant.username)
        && Objects.equals(password, merchant.password) && Objects.equals(nickname,
        merchant.nickname) && Objects.equals(email, merchant.email)
        && Objects.equals(initialMsg, merchant.initialMsg) && Objects.equals(
        botName, merchant.botName) && Objects.equals(chatStyle, merchant.chatStyle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchantName, mainCategory, returnAndExchangeRules, introduction,
        logisticalArea, isLogisticsSpecifiable, username, password, nickname, email, initialMsg,
        botName, chatStyle);
  }
}
