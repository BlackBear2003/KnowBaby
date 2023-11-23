package host.luke.knowbaby.auth;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import host.luke.knowbaby.constant.RequestContextConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInfoInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String accessToken = authorizationHeader.substring(7);
      JWT parseToken = JWTUtil.parseToken(accessToken);

      UserInfo userInfo = JSONUtil.parse(
              parseToken.getPayload(RequestContextConstant.REQUEST_CONTEXT_USER_INFO))
          .toBean(UserInfo.class);
      UserInfoHolder.setUserInfo(userInfo);

    }
    return true;
  }
}
