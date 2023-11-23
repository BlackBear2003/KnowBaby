package host.luke.knowbaby.controller;

import host.luke.knowbaby.entity.PresetQnA;
import host.luke.knowbaby.service.PresentQnAService;
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
@RequestMapping("/merchant/pre-qa")
@Api(tags = "商家预设问答管理接口", description = "提供商家预设问答的创建、删除、查询操作")
public class PresentQnAController {

  @Autowired
  PresentQnAService presentQnAService;

  @PostMapping("/")
  @ApiOperation(value = "创建商家预设问答", notes = "创建商家预设问答")
  public ResponseEntity<PresetQnA> create(
      @RequestBody @ApiParam(value = "预设问答信息", required = true) PresetQnA presetQnA) {
    PresetQnA saved = presentQnAService.create(presetQnA);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/")
  @ApiOperation(value = "根据问答ID删除商家预设问答", notes = "根据问答ID删除商家预设问答")
  public ResponseEntity<Void> deleteById(
      @RequestParam @ApiParam(value = "问答ID", required = true) long id) {
    presentQnAService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/list")
  @ApiOperation(value = "根据商家ID获取商家预设问答列表", notes = "根据商家ID获取商家预设问答列表")
  public ResponseEntity<List<PresetQnA>> listByMerchantId(
      @RequestParam @ApiParam(value = "商家ID", required = true) long merchantId) {
    List<PresetQnA> list = presentQnAService.findByMerchantId(merchantId);
    return ResponseEntity.ok(list);
  }
}
