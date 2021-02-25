package livelihoodzone.entity.user_management;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "users")
public class User implements java.io.Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "UserId")
  private int userId;

  @Column(name = "CountyId")
  private String countyId;

  @Column(name = "UserName")
  private String userName;

  @Column(name = "UserEmail")
  private String userEmail;

  @Column(name = "OrganizationName")
  private String organizationName;

  @Column(name = "EncryptedPassword")
  private String encryptedPassword;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getCountyId() {
    return countyId;
  }

  public void setCountyId(String countyId) {
    this.countyId = countyId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }
}
