package livelihoodzone.dto.user_management;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import livelihoodzone.entity.user_management.Role;

public class UserResponseDTO {

  @ApiModelProperty(position = 0)
  private int userId;
  @ApiModelProperty(position = 1)
  private String countyName;
  @ApiModelProperty(position = 2)
  private String firstName;
  @ApiModelProperty(position = 3)
  private String middleName;
  @ApiModelProperty(position = 4)
  private String surname;
  @ApiModelProperty(position = 5)
  private String email;
  @ApiModelProperty(position = 6)
  private String organizationName;
  @ApiModelProperty(position = 7)
  private int countyId;
  @ApiModelProperty(position = 8)
  private String phoneNumber;
  @ApiModelProperty(position = 9)
  private List<SimplifiedUserRolesDto> userRoles;

  public UserResponseDTO() {
  }

  public UserResponseDTO(int userId, String countyName, String firstName, String middleName, String surname, String email, String organizationName) {
    this.userId = userId;
    this.countyName = countyName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.surname = surname;
    this.email = email;
    this.organizationName = organizationName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getCountyName() {
    return countyName;
  }

  public void setCountyName(String countyName) {
    this.countyName = countyName;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public int getCountyId() {
    return countyId;
  }

  public void setCountyId(int countyId) {
    this.countyId = countyId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public List<SimplifiedUserRolesDto> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<SimplifiedUserRolesDto> userRoles) {
    this.userRoles = userRoles;
  }
}
