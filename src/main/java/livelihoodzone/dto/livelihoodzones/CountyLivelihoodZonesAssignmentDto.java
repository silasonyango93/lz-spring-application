package livelihoodzone.dto.livelihoodzones;

import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentStatus;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;

import java.util.List;

public class CountyLivelihoodZonesAssignmentDto {
    private CountyLivelihoodZonesAssignmentStatus countyLivelihoodZonesAssignmentStatus;
    private int countyId;
    private String countyName;
    private List<LivelihoodZonesEntity> livelihoodZones;

    public CountyLivelihoodZonesAssignmentDto() {
    }

    public CountyLivelihoodZonesAssignmentDto(CountyLivelihoodZonesAssignmentStatus countyLivelihoodZonesAssignmentStatus, int countyId, String countyName, List<LivelihoodZonesEntity> livelihoodZones) {
        this.countyLivelihoodZonesAssignmentStatus = countyLivelihoodZonesAssignmentStatus;
        this.countyId = countyId;
        this.countyName = countyName;
        this.livelihoodZones = livelihoodZones;
    }

    public CountyLivelihoodZonesAssignmentStatus getCountyLivelihoodZonesAssignmentStatus() {
        return countyLivelihoodZonesAssignmentStatus;
    }

    public void setCountyLivelihoodZonesAssignmentStatus(CountyLivelihoodZonesAssignmentStatus countyLivelihoodZonesAssignmentStatus) {
        this.countyLivelihoodZonesAssignmentStatus = countyLivelihoodZonesAssignmentStatus;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public List<LivelihoodZonesEntity> getLivelihoodZones() {
        return livelihoodZones;
    }

    public void setLivelihoodZones(List<LivelihoodZonesEntity> livelihoodZones) {
        this.livelihoodZones = livelihoodZones;
    }
}
