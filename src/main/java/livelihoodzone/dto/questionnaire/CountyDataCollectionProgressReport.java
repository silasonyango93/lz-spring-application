package livelihoodzone.dto.questionnaire;

import java.util.List;

public class CountyDataCollectionProgressReport {
    private boolean isZoneLevelDataCollectionCompleted;
    private boolean isWealthGroupDataCollectionCompleted;
    private List<String> completedZoneLevelQuestionnaires;
    private List<String> pendingZoneLevelQuestionnaires;
    private List<String> completedWealthGroupQuestionnaires;
    private List<String> pendingWealthGroupQuestionnaires;

    public boolean isZoneLevelDataCollectionCompleted() {
        return isZoneLevelDataCollectionCompleted;
    }

    public void setZoneLevelDataCollectionCompleted(boolean zoneLevelDataCollectionCompleted) {
        isZoneLevelDataCollectionCompleted = zoneLevelDataCollectionCompleted;
    }

    public boolean isWealthGroupDataCollectionCompleted() {
        return isWealthGroupDataCollectionCompleted;
    }

    public void setWealthGroupDataCollectionCompleted(boolean wealthGroupDataCollectionCompleted) {
        isWealthGroupDataCollectionCompleted = wealthGroupDataCollectionCompleted;
    }

    public List<String> getCompletedZoneLevelQuestionnaires() {
        return completedZoneLevelQuestionnaires;
    }

    public void setCompletedZoneLevelQuestionnaires(List<String> completedZoneLevelQuestionnaires) {
        this.completedZoneLevelQuestionnaires = completedZoneLevelQuestionnaires;
    }

    public List<String> getPendingZoneLevelQuestionnaires() {
        return pendingZoneLevelQuestionnaires;
    }

    public void setPendingZoneLevelQuestionnaires(List<String> pendingZoneLevelQuestionnaires) {
        this.pendingZoneLevelQuestionnaires = pendingZoneLevelQuestionnaires;
    }

    public List<String> getCompletedWealthGroupQuestionnaires() {
        return completedWealthGroupQuestionnaires;
    }

    public void setCompletedWealthGroupQuestionnaires(List<String> completedWealthGroupQuestionnaires) {
        this.completedWealthGroupQuestionnaires = completedWealthGroupQuestionnaires;
    }

    public List<String> getPendingWealthGroupQuestionnaires() {
        return pendingWealthGroupQuestionnaires;
    }

    public void setPendingWealthGroupQuestionnaires(List<String> pendingWealthGroupQuestionnaires) {
        this.pendingWealthGroupQuestionnaires = pendingWealthGroupQuestionnaires;
    }
}
