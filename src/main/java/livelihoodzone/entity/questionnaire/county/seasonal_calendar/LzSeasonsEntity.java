package livelihoodzone.entity.questionnaire.county.seasonal_calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_seasons")
public class LzSeasonsEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzSeasonId")
    private int lzSeasonId;

    @Column(name = "LzSeasonName")
    private String lzSeasonName;

    @Column(name = "LzSeasonCode")
    private int lzSeasonCode;

    public int getLzSeasonId() {
        return lzSeasonId;
    }

    public void setLzSeasonId(int lzSeasonId) {
        this.lzSeasonId = lzSeasonId;
    }

    public String getLzSeasonName() {
        return lzSeasonName;
    }

    public void setLzSeasonName(String lzSeasonName) {
        this.lzSeasonName = lzSeasonName;
    }

    public int getLzSeasonCode() {
        return lzSeasonCode;
    }

    public void setLzSeasonCode(int lzSeasonCode) {
        this.lzSeasonCode = lzSeasonCode;
    }
}
