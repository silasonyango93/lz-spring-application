package livelihoodzone.service.reports.zonal;

import livelihoodzone.service.reports.zonal.wealthgroup.WealthGroupReportsService;
import livelihoodzone.service.retrofit.reports.zonelevel.WealthGroupCharectaristicsRetrofitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneLevelReportService {

    @Autowired
    WealthGroupReportsService wealthGroupReportsService;

    public List<WealthGroupCharectaristicsRetrofitModel> comprehensivelyFetchWealthGroupCharacteristicsReport() {
        return wealthGroupReportsService.fetchWealthGroupCharacteristicsReportsComprehensively();
    }
}
