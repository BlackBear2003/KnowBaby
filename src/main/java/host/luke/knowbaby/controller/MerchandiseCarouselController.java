package host.luke.knowbaby.controller;

import host.luke.knowbaby.entity.MerchandiseCarousel;
import host.luke.knowbaby.entity.vo.MerchandiseCarouselVo;
import host.luke.knowbaby.service.MerchandiseCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchandise/carousel")
@Api(tags = "商品轮播图管理接口", description = "提供商家轮播图的上传、删除、查询操作")
public class MerchandiseCarouselController {

  @Autowired
  MerchandiseCarouselService merchandiseCarouselService;

  @PostMapping("/")
  @ApiOperation(value = "上传商品轮播图", notes = "上传商品轮播图")
  public ResponseEntity<MerchandiseCarousel> uploadCarousel(
      @RequestBody @ApiParam(value = "轮播图信息", required = true) MerchandiseCarouselVo dto) {
    MerchandiseCarousel saved = merchandiseCarouselService.create(dto);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/")
  @ApiOperation(value = "根据轮播图ID删除商品轮播图", notes = "根据轮播图ID删除商品轮播图")
  public ResponseEntity<Void> deleteById(
      @RequestParam @ApiParam(value = "轮播图ID", required = true) long id) {
    merchandiseCarouselService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/list")
  @ApiOperation(value = "根据商品ID获取商品轮播图列表", notes = "根据商品ID获取商品轮播图列表")
  public ResponseEntity<List<MerchandiseCarousel>> listByMerchandiseId(
      @RequestParam @ApiParam(value = "商品ID", required = true) long merchandiseId) {
    List<MerchandiseCarousel> carouselList = merchandiseCarouselService.findByMerchandiseId(
        merchandiseId);
    return ResponseEntity.ok(carouselList);
  }
}
