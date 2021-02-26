package livelihoodzone.dto.user_management;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserDataDTO {
  
  @ApiModelProperty(position = 0)
  private String firstName;
  @ApiModelProperty(position = 1)
  private String middleName;
  @ApiModelProperty(position = 2)
  private String surname;
  @ApiModelProperty(position = 3)
  private String userEmail;
  @ApiModelProperty(position = 4)
  private String password;
  @ApiModelProperty(position = 5)
  private int countyId;
  @ApiModelProperty(position = 6)
  private String organizationName;
  @ApiModelProperty(position = 7)
  private List<RoleAssignmentDto> rolesToBeAssigned;

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

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getCountyId() {
    return countyId;
  }

  public void setCountyId(int countyId) {
    this.countyId = countyId;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public List<RoleAssignmentDto> getRolesToBeAssigned() {
    return rolesToBeAssigned;
  }

  public void setRolesToBeAssigned(List<RoleAssignmentDto> rolesToBeAssigned) {
    this.rolesToBeAssigned = rolesToBeAssigned;
  }
}
