package livelihoodzone.dto.user_management;

public class SimplifiedUserRolesDto {
    private String roleDescription;
    private boolean hasThisRole;
    private int roleId;
    private int roleCode;
    private int userRoleId;

    public SimplifiedUserRolesDto(String roleDescription, boolean hasThisRole, int roleCode) {
        this.roleDescription = roleDescription;
        this.hasThisRole = hasThisRole;
        this.roleCode = roleCode;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public boolean isHasThisRole() {
        return hasThisRole;
    }

    public void setHasThisRole(boolean hasThisRole) {
        this.hasThisRole = hasThisRole;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
