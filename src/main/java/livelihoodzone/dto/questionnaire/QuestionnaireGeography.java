package livelihoodzone.dto.questionnaire;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;

public class QuestionnaireGeography {
    private SubCountyEntity selectedSubCounty;
    private WardEntity selectedWard;
    private SubLocationEntity selectedSubLocation;
    private WealthGroupDto selectedWealthGroup;

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
}
