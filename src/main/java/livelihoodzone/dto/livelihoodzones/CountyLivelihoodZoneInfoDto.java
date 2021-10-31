package livelihoodzone.dto.livelihoodzones;

import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.service.retrofit.livelihoodzones.SubLocationRetrofitModel;

import java.util.List;

public class CountyLivelihoodZoneInfoDto {
    private int livelihoodzoneId;
    private String livelihoodZoneName;
    private List<SubLocationRetrofitModel> sublocationsUnderTheLivelihoodZone;

    public CountyLivelihoodZoneInfoDto(int livelihoodzoneId, String livelihoodZoneName, List<SubLocationRetrofitModel> sublocationsUnderTheLivelihoodZone) {
        this.livelihoodzoneId = livelihoodzoneId;
        this.livelihoodZoneName = livelihoodZoneName;
        this.sublocationsUnderTheLivelihoodZone = sublocationsUnderTheLivelihoodZone;
    }

    public int getLivelihoodzoneId() {
        return livelihoodzoneId;
    }

    public void setLivelihoodzoneId(int livelihoodzoneId) {
        this.livelihoodzoneId = livelihoodzoneId;
    }

    public String getLivelihoodZoneName() {
        return livelihoodZoneName;
    }

    public void setLivelihoodZoneName(String livelihoodZoneName) {
        this.livelihoodZoneName = livelihoodZoneName;
    }

    public List<SubLocationRetrofitModel> getSublocationsUnderTheLivelihoodZone() {
        return sublocationsUnderTheLivelihoodZone;
    }

    public void setSublocationsUnderTheLivelihoodZone(List<SubLocationRetrofitModel> sublocationsUnderTheLivelihoodZone) {
        this.sublocationsUnderTheLivelihoodZone = sublocationsUnderTheLivelihoodZone;
    }
}
