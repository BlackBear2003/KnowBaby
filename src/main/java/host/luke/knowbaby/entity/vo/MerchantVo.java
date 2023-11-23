package host.luke.knowbaby.entity.vo;

public class MerchantVo {
  private long id;

  private String merchantName;

  private String mainCategory;

  private String returnAndExchangeRules;

  private String introduction;

  private String logisticalArea;

  private Boolean isLogisticsSpecifiable;

  private String username;

  private String password;

  private String nickname;

  private String email;

  /**
   * diff to Merchant
   */
  private String coverBase64;

  private String initialMsg;

  private String botName;

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

  public String getCoverBase64() {
    return coverBase64;
  }

  public void setCoverBase64(String coverBase64) {
    this.coverBase64 = coverBase64;
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


}
