package livelihoodzone.entity.questionnaire.county;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "lz_hazards")
public class LzHazardsEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LzHazardId")
    private int lzHazardId;

    @Column(name = "LzHazardDescription")
    private String lzHazardDescription;

    @Column(name = "LzHazardCode")
    private int lzHazardCode;

    public int getLzHazardId() {
        return lzHazardId;
    }

    public void setLzHazardId(int lzHazardId) {
        this.lzHazardId = lzHazardId;
    }

    public String getLzHazardDescription() {
        return lzHazardDescription;
    }

    public void setLzHazardDescription(String lzHazardDescription) {
        this.lzHazardDescription = lzHazardDescription;
    }

    public int getLzHazardCode() {
        return lzHazardCode;
    }

    public void setLzHazardCode(int lzHazardCode) {
        this.lzHazardCode = lzHazardCode;
    }
}
