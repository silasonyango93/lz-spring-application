package livelihoodzone.dto.reports.wealthgroup;

import livelihoodzone.service.retrofit.reports.wealthgroup.WgQuestionnaireDetailsRetrofitModel;

import java.util.List;

public class WgQuestionnaireDetailsResponseObjectDto {
    List<WgQuestionnaireDetailsRetrofitModel> wgQuestionnaireDetailsRetrofitModelList;

    public WgQuestionnaireDetailsResponseObjectDto() {
    }

    public WgQuestionnaireDetailsResponseObjectDto(List<WgQuestionnaireDetailsRetrofitModel> wgQuestionnaireDetailsRetrofitModelList) {
        this.wgQuestionnaireDetailsRetrofitModelList = wgQuestionnaireDetailsRetrofitModelList;
    }

    public List<WgQuestionnaireDetailsRetrofitModel> getWgQuestionnaireDetailsRetrofitModelList() {
        return wgQuestionnaireDetailsRetrofitModelList;
    }

    public void setWgQuestionnaireDetailsRetrofitModelList(List<WgQuestionnaireDetailsRetrofitModel> wgQuestionnaireDetailsRetrofitModelList) {
        this.wgQuestionnaireDetailsRetrofitModelList = wgQuestionnaireDetailsRetrofitModelList;
    }
}
