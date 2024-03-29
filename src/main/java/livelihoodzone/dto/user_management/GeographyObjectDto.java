package livelihoodzone.dto.user_management;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.countries.CountriesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.calendar.MonthsEntity;
import livelihoodzone.entity.questionnaire.crops.CropsEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentEntity;
import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsEntity;
import livelihoodzone.service.retrofit.livelihoodzones.CountySubLocationsLivelihoodZonesAssignmentRetrofitModel;

import java.util.List;

public class GeographyObjectDto {
    private List<LivelihoodZonesEntity> livelihoodZones;
    private CountiesEntity county;
    private List<SubLocationEntity> subLocations;
    private List<CropsEntity> crops;
    private List<EthnicGroupsEntity> ethnicGroups;
    private List<MonthsEntity> months;
    private List<SubCountyEntity> subCounties;
    private List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel> sublocationsLivelihoodZoneAssignments;
    private List<LivelihoodZonesEntity> currentUserAssignedCountyLivelihoodZones;
    private List<CountiesEntity> counties;
    private List<CountriesEntity> countries;

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

    public List<CropsEntity> getCrops() {
        return crops;
    }

    public void setCrops(List<CropsEntity> crops) {
        this.crops = crops;
    }

    public List<EthnicGroupsEntity> getEthnicGroups() {
        return ethnicGroups;
    }

    public void setEthnicGroups(List<EthnicGroupsEntity> ethnicGroups) {
        this.ethnicGroups = ethnicGroups;
    }

    public List<MonthsEntity> getMonths() {
        return months;
    }

    public void setMonths(List<MonthsEntity> months) {
        this.months = months;
    }

    public List<SubCountyEntity> getSubCounties() {
        return subCounties;
    }

    public void setSubCounties(List<SubCountyEntity> subCounties) {
        this.subCounties = subCounties;
    }

    public List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel> getSublocationsLivelihoodZoneAssignments() {
        return sublocationsLivelihoodZoneAssignments;
    }

    public void setSublocationsLivelihoodZoneAssignments(List<CountySubLocationsLivelihoodZonesAssignmentRetrofitModel> sublocationsLivelihoodZoneAssignments) {
        this.sublocationsLivelihoodZoneAssignments = sublocationsLivelihoodZoneAssignments;
    }

    public List<LivelihoodZonesEntity> getCurrentUserAssignedCountyLivelihoodZones() {
        return currentUserAssignedCountyLivelihoodZones;
    }

    public void setCurrentUserAssignedCountyLivelihoodZones(List<LivelihoodZonesEntity> currentUserAssignedCountyLivelihoodZones) {
        this.currentUserAssignedCountyLivelihoodZones = currentUserAssignedCountyLivelihoodZones;
    }

    public List<CountriesEntity> getCountries() {
        return countries;
    }

    public void setCountries(List<CountriesEntity> countries) {
        this.countries = countries;
    }

    public List<CountiesEntity> getCounties() {
        return counties;
    }

    public void setCounties(List<CountiesEntity> counties) {
        this.counties = counties;
    }
}
