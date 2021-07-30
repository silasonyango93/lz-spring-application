package livelihoodzone.dto.questionnaire.wealthgroup;

import livelihoodzone.dto.user_management.UserResponseDTO;

public class WealthGroupQuestionnaireSessionResponseDto {
    private int questionnaireSessionId;
    private int userId;
    private int questionnaireTypeId;
    private int wealthGroupId;
    private int countyId;
    private int subCountyId;
    private int wardId;
    private int subLocationId;
    private int livelihoodZoneId;
    private String questionnaireSessionDescription;
    private double latitude;
    private double longitude;
    private String sessionStartDate;
    private String sessionEndDate;
    private String questionnaireUniqueId;
    private String countyName;
    private String subCountyName;
    private String wardName;
    private String sublocationName;
    private String livelihoodzoneName;
    private String questionnaireTypeDescription;
    private UserResponseDTO userDetails;
    private String wealthGroupName;

    public WealthGroupQuestionnaireSessionResponseDto() {
    }

    public WealthGroupQuestionnaireSessionResponseDto(int questionnaireSessionId, int userId, int questionnaireTypeId, int wealthGroupId, int countyId, int subCountyId, int wardId, int subLocationId, int livelihoodZoneId, String questionnaireSessionDescription, double latitude, double longitude, String sessionStartDate, String sessionEndDate, String questionnaireUniqueId, String countyName, String subCountyName, String wardName, String sublocationName, String livelihoodzoneName, String questionnaireTypeDescription, UserResponseDTO userDetails, String wealthGroupName) {
        this.questionnaireSessionId = questionnaireSessionId;
        this.userId = userId;
        this.questionnaireTypeId = questionnaireTypeId;
        this.wealthGroupId = wealthGroupId;
        this.countyId = countyId;
        this.subCountyId = subCountyId;
        this.wardId = wardId;
        this.subLocationId = subLocationId;
        this.livelihoodZoneId = livelihoodZoneId;
        this.questionnaireSessionDescription = questionnaireSessionDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.questionnaireUniqueId = questionnaireUniqueId;
        this.countyName = countyName;
        this.subCountyName = subCountyName;
        this.subCountyName = subCountyName;
        this.wardName = wardName;
        this.sublocationName = sublocationName;
        this.livelihoodzoneName = livelihoodzoneName;
        this.questionnaireTypeDescription = questionnaireTypeDescription;
        this.userDetails = userDetails;
        this.wealthGroupName = wealthGroupName;
    }

    public int getQuestionnaireSessionId() {
        return questionnaireSessionId;
    }

    public void setQuestionnaireSessionId(int questionnaireSessionId) {
        this.questionnaireSessionId = questionnaireSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionnaireTypeId() {
        return questionnaireTypeId;
    }

    public void setQuestionnaireTypeId(int questionnaireTypeId) {
        this.questionnaireTypeId = questionnaireTypeId;
    }

    public int getWealthGroupId() {
        return wealthGroupId;
    }

    public void setWealthGroupId(int wealthGroupId) {
        this.wealthGroupId = wealthGroupId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getSubCountyId() {
        return subCountyId;
    }

    public void setSubCountyId(int subCountyId) {
        this.subCountyId = subCountyId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getLivelihoodZoneId() {
        return livelihoodZoneId;
    }

    public void setLivelihoodZoneId(int livelihoodZoneId) {
        this.livelihoodZoneId = livelihoodZoneId;
    }

    public String getQuestionnaireSessionDescription() {
        return questionnaireSessionDescription;
    }

    public void setQuestionnaireSessionDescription(String questionnaireSessionDescription) {
        this.questionnaireSessionDescription = questionnaireSessionDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public String getQuestionnaireUniqueId() {
        return questionnaireUniqueId;
    }

    public void setQuestionnaireUniqueId(String questionnaireUniqueId) {
        this.questionnaireUniqueId = questionnaireUniqueId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getSubCountyName() {
        return subCountyName;
    }

    public void setSubCountyName(String subCountyName) {
        this.subCountyName = subCountyName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getSublocationName() {
        return sublocationName;
    }

    public void setSublocationName(String sublocationName) {
        this.sublocationName = sublocationName;
    }

    public String getLivelihoodzoneName() {
        return livelihoodzoneName;
    }

    public void setLivelihoodzoneName(String livelihoodzoneName) {
        this.livelihoodzoneName = livelihoodzoneName;
    }

    public String getQuestionnaireTypeDescription() {
        return questionnaireTypeDescription;
    }

    public void setQuestionnaireTypeDescription(String questionnaireTypeDescription) {
        this.questionnaireTypeDescription = questionnaireTypeDescription;
    }

    public UserResponseDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserResponseDTO userDetails) {
        this.userDetails = userDetails;
    }

    public String getWealthGroupName() {
        return wealthGroupName;
    }

    public void setWealthGroupName(String wealthGroupName) {
        this.wealthGroupName = wealthGroupName;
    }
}
