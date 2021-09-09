package livelihoodzone.dto.reports.wealthgroup;

import java.util.List;

public class WgMigrationPatternsDataSetObject {
    private List<Number> fullyNomadic;
    private List<Number> semiNomadic;
    private List<Number> occasionalNomadic;
    private List<Number> outMigrantLabour;
    private List<Number> inMigrantLabour;
    private List<Number> fullySettled;
    private List<Number> internallyDisplaced;

    public WgMigrationPatternsDataSetObject() {
    }

    public WgMigrationPatternsDataSetObject(List<Number> fullyNomadic, List<Number> semiNomadic, List<Number> occasionalNomadic, List<Number> outMigrantLabour, List<Number> inMigrantLabour, List<Number> fullySettled, List<Number> internallyDisplaced) {
        this.fullyNomadic = fullyNomadic;
        this.semiNomadic = semiNomadic;
        this.occasionalNomadic = occasionalNomadic;
        this.outMigrantLabour = outMigrantLabour;
        this.inMigrantLabour = inMigrantLabour;
        this.fullySettled = fullySettled;
        this.internallyDisplaced = internallyDisplaced;
    }

    public List<Number> getFullyNomadic() {
        return fullyNomadic;
    }

    public void setFullyNomadic(List<Number> fullyNomadic) {
        this.fullyNomadic = fullyNomadic;
    }

    public List<Number> getSemiNomadic() {
        return semiNomadic;
    }

    public void setSemiNomadic(List<Number> semiNomadic) {
        this.semiNomadic = semiNomadic;
    }

    public List<Number> getOccasionalNomadic() {
        return occasionalNomadic;
    }

    public void setOccasionalNomadic(List<Number> occasionalNomadic) {
        this.occasionalNomadic = occasionalNomadic;
    }

    public List<Number> getOutMigrantLabour() {
        return outMigrantLabour;
    }

    public void setOutMigrantLabour(List<Number> outMigrantLabour) {
        this.outMigrantLabour = outMigrantLabour;
    }

    public List<Number> getInMigrantLabour() {
        return inMigrantLabour;
    }

    public void setInMigrantLabour(List<Number> inMigrantLabour) {
        this.inMigrantLabour = inMigrantLabour;
    }

    public List<Number> getFullySettled() {
        return fullySettled;
    }

    public void setFullySettled(List<Number> fullySettled) {
        this.fullySettled = fullySettled;
    }

    public List<Number> getInternallyDisplaced() {
        return internallyDisplaced;
    }

    public void setInternallyDisplaced(List<Number> internallyDisplaced) {
        this.internallyDisplaced = internallyDisplaced;
    }
}
