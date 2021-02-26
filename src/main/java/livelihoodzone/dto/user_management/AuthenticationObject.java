package livelihoodzone.dto.user_management;

public class AuthenticationObject {
    private boolean isAuthenticationSuccessful;
    private String accessToken;
    private String userEmail;
    private String organizationName;


    public AuthenticationObject(boolean isAuthenticationSuccessful, String accessToken, String userEmail, String organizationName) {
        this.isAuthenticationSuccessful = isAuthenticationSuccessful;
        this.accessToken = accessToken;
        this.userEmail = userEmail;
        this.organizationName = organizationName;
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
}
