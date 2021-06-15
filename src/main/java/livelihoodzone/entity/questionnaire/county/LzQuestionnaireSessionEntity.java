package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_questionnaire_sessions")

@javax.persistence.SqlResultSetMapping(
        name = "lz_questionnaire_sessions", entities =
@javax.persistence.EntityResult(entityClass = LzQuestionnaireSessionEntity.class)
)


@NamedNativeQueries({
        @NamedNativeQuery(
                name="LzQuestionnaireSessionEntity.fetchQuestionnaireSessionByCountyAndLivelihoodZone",
                query="SELECT * FROM lz_questionnaire_sessions WHERE lz_questionnaire_sessions.CountyId = ? AND lz_questionnaire_sessions.LivelihoodZoneId = ?",
                resultSetMapping = "lz_questionnaire_sessions")
})

public class LzQuestionnaireSessionEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzQuestionnaireSessionId")
    private int lzQuestionnaireSessionId;

    @Column(name = "UserId")
    private int userId;

    @Column(name = "CountyId")
    private int countyId;

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

    @Column(name = "LzQuestionnaireUniqueId")
    private String lzQuestionnaireUniqueId;

    @Column(name = "QuestionnaireJsonString")
    private String questionnaireJsonString;

    public LzQuestionnaireSessionEntity() {
    }

    public LzQuestionnaireSessionEntity(int userId, int countyId, int livelihoodZoneId, String questionnaireSessionDescription, double latitude, double longitude, String sessionStartDate, String sessionEndDate, String lzQuestionnaireUniqueId, String questionnaireJsonString) {
        this.userId = userId;
        this.countyId = countyId;
        this.livelihoodZoneId = livelihoodZoneId;
        this.questionnaireSessionDescription = questionnaireSessionDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.lzQuestionnaireUniqueId = lzQuestionnaireUniqueId;
        this.questionnaireJsonString = questionnaireJsonString;
    }

    public int getLzQuestionnaireSessionId() {
        return lzQuestionnaireSessionId;
    }

    public void setLzQuestionnaireSessionId(int lzQuestionnaireSessionId) {
        this.lzQuestionnaireSessionId = lzQuestionnaireSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
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

    public String getLzQuestionnaireUniqueId() {
        return lzQuestionnaireUniqueId;
    }

    public void setLzQuestionnaireUniqueId(String lzQuestionnaireUniqueId) {
        this.lzQuestionnaireUniqueId = lzQuestionnaireUniqueId;
    }

    public String getQuestionnaireJsonString() {
        return questionnaireJsonString;
    }

    public void setQuestionnaireJsonString(String questionnaireJsonString) {
        this.questionnaireJsonString = questionnaireJsonString;
    }
}
