package host.luke.knowbaby.controller;

import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.service.MerchandiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/merchandise")
@Api(tags = "商品管理接口", description = "提供商品的增删改查操作")
public class MerchandiseController {

  @Autowired
  MerchandiseService merchandiseService;

  @GetMapping("/")
  @ApiOperation(value = "根据商品ID获取商品信息", notes = "根据商品ID获取商品信息")
  public ResponseEntity<Merchandise> getById(
      @RequestParam @ApiParam(value = "商品ID", required = true) long id) {
    Merchandise saved = merchandiseService.findById(id);
    return ResponseEntity.ok(saved);
  }

  @PostMapping("/")
  @ApiOperation(value = "创建商品", notes = "创建新的商品")
  public ResponseEntity<Merchandise> create(
      @RequestBody @ApiParam(value = "商品信息", required = true) Merchandise merchandise) {
    Merchandise saved = merchandiseService.save(merchandise);
    return ResponseEntity.ok(saved);
  }

  @PutMapping("/")
  @ApiOperation(value = "更新商品信息", notes = "更新商品信息")
  public ResponseEntity<Merchandise> updateById(
      @RequestBody @ApiParam(value = "商品信息", required = true) Merchandise merchandise) {
    if (merchandise.getId() == 0) {
      return ResponseEntity.badRequest().build();
    }

    Merchandise saved = merchandiseService.save(merchandise);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/")
  @ApiOperation(value = "根据商品ID删除商品", notes = "根据商品ID删除商品")
  public ResponseEntity<Void> deleteById(
      @RequestParam @ApiParam(value = "商品ID", required = true) long id) {
    if (id == 0) {
      return ResponseEntity.badRequest().build();
    }
    merchandiseService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/merchantId")
  @ApiOperation(value = "根据商家ID获取商品列表", notes = "根据商家ID获取商品列表")
  public ResponseEntity<List<Merchandise>> getByMerchantId(
      @RequestParam @ApiParam(value = "商家ID", required = true) long merchantId) {
    List<Merchandise> merchandiseList = merchandiseService.findByMerchantId(merchantId);
    return ResponseEntity.ok(merchandiseList);
  }
}
