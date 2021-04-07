package livelihoodzone.dto.user_management;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;
import livelihoodzone.entity.questionnaire.LivelihoodZonesEntity;

import java.util.ArrayList;
import java.util.List;

public class GeographyObjectDto {
    private List<LivelihoodZonesEntity> livelihoodZones;
    private CountiesEntity county;
    private List<SubLocationEntity> subLocations;

    public List<LivelihoodZonesEntity> getLivelihoodZones() {
        return livelihoodZones;
    }

    public void setLivelihoodZones(List<LivelihoodZonesEntity> livelihoodZones) {
        this.livelihoodZones = livelihoodZones;
    }

    public CountiesEntity getCounty() {
        return county;
    }

    public void setCounty(CountiesEntity county) {
        this.county = county;
    }

    public List<SubLocationEntity> getSubLocations() {
        return subLocations;
    }

    public void setSubLocations(List<SubLocationEntity> subLocations) {
        this.subLocations = subLocations;
    }
}
