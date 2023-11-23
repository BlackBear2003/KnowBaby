package host.luke.knowbaby.entity.dto;

import host.luke.knowbaby.entity.ChatMessage;
import host.luke.knowbaby.entity.ChatSession;
import java.util.List;

public class ChatSessionDto4C {

  private ChatSession chatSession;

  private List<ChatMessage> histories;
  private MerchantInfoDto merchantInfo;
  private List<MerchandiseInfoDto> merchandiseInfoList;

  public ChatSession getChatSession() {
    return chatSession;
  }

  public void setChatSession(ChatSession chatSession) {
    this.chatSession = chatSession;
  }

  public List<ChatMessage> getHistories() {
    return histories;
  }

  public void setHistories(List<ChatMessage> histories) {
    this.histories = histories;
  }

  public MerchantInfoDto getMerchantInfo() {
    return merchantInfo;
  }

  public void setMerchantInfo(MerchantInfoDto merchantInfo) {
    this.merchantInfo = merchantInfo;
  }

  public List<MerchandiseInfoDto> getMerchandiseInfoList() {
    return merchandiseInfoList;
  }

  public void setMerchandiseInfoList(
      List<MerchandiseInfoDto> merchandiseInfoList) {
    this.merchandiseInfoList = merchandiseInfoList;
  }
}
