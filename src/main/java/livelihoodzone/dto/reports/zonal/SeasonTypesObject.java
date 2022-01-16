package livelihoodzone.dto.reports.zonal;

public class SeasonTypesObject {
    int seasonCode;
    String seasonName;

    public SeasonTypesObject() {
    }

    public SeasonTypesObject(int seasonCode, String seasonName) {
        this.seasonCode = seasonCode;
        this.seasonName = seasonName;
    }

    public int getSeasonCode() {
        return seasonCode;
    }

    public void setSeasonCode(int seasonCode) {
        this.seasonCode = seasonCode;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
}
