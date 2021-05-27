package livelihoodzone.dto.questionnaire.county.model.ethnicgroup;

import livelihoodzone.entity.questionnaire.tribe.EthnicGroupsEntity;

public class EthnicityResponseItem {
    private EthnicGroupsEntity ethnicGroupModel;
    private double populationPercentage;

    public EthnicGroupsEntity getEthnicGroupModel() {
        return ethnicGroupModel;
    }

    public void setEthnicGroupModel(EthnicGroupsEntity ethnicGroupModel) {
        this.ethnicGroupModel = ethnicGroupModel;
    }

    public double getPopulationPercentage() {
        return populationPercentage;
    }

    public void setPopulationPercentage(double populationPercentage) {
        this.populationPercentage = populationPercentage;
    }
}
