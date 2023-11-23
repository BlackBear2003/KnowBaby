package host.luke.knowbaby.controller;

import host.luke.knowbaby.auth.Status;
import host.luke.knowbaby.auth.UserInfo;
import host.luke.knowbaby.auth.UserInfoHolder;
import host.luke.knowbaby.entity.ChatSession;
import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.dto.ChatSessionDto4C;
import host.luke.knowbaby.entity.dto.ChatSessionDto4M;
import host.luke.knowbaby.entity.dto.MerchandiseInfoDto;
import host.luke.knowbaby.entity.dto.MerchantInfoDto;
import host.luke.knowbaby.service.ChatMessageService;
import host.luke.knowbaby.service.ChatSessionService;
import host.luke.knowbaby.service.CustomerService;
import host.luke.knowbaby.service.MerchandiseCarouselService;
import host.luke.knowbaby.service.MerchandiseService;
import host.luke.knowbaby.service.MerchantCarouselService;
import host.luke.knowbaby.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/session")
@Api(tags = "用户商家聊天列表相关接口")
public class ChatSessionController {

  @Autowired
  ChatSessionService chatSessionService;
  @Autowired
  MerchantService merchantService;
  @Autowired
  MerchandiseService merchandiseService;
  @Autowired
  CustomerService customerService;
  @Autowired
  ChatMessageService chatMessageService;
  @Autowired
  MerchantCarouselService merchantCarouselService;
  @Autowired
  MerchandiseCarouselService merchandiseCarouselService;

  @ApiOperation("获取顾客的聊天会话列表")
  @GetMapping("/list-by-customer")
  public ResponseEntity<List<ChatSessionDto4C>> getCustomerChatSessionList() {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (Objects.isNull(userInfo)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    if (!userInfo.getStatus().equals(Status.Customer.name())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    List<ChatSession> sessionList = chatSessionService.findByCustomerId(userInfo.getUserId());
    List<ChatSessionDto4C> dtoList = new ArrayList<>();
    for (ChatSession session : sessionList) {
      ChatSessionDto4C each = makeSessionDtoForCustomer(session);
      dtoList.add(each);
    }
    return ResponseEntity.ok(dtoList);
  }

  @ApiOperation("获取商家的聊天会话列表")
  @GetMapping("/list-by-merchant")
  public ResponseEntity<List<ChatSessionDto4M>> getMerchantChatSessionList() {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (Objects.isNull(userInfo)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    if (!userInfo.getStatus().equals(Status.Merchant.name())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    List<ChatSession> sessionList = chatSessionService.findByMerchantId(userInfo.getUserId());
    List<ChatSessionDto4M> dtoList = new ArrayList<>();
    for (ChatSession session : sessionList) {
      ChatSessionDto4M each = makeSessionDtoForMerchant(session);
      dtoList.add(each);
    }
    return ResponseEntity.ok(dtoList);
  }

  @ApiOperation("客户通过商户给的Url创建聊天会话")
  @GetMapping("/merchant/{merchantId}")
  public ResponseEntity<ChatSessionDto4C> createSessionByMerchantId(
      @ApiParam("商户的ID") @PathVariable long merchantId
  ) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (Objects.isNull(userInfo)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    if (!userInfo.getStatus().equals(Status.Customer.name())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    if (chatSessionService.isSessionExist(userInfo.getUserId(), merchantId)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    ChatSession init = chatSessionService.initByMerchantIdAndCustomerId(merchantId,
        userInfo.getUserId());
    ChatSessionDto4C sessionDto = makeSessionDtoForCustomer(init);
    return ResponseEntity.ok(sessionDto);
  }

  @ApiOperation("清除聊天会话消息")
  @DeleteMapping("/")
  public ResponseEntity<Void> clearSessionBySessionId(
      @ApiParam("聊天会话的ID") @RequestParam long sessionId
  ) {
    chatMessageService.clearSession(sessionId);
    return ResponseEntity.ok().build();
  }


  ChatSessionDto4C makeSessionDtoForCustomer(ChatSession session) {
    ChatSessionDto4C dto = new ChatSessionDto4C();

    dto.setChatSession(session);

    MerchantInfoDto merchantInfo = new MerchantInfoDto();
    merchantInfo.setMerchant(merchantService.findById(session.getMerchantId()));
    merchantInfo.setMerchantCarouselList(merchantCarouselService.findByMerchantId(session.getMerchantId()));
    dto.setMerchantInfo(merchantInfo);

    dto.setHistories(chatMessageService.findBySessionIdExceptPrompts(session.getId()));

    List<Merchandise> merchandiseList = merchandiseService.findByMerchantId(session.getMerchantId());
    List<MerchandiseInfoDto> merchandiseInfoDtoList = new ArrayList<>();
    for (Merchandise m : merchandiseList) {
      MerchandiseInfoDto merchandiseInfo = new MerchandiseInfoDto();
      merchandiseInfo.setMerchandise(m);
      merchandiseInfo.setMerchandiseCarouselList(merchandiseCarouselService.findByMerchandiseId(m.getId()));
      merchandiseInfoDtoList.add(merchandiseInfo);
    }
    dto.setMerchandiseInfoList(merchandiseInfoDtoList);

    return dto;
  }

  ChatSessionDto4M makeSessionDtoForMerchant(ChatSession session) {
    ChatSessionDto4M dto = new ChatSessionDto4M();
    dto.setChatSession(session);
    dto.setCustomer(customerService.findById(session.getCustomerId()));
    dto.setHistories(chatMessageService.findBySessionIdExceptPrompts(session.getId()));
    return dto;
  }

}
