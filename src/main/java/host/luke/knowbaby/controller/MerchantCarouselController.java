package host.luke.knowbaby.controller;

import host.luke.knowbaby.entity.MerchantCarousel;
import host.luke.knowbaby.entity.vo.MerchantCarouselVo;
import host.luke.knowbaby.service.MerchantCarouselService;
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
@RequestMapping("/merchant/carousel")
@Api(tags = "商家轮播图管理接口", description = "提供商家轮播图的上传、删除、查询操作")
public class MerchantCarouselController {

  @Autowired
  MerchantCarouselService merchantCarouselService;

  @PostMapping("/")
  @ApiOperation(value = "上传商家轮播图", notes = "上传商家轮播图")
  public ResponseEntity<MerchantCarousel> uploadCarousel(
      @RequestBody @ApiParam(value = "轮播图信息", required = true) MerchantCarouselVo dto) {
    MerchantCarousel saved = merchantCarouselService.create(dto);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/")
  @ApiOperation(value = "根据轮播图ID删除商家轮播图", notes = "根据轮播图ID删除商家轮播图")
  public ResponseEntity<Void> deleteById(
      @RequestParam @ApiParam(value = "轮播图ID", required = true) long id) {
    merchantCarouselService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/list")
  @ApiOperation(value = "根据商家ID获取商家轮播图列表", notes = "根据商家ID获取商家轮播图列表")
  public ResponseEntity<List<MerchantCarousel>> listByMerchantId(
      @RequestParam @ApiParam(value = "商家ID", required = true) long merchantId) {
    List<MerchantCarousel> carouselList = merchantCarouselService.findByMerchantId(merchantId);
    return ResponseEntity.ok(carouselList);
  }
}
