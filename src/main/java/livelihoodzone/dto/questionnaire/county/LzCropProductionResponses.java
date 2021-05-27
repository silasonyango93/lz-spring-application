package livelihoodzone.dto.questionnaire.county;

import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;

import java.util.List;

public class LzCropProductionResponses {
    private List<WgCropProductionResponseItem> cropProductionResponses;

    public List<WgCropProductionResponseItem> getCropProductionResponses() {
        return cropProductionResponses;
    }

    public void setCropProductionResponses(List<WgCropProductionResponseItem> cropProductionResponses) {
        this.cropProductionResponses = cropProductionResponses;
    }
}
