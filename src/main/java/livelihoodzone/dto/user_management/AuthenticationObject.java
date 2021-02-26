package livelihoodzone.dto.user_management;

import java.util.List;

public class AuthenticationObject {
    private boolean isAuthenticationSuccessful;
    private String accessToken;
    private String userEmail;
    private String organizationName;
    private List<SimplifiedUserRolesDto> roles;


    public AuthenticationObject(boolean isAuthenticationSuccessful, String accessToken, String userEmail, String organizationName, List<SimplifiedUserRolesDto> roles) {
        this.isAuthenticationSuccessful = isAuthenticationSuccessful;
        this.accessToken = accessToken;
        this.userEmail = userEmail;
        this.organizationName = organizationName;
        this.roles = roles;
    }

    public boolean isAuthenticationSuccessful() {
        return isAuthenticationSuccessful;
    }

    public void setAuthenticationSuccessful(boolean authenticationSuccessful) {
        isAuthenticationSuccessful = authenticationSuccessful;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public List<SimplifiedUserRolesDto> getRoles() {
        return roles;
    }

    public void setRoles(List<SimplifiedUserRolesDto> roles) {
        this.roles = roles;
    }
}
