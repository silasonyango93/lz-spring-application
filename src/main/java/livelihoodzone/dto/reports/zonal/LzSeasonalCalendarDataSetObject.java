package livelihoodzone.dto.reports.zonal;

import java.util.List;

public class LzSeasonalCalendarDataSetObject {
    private List<String> drySeasonMonths;
    private List<String> longRainsMonths;
    private List<String> shortRainsMonths;

    public List<String> getDrySeasonMonths() {
        return drySeasonMonths;
    }

    public void setDrySeasonMonths(List<String> drySeasonMonths) {
        this.drySeasonMonths = drySeasonMonths;
    }

    public List<String> getLongRainsMonths() {
        return longRainsMonths;
    }

    public void setLongRainsMonths(List<String> longRainsMonths) {
        this.longRainsMonths = longRainsMonths;
    }

    public List<String> getShortRainsMonths() {
        return shortRainsMonths;
    }

    public void setShortRainsMonths(List<String> shortRainsMonths) {
        this.shortRainsMonths = shortRainsMonths;
    }
}
