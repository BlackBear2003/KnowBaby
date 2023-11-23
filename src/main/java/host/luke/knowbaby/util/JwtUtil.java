package host.luke.knowbaby.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import host.luke.knowbaby.auth.UserInfo;
import host.luke.knowbaby.auth.UserInfoHolder;
import host.luke.knowbaby.constant.RequestContextConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

  public static String byUserInfo() {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Map<String, Object> payload = new HashMap<>();
    payload.put(RequestContextConstant.REQUEST_CONTEXT_USER_INFO, userInfo);
    payload.put(JWTPayload.JWT_ID, UUID.randomUUID());
    payload.put(JWTPayload.SUBJECT, "access token");

    return JWTUtil.createToken(payload, JWTSignerUtil.hs256("luke".getBytes()));
  }

}
