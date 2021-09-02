package livelihoodzone.dto.user_management;

import java.util.List;

public class UserUpdateRequestDto {
    private int userId;
    private String userFirstName;
    private String userMiddleName;
    private String userSurname;
    private String organization;
    private String phoneNumber;
    private int newlyAssignedCountyId;
    private List<RoleAssignmentDto> rolesToBeAssigned;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNewlyAssignedCountyId() {
        return newlyAssignedCountyId;
    }

    public void setNewlyAssignedCountyId(int newlyAssignedCountyId) {
        this.newlyAssignedCountyId = newlyAssignedCountyId;
    }

    public List<RoleAssignmentDto> getRolesToBeAssigned() {
        return rolesToBeAssigned;
    }

    public void setRolesToBeAssigned(List<RoleAssignmentDto> rolesToBeAssigned) {
        this.rolesToBeAssigned = rolesToBeAssigned;
    }
}
