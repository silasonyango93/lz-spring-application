package livelihoodzone.common;

import livelihoodzone.util.excel.IngestedFileModel;

import java.util.ArrayList;
import java.util.List;

public class CustomRunTimeStore {
    private static final CustomRunTimeStore customRunTimeStore = new CustomRunTimeStore();

    private List<IngestedFileModel> ingestedRows = new ArrayList<>();

    public static CustomRunTimeStore getInstance() {
        return customRunTimeStore;
    }

    public List<IngestedFileModel> getIngestedRows() {
        return ingestedRows;
    }

    public void setIngestedRows(List<IngestedFileModel> ingestedRows) {
        this.ingestedRows = ingestedRows;
    }

    public void addARow(IngestedFileModel item) {
        ingestedRows.add(item);
    }
}
