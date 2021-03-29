package livelihoodzone.dto.user_management;

import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;

import java.util.ArrayList;
import java.util.List;

public class GeographyObjectDto {
    private List<SubCountyEntity> subCounties = new ArrayList<>();
    private List<WardEntity> wards = new ArrayList<>();
    private List<SubLocationEntity> subLocations = new ArrayList<>();

    public List<SubCountyEntity> getSubCounties() {
        return subCounties;
    }

    public void setSubCounties(List<SubCountyEntity> subCounties) {
        this.subCounties = subCounties;
    }

    public List<WardEntity> getWards() {
        return wards;
    }

    public void setWards(List<WardEntity> wards) {
        this.wards = wards;
    }

    public List<SubLocationEntity> getSubLocations() {
        return subLocations;
    }

    public void setSubLocations(List<SubLocationEntity> subLocations) {
        this.subLocations = subLocations;
    }
}
