package livelihoodzone.dto.reports.zonal.wealthgroup;

public class WealthGroupReportResponseDto {
    private double veryPoor;
    private double poor;
    private double medium;
    private double betterOff;

    public double getVeryPoor() {
        return veryPoor;
    }

    public void setVeryPoor(double veryPoor) {
        this.veryPoor = veryPoor;
    }

    public double getPoor() {
        return poor;
    }

    public void setPoor(double poor) {
        this.poor = poor;
    }

    public double getMedium() {
        return medium;
    }

    public void setMedium(double medium) {
        this.medium = medium;
    }

    public double getBetterOff() {
        return betterOff;
    }

    public void setBetterOff(double betterOff) {
        this.betterOff = betterOff;
    }
}
