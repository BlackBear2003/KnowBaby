package host.luke.knowbaby.controller;

import host.luke.knowbaby.auth.Status;
import host.luke.knowbaby.auth.UserInfo;
import host.luke.knowbaby.auth.UserInfoHolder;
import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.dto.LoginDto;
import host.luke.knowbaby.entity.vo.MerchantVo;
import host.luke.knowbaby.service.MerchantService;
import host.luke.knowbaby.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
@Api(tags = "商家管理接口", description = "提供商家的登录、注册、查询、更新、删除操作")
public class MerchantController {

  @Autowired
  MerchantService merchantService;

  @PostMapping("/login")
  @ApiOperation(value = "商家登录", notes = "商家登录")
  public ResponseEntity<LoginDto> login(
      @RequestParam @ApiParam(value = "用户名", required = true) String username,
      @RequestParam @ApiParam(value = "密码", required = true) String password) {
    Merchant byUsername = merchantService.findByUsername(username);
    if (byUsername.getPassword().equals(password)) {
      UserInfo userInfo = new UserInfo(byUsername.getId(), byUsername.getUsername(),
          Status.Merchant);
      UserInfoHolder.setUserInfo(userInfo);
      LoginDto dto = new LoginDto();
      dto.setInfo(userInfo);
      dto.setAccessToken(JwtUtil.byUserInfo());
      return ResponseEntity.ok(dto);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @PostMapping("/register")
  @ApiOperation(value = "商家注册", notes = "商家注册")
  public ResponseEntity<Merchant> register(
      @RequestBody @ApiParam(value = "商家信息", required = true) MerchantVo vo) {
    Merchant saved = merchantService.save(vo);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("/")
  @ApiOperation(value = "根据商家ID获取商家信息", notes = "根据商家ID获取商家信息")
  public ResponseEntity<Merchant> getById(
      @RequestParam @ApiParam(value = "商家ID", required = true) long id) {
    Merchant saved = merchantService.findById(id);
    return ResponseEntity.ok(saved);
  }

  @PutMapping("/")
  @ApiOperation(value = "更新商家信息", notes = "更新商家信息")
  public ResponseEntity<Merchant> updateById(
      @RequestBody @ApiParam(value = "商家信息", required = true) MerchantVo vo) {
    if (vo.getId() == 0) {
      return ResponseEntity.badRequest().build();
    }
    Merchant saved = merchantService.save(vo);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/")
  @ApiOperation(value = "根据商家ID删除商家", notes = "根据商家ID删除商家")
  public ResponseEntity<Void> deleteById(
      @RequestParam @ApiParam(value = "商家ID", required = true) long id) {
    if (id == 0) {
      return ResponseEntity.badRequest().build();
    }
    merchantService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
