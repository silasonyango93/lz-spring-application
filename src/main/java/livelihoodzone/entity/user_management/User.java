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

  @Column(name = "UserFirstName")
  private String firstName;

  @Column(name = "UserMiddleName")
  private String middleName;

  @Column(name = "UserSurname")
  private String surname;

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }
}
