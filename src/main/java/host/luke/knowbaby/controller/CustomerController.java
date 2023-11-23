package host.luke.knowbaby.controller;

import host.luke.knowbaby.auth.Status;
import host.luke.knowbaby.auth.UserInfo;
import host.luke.knowbaby.auth.UserInfoHolder;
import host.luke.knowbaby.entity.Customer;
import host.luke.knowbaby.entity.dto.LoginDto;
import host.luke.knowbaby.entity.vo.CustomerVo;
import host.luke.knowbaby.service.CustomerService;
import host.luke.knowbaby.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Api(tags = "用户管理接口", description = "提供用户登录和注册操作")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @PostMapping("/login")
  @ApiOperation(value = "用户登录", notes = "用户登录接口")
  public ResponseEntity<LoginDto> login(
      @RequestParam @ApiParam(value = "用户名", required = true) String username,
      @RequestParam @ApiParam(value = "密码", required = true) String password) {
    Customer byUsername = customerService.findByUsername(username);
    if (byUsername.getPassword().equals(password)) {
      UserInfo userInfo = new UserInfo(byUsername.getId(), byUsername.getUsername(),
          Status.Customer);
      UserInfoHolder.setUserInfo(userInfo);
      LoginDto dto = new LoginDto();
      dto.setInfo(userInfo);
      dto.setAccessToken(JwtUtil.byUserInfo());
      return ResponseEntity.ok(dto);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @PostMapping("/register")
  @ApiOperation(value = "用户注册", notes = "用户注册接口")
  public ResponseEntity<Customer> register(
      @RequestBody @ApiParam(value = "用户信息", required = true) CustomerVo vo) {
    Customer saved = customerService.save(vo);
    return ResponseEntity.ok(saved);
  }

  @PutMapping("/")
  @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
  public ResponseEntity<Customer> updateById(
      @RequestBody @ApiParam(value = "用户信息", required = true) CustomerVo vo) {
    if (vo.getId() == 0) {
      return ResponseEntity.badRequest().build();
    }
    Customer saved = customerService.save(vo);
    return ResponseEntity.ok(saved);
  }

}

