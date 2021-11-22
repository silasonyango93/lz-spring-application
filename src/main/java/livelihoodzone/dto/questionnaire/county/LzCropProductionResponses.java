package livelihoodzone.dto.questionnaire.county;

import livelihoodzone.dto.questionnaire.county.model.cropproduction.WgCropProductionResponseItem;

import java.util.ArrayList;
import java.util.List;

public class LzCropProductionResponses {
    private List<WgCropProductionResponseItem> cropProductionResponses;

    public LzCropProductionResponses() {
    }

    public LzCropProductionResponses(boolean instantiate) {
        if (instantiate) {
            cropProductionResponses = new ArrayList<>();
        }
    }

    public List<WgCropProductionResponseItem> getCropProductionResponses() {
        return cropProductionResponses;
    }

    public void setCropProductionResponses(List<WgCropProductionResponseItem> cropProductionResponses) {
        this.cropProductionResponses = cropProductionResponses;
    }
}
