package livelihoodzone.dto.user_management;

public class RoleAssignmentDto {
    private int roleId;
    private boolean toBeAssignedThisRole;

    public RoleAssignmentDto() {
    }

    public RoleAssignmentDto(int roleId, boolean toBeAssignedThisRole) {
        this.roleId = roleId;
        this.toBeAssignedThisRole = toBeAssignedThisRole;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isToBeAssignedThisRole() {
        return toBeAssignedThisRole;
    }

    public void setToBeAssignedThisRole(boolean toBeAssignedThisRole) {
        this.toBeAssignedThisRole = toBeAssignedThisRole;
    }
}
