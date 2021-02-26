package livelihoodzone.dto.user_management;

import java.util.List;

public class AuthenticationObject {
    private boolean isAuthenticationSuccessful;
    private String accessToken;
    private String firstName;
    private String middleName;
    private String surname;
    private String userEmail;
    private String organizationName;
    private List<SimplifiedUserRolesDto> roles;


    public AuthenticationObject(boolean isAuthenticationSuccessful, String accessToken,String firstName ,String middleName ,String surname , String userEmail, String organizationName, List<SimplifiedUserRolesDto> roles) {
        this.isAuthenticationSuccessful = isAuthenticationSuccessful;
        this.accessToken = accessToken;
        this.userEmail = userEmail;
        this.organizationName = organizationName;
        this.roles = roles;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
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
