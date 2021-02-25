package livelihoodzone.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import livelihoodzone.model.Role;

public class UserDataDTO {
  
  @ApiModelProperty(position = 0)
  private String userName;
  @ApiModelProperty(position = 1)
  private String userEmail;
  @ApiModelProperty(position = 2)
  private String encryptedPassword;
  @ApiModelProperty(position = 3)
  private int countyId;
  @ApiModelProperty(position = 4)
  private String organizationName;

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

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
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
}
