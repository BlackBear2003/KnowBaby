package host.luke.knowbaby.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "ChatSessionId")
  private long chatSessionId;
  @Basic
  @Column(name = "Content")
  private String content;
  @Basic
  @Column(name = "Role")
  private String role;
  @Deprecated
  @Basic
  @Column(name = "IsPrompt")
  private Boolean isPrompt;
  @Basic
  @Column(name = "CreateTime")
  private Date createTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getChatSessionId() {
    return chatSessionId;
  }

  public void setChatSessionId(long chatSessionId) {
    this.chatSessionId = chatSessionId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Boolean isPrompt() {
    return isPrompt;
  }

  public void setIsPrompt(Boolean prompt) {
    isPrompt = prompt;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatMessage that = (ChatMessage) o;
    return id == that.id && chatSessionId == that.chatSessionId && Objects.equals(content,
        that.content) && Objects.equals(role, that.role) && Objects.equals(
        isPrompt, that.isPrompt) && Objects.equals(createTime, that.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, chatSessionId, content, role, isPrompt, createTime);
  }
}
