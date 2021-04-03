package livelihoodzone.dto.questionnaire;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;
import livelihoodzone.entity.questionnaire.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.wealthgroup.WgQuestionnaireTypesEntity;

public class QuestionnaireGeography {
    private double latitude;
    private double longitude;
    private SubCountyEntity selectedSubCounty;
    private WardEntity selectedWard;
    private SubLocationEntity selectedSubLocation;
    private WealthGroupDto selectedWealthGroup;
    private LivelihoodZonesEntity selectedLivelihoodZone;
    private WgQuestionnaireTypesEntity selectedWgQuestionnaireType;

    public SubCountyEntity getSelectedSubCounty() {
        return selectedSubCounty;
    }

    public void setSelectedSubCounty(SubCountyEntity selectedSubCounty) {
        this.selectedSubCounty = selectedSubCounty;
    }

    public WardEntity getSelectedWard() {
        return selectedWard;
    }

    public void setSelectedWard(WardEntity selectedWard) {
        this.selectedWard = selectedWard;
    }

    public SubLocationEntity getSelectedSubLocation() {
        return selectedSubLocation;
    }

    public void setSelectedSubLocation(SubLocationEntity selectedSubLocation) {
        this.selectedSubLocation = selectedSubLocation;
    }

    public WealthGroupDto getSelectedWealthGroup() {
        return selectedWealthGroup;
    }

    public void setSelectedWealthGroup(WealthGroupDto selectedWealthGroup) {
        this.selectedWealthGroup = selectedWealthGroup;
    }

    public LivelihoodZonesEntity getSelectedLivelihoodZone() {
        return selectedLivelihoodZone;
    }

    public void setSelectedLivelihoodZone(LivelihoodZonesEntity selectedLivelihoodZone) {
        this.selectedLivelihoodZone = selectedLivelihoodZone;
    }

    public WgQuestionnaireTypesEntity getSelectedWgQuestionnaireType() {
        return selectedWgQuestionnaireType;
    }

    public void setSelectedWgQuestionnaireType(WgQuestionnaireTypesEntity selectedWgQuestionnaireType) {
        this.selectedWgQuestionnaireType = selectedWgQuestionnaireType;
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
}
