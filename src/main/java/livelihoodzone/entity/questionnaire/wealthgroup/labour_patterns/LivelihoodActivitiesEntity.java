package livelihoodzone.entity.questionnaire.wealthgroup.labour_patterns;


import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "livelihood_activities")
public class LivelihoodActivitiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LivelihoodActivityId")
    private int livelihoodActivityId;

    @Column(name = "LivelihoodActivityDescription")
    private String livelihoodActivityDescription;

    @Column(name = "LivelihoodActivityCode")
    private int livelihoodActivityCode;

    public int getLivelihoodActivityId() {
        return livelihoodActivityId;
    }

    public void setLivelihoodActivityId(int livelihoodActivityId) {
        this.livelihoodActivityId = livelihoodActivityId;
    }

    public String getLivelihoodActivityDescription() {
        return livelihoodActivityDescription;
    }

    public void setLivelihoodActivityDescription(String livelihoodActivityDescription) {
        this.livelihoodActivityDescription = livelihoodActivityDescription;
    }

    public int getLivelihoodActivityCode() {
        return livelihoodActivityCode;
    }

    public void setLivelihoodActivityCode(int livelihoodActivityCode) {
        this.livelihoodActivityCode = livelihoodActivityCode;
    }
}
