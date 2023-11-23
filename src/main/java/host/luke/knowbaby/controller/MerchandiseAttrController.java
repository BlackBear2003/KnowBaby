package host.luke.knowbaby.controller;

import host.luke.knowbaby.entity.MerchandiseAttr;
import host.luke.knowbaby.service.MerchandiseAttrService;
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
@RequestMapping("/merchandise/attr")
@Api(tags = "商品附加信息管理接口", description = "提供商品附加信息的创建、更新、删除、查询操作")
public class MerchandiseAttrController {

  @Autowired
  MerchandiseAttrService attrService;

  @GetMapping("/")
  @ApiOperation(value = "根据商品ID获取商品附加信息列表", notes = "根据商品ID获取商品附加信息列表")
  public ResponseEntity<List<MerchandiseAttr>> getByMerchandiseId(
      @RequestParam @ApiParam(value = "商品ID", required = true) long merchandiseId) {
    List<MerchandiseAttr> list = attrService.findByMerchandiseId(merchandiseId);
    return ResponseEntity.ok(list);
  }

  @PostMapping("/")
  @ApiOperation(value = "创建商品附加信息", notes = "创建商品附加信息")
  public ResponseEntity<MerchandiseAttr> create(
      @RequestBody @ApiParam(value = "商品附加信息", required = true) MerchandiseAttr attr) {
    MerchandiseAttr saved = attrService.save(attr);
    return ResponseEntity.ok(saved);
  }

  @PutMapping("/")
  @ApiOperation(value = "更新商品附加信息", notes = "更新商品附加信息")
  public ResponseEntity<MerchandiseAttr> updateById(
      @RequestBody @ApiParam(value = "商品属性信息", required = true) MerchandiseAttr attr) {
    if (attr.getId() == 0) {
      return ResponseEntity.badRequest().build();
    }

    MerchandiseAttr updated = attrService.save(attr);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/")
  @ApiOperation(value = "根据附加信息ID删除商品附加信息", notes = "根据附加信息ID删除商品附加信息")
  public ResponseEntity<Void> deleteById(
      @RequestParam @ApiParam(value = "附加信息ID", required = true) long id) {
    if (id == 0) {
      return ResponseEntity.badRequest().build();
    }
    attrService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
