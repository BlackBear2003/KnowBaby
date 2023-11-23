package host.luke.knowbaby.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "Id")
  private long id;
  @Basic
  @Column(name = "Height")
  private String height;
  @Basic
  @Column(name = "Weight")
  private String weight;
  @Basic
  @Column(name = "Age")
  private String age;
  @Basic
  @Column(name = "Features")
  private String features;
  @Basic
  @Column(name = "Username")
  private String username;
  @Basic
  @Column(name = "Password")
  private String password;
  @Basic
  @Column(name = "Nickname")
  private String nickname;
  @Basic
  @Column(name = "Email")
  private String email;
  @Basic
  @Column(name = "AvatarUrl")
  private String avatarUrl;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getFeatures() {
    return features;
  }

  public void setFeatures(String features) {
    this.features = features;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return id == customer.id && Objects.equals(height, customer.height)
        && Objects.equals(weight, customer.weight) && Objects.equals(age,
        customer.age) && Objects.equals(features, customer.features)
        && Objects.equals(username, customer.username) && Objects.equals(password,
        customer.password) && Objects.equals(nickname, customer.nickname)
        && Objects.equals(email, customer.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, height, weight, age, features, username, password, nickname, email);
  }
}
