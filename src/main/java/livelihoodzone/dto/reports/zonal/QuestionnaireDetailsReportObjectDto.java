package livelihoodzone.dto.reports.zonal;

import livelihoodzone.service.retrofit.reports.zonelevel.QuestionnaireDetailsRetrofitModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDetailsReportObjectDto {
    private List<QuestionnaireDetailsRetrofitModel> questionnaireDetailsRetrofitModelList = new ArrayList<>();

    public QuestionnaireDetailsReportObjectDto() {
    }

    public QuestionnaireDetailsReportObjectDto(List<QuestionnaireDetailsRetrofitModel> questionnaireDetailsRetrofitModelList) {
        this.questionnaireDetailsRetrofitModelList = questionnaireDetailsRetrofitModelList;
    }

    public List<QuestionnaireDetailsRetrofitModel> getQuestionnaireDetailsRetrofitModelList() {
        return questionnaireDetailsRetrofitModelList;
    }

    public void setQuestionnaireDetailsRetrofitModelList(List<QuestionnaireDetailsRetrofitModel> questionnaireDetailsRetrofitModelList) {
        this.questionnaireDetailsRetrofitModelList = questionnaireDetailsRetrofitModelList;
    }
}
