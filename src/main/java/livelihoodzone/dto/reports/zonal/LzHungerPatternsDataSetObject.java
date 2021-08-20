package livelihoodzone.dto.reports.zonal;

import java.util.List;

public class LzHungerPatternsDataSetObject {
    private List<Number> longRains;
    private List<Number> shortRains;
    private List<Number> endLongBeginShort;
    private List<Number> endShortBeginLong;

    public LzHungerPatternsDataSetObject() {
    }

    public LzHungerPatternsDataSetObject(List<Number> longRains, List<Number> shortRains, List<Number> endLongBeginShort, List<Number> endShortBeginLong) {
        this.longRains = longRains;
        this.shortRains = shortRains;
        this.endLongBeginShort = endLongBeginShort;
        this.endShortBeginLong = endShortBeginLong;
    }

    public List<Number> getLongRains() {
        return longRains;
    }

    public void setLongRains(List<Number> longRains) {
        this.longRains = longRains;
    }

    public List<Number> getShortRains() {
        return shortRains;
    }

    public void setShortRains(List<Number> shortRains) {
        this.shortRains = shortRains;
    }

    public List<Number> getEndLongBeginShort() {
        return endLongBeginShort;
    }

    public void setEndLongBeginShort(List<Number> endLongBeginShort) {
        this.endLongBeginShort = endLongBeginShort;
    }

    public List<Number> getEndShortBeginLong() {
        return endShortBeginLong;
    }

    public void setEndShortBeginLong(List<Number> endShortBeginLong) {
        this.endShortBeginLong = endShortBeginLong;
    }
}
