package livelihoodzone.dto.reports.wealthgroup;

import java.util.List;

public class WgCropContributionReportResponseObject {
    private List<String> cropNameList;
    private List<String> cashIncomeRankList;
    private List<String> cashIncomeApproxPercentageList;
    private List<String> foodConsumptionRankList;
    private List<String> foodConsumptionApproxPercentageList;

    public WgCropContributionReportResponseObject() {
    }

    public WgCropContributionReportResponseObject(List<String> cropNameList, List<String> cashIncomeRankList, List<String> cashIncomeApproxPercentageList, List<String> foodConsumptionRankList, List<String> foodConsumptionApproxPercentageList) {
        this.cropNameList = cropNameList;
        this.cashIncomeRankList = cashIncomeRankList;
        this.cashIncomeApproxPercentageList = cashIncomeApproxPercentageList;
        this.foodConsumptionRankList = foodConsumptionRankList;
        this.foodConsumptionApproxPercentageList = foodConsumptionApproxPercentageList;
    }

    public List<String> getCropNameList() {
        return cropNameList;
    }

    public void setCropNameList(List<String> cropNameList) {
        this.cropNameList = cropNameList;
    }

    public List<String> getCashIncomeRankList() {
        return cashIncomeRankList;
    }

    public void setCashIncomeRankList(List<String> cashIncomeRankList) {
        this.cashIncomeRankList = cashIncomeRankList;
    }

    public List<String> getCashIncomeApproxPercentageList() {
        return cashIncomeApproxPercentageList;
    }

    public void setCashIncomeApproxPercentageList(List<String> cashIncomeApproxPercentageList) {
        this.cashIncomeApproxPercentageList = cashIncomeApproxPercentageList;
    }

    public List<String> getFoodConsumptionRankList() {
        return foodConsumptionRankList;
    }

    public void setFoodConsumptionRankList(List<String> foodConsumptionRankList) {
        this.foodConsumptionRankList = foodConsumptionRankList;
    }

    public List<String> getFoodConsumptionApproxPercentageList() {
        return foodConsumptionApproxPercentageList;
    }

    public void setFoodConsumptionApproxPercentageList(List<String> foodConsumptionApproxPercentageList) {
        this.foodConsumptionApproxPercentageList = foodConsumptionApproxPercentageList;
    }
}
