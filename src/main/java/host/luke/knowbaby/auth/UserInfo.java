package host.luke.knowbaby.auth;

public class UserInfo {

  private long userId;
  private String username;
  /**
   * status = 0 : admin
   * status = 1 : customer
   * status = 2 : merchant
   */
  private String status;

  public UserInfo(long userId, String username, String status) {
    this.userId = userId;
    this.username = username;
    this.status = status;
  }

  public UserInfo(long userId, String username, Status status) {
    this.userId = userId;
    this.username = username;
    this.status = status.name();
  }

  public UserInfo() {
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
