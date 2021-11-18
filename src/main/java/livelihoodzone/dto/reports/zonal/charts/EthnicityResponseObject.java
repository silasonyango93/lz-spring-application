package livelihoodzone.dto.reports.zonal.charts;

import livelihoodzone.dto.questionnaire.county.model.ethnicgroup.EthnicityResponseItem;

import java.util.List;

public class EthnicityResponseObject {
    private List<EthnicityResponseItem> ethnicGroupResponseList;

    public List<EthnicityResponseItem> getEthnicGroupResponseList() {
        return ethnicGroupResponseList;
    }

    public void setEthnicGroupResponseList(List<EthnicityResponseItem> ethnicGroupResponseList) {
        this.ethnicGroupResponseList = ethnicGroupResponseList;
    }
}
