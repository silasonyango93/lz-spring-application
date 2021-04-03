package livelihoodzone.entity.questionnaire.wealthgroup;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "wg_questionnaire_sessions")
public class WgQuestionnaireSessionEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WgQuestionnaireSessionId")
    private int wgQuestionnaireSessionId;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "WgQuestionnaireTypeId")
    private int wgQuestionnaireTypeId;

    @Column(name = "WealthGroupId")
    private int wealthGroupId;

    @Column(name = "CountyId")
    private int countyId;

    @Column(name = "SubCountyId")
    private int subCountyId;

    @Column(name = "WardId")
    private int wardId;

    @Column(name = "SubLocationId")
    private int subLocationId;

    @Column(name = "LivelihoodZoneId")
    private int livelihoodZoneId;

    @Column(name = "QuestionnaireSessionDescription")
    private String questionnaireSessionDescription;

    @Column(name = "Latitude")
    private double latitude;

    @Column(name = "Longitude")
    private double longitude;

    @Column(name = "SessionStartDate")
    private String sessionStartDate;

    @Column(name = "SessionEndDate")
    private String sessionEndDate;

    @Column(name = "HasBeenSubmitted")
    private int hasBeenSubmitted;

    @Column(name = "QuestionnaireUniqueId")
    private String questionnaireUniqueId;

    public WgQuestionnaireSessionEntity() {
    }

    public int getWgQuestionnaireSessionId() {
        return wgQuestionnaireSessionId;
    }

    public void setWgQuestionnaireSessionId(int wgQuestionnaireSessionId) {
        this.wgQuestionnaireSessionId = wgQuestionnaireSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWgQuestionnaireTypeId() {
        return wgQuestionnaireTypeId;
    }

    public void setWgQuestionnaireTypeId(int wgQuestionnaireTypeId) {
        this.wgQuestionnaireTypeId = wgQuestionnaireTypeId;
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

    public int getHasBeenSubmitted() {
        return hasBeenSubmitted;
    }

    public void setHasBeenSubmitted(int hasBeenSubmitted) {
        this.hasBeenSubmitted = hasBeenSubmitted;
    }

    public String getQuestionnaireUniqueId() {
        return questionnaireUniqueId;
    }

    public void setQuestionnaireUniqueId(String questionnaireUniqueId) {
        this.questionnaireUniqueId = questionnaireUniqueId;
    }
}
