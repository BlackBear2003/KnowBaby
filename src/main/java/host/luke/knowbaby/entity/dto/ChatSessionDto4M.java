package host.luke.knowbaby.entity.dto;

import host.luke.knowbaby.entity.ChatMessage;
import host.luke.knowbaby.entity.ChatSession;
import host.luke.knowbaby.entity.Customer;
import java.util.List;

public class ChatSessionDto4M {

  private ChatSession chatSession;
  private Customer customer;
  private List<ChatMessage> histories;

  public ChatSession getChatSession() {
    return chatSession;
  }

  public void setChatSession(ChatSession chatSession) {
    this.chatSession = chatSession;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<ChatMessage> getHistories() {
    return histories;
  }

  public void setHistories(List<ChatMessage> histories) {
    this.histories = histories;
  }
}
