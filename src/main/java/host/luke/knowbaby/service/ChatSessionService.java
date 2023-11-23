package host.luke.knowbaby.service;

import com.plexpt.chatgpt.entity.chat.Message;
import host.luke.knowbaby.auth.UserInfoHolder;
import host.luke.knowbaby.entity.ChatSession;
import host.luke.knowbaby.entity.Merchandise;
import host.luke.knowbaby.entity.MerchandiseAttr;
import host.luke.knowbaby.entity.Merchant;
import host.luke.knowbaby.entity.PresetQnA;
import host.luke.knowbaby.repository.ChatSessionRepository;
import host.luke.knowbaby.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatSessionService {

  @Autowired
  ChatSessionRepository chatSessionRepository;
  @Autowired
  ChatMessageService messageService;

  public List<ChatSession> findByCustomerId(long customerId) {
    return chatSessionRepository.findByCustomerId(customerId);
  }

  //TODO: add update-time for ChatSession
  public List<ChatSession> findByMerchantId(long merchantId) {
    return chatSessionRepository.findByMerchantId(merchantId);
  }

  public ChatSession findByCustomerIdAndMerchantId(long customerId, long merchantId) {
    return chatSessionRepository.findByCustomerIdAndMerchantId(customerId, merchantId);
  }

  public ChatSession initByMerchantIdAndCustomerId(long merchantId, long customerId) {

    ChatSession session = new ChatSession();
    session.setCustomerId(customerId);
    session.setMerchantId(merchantId);
    ChatSession save = chatSessionRepository.save(session);

    return save;
  }

  public boolean isSessionExist(long customerId, long merchantId) {
    return chatSessionRepository.existsByCustomerIdAndMerchantId(customerId, merchantId);
  }



}
