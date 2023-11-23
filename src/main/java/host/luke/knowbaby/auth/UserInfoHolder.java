package host.luke.knowbaby.auth;

import host.luke.knowbaby.constant.RequestContextConstant;
import java.util.Objects;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserInfoHolder {

  public static UserInfo getUserInfo() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes != null) {
      Object principal = requestAttributes.getAttribute(
          RequestContextConstant.REQUEST_CONTEXT_USER_INFO, RequestAttributes.SCOPE_REQUEST);
      if (principal != null) {
        return ((UserInfo) principal);
      }
    }
    return null;
  }

  public static void setUserInfo(UserInfo userInfo) {
    if (Objects.nonNull(RequestContextHolder.getRequestAttributes())) {
      RequestContextHolder.getRequestAttributes()
          .setAttribute(RequestContextConstant.REQUEST_CONTEXT_USER_INFO, userInfo,
              RequestAttributes.SCOPE_REQUEST);
    }
  }

}
