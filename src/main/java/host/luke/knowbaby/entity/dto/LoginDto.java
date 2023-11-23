package host.luke.knowbaby.entity.dto;

import host.luke.knowbaby.auth.UserInfo;

public class LoginDto {

  private String accessToken;
  private UserInfo info;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public UserInfo getInfo() {
    return info;
  }

  public void setInfo(UserInfo info) {
    this.info = info;
  }
}
