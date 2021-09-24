package livelihoodzone.dto.reports.wealthgroup.charts;

import livelihoodzone.dto.questionnaire.wealthgroup.cropcontribution.WgCropContributionResponseItem;

import java.util.List;

public class WgCropContributionChartObject {
    private List<WgCropContributionResponseItem> cropContributionResponseItems;

    public List<WgCropContributionResponseItem> getCropContributionResponseItems() {
        return cropContributionResponseItems;
    }

    public void setCropContributionResponseItems(List<WgCropContributionResponseItem> cropContributionResponseItems) {
        this.cropContributionResponseItems = cropContributionResponseItems;
    }
}
