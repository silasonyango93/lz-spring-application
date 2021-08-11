package livelihoodzone.service.ingestor;

import livelihoodzone.common.CustomRunTimeStore;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.administrative_boundaries.sublocation.SubLocationRepository;
import livelihoodzone.repository.administrative_boundaries.wards.WardsRepository;
import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.IngestedFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelService {

    @Autowired
    SubCountiesRepository subCountiesRepository;

    @Autowired
    WardsRepository wardsRepository;

    @Autowired
    SubLocationRepository subLocationRepository;

    @Autowired
    CountiesRepository countiesRepository;

    public void save(MultipartFile file) {
        try {
            System.out.println();
            List<IngestedFileModel> ingestedRows = ExcelHelper.excelToTutorials(file.getInputStream());

            List<IngestedFileModel> rows = CustomRunTimeStore.getInstance().getIngestedRows();

            System.out.println();

            if (!rows.isEmpty()) {
                saveCounties(rows);
            }





        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

//    public List<SubCountyEntity> ingestSubCounties(List<IngestedFileModel> ingestedRows) {
//
//        List<String> alreadyRecordedCounties = new ArrayList<>();
//
//        List<SubCountyEntity> subCounties = new ArrayList<>();
//        subCounties.add(new SubCountyEntity(
//                2,
//                ingestedRows.get(0).getCurrentConstituency(),
//                1
//        ));
//
//        alreadyRecordedCounties.add(ingestedRows.get(0).getCurrentConstituency());
//
//        for (IngestedFileModel currentRow : ingestedRows) {
//            if (!isRecordAlredyCaptured(alreadyRecordedCounties, currentRow.getCurrentConstituency())) {
//                subCounties.add(new SubCountyEntity(
//                        1,
//                        currentRow.getCurrentConstituency(),
//                        1
//                ));
//                alreadyRecordedCounties.add(currentRow.getCurrentConstituency());
//            }
//        }
//
//        return subCounties;
//
//    }


    public boolean isRecordAlredyCaptured(List<String> recordedItems, String currentString) {
        List<String> duplicates = recordedItems
                .stream()
                .filter(c -> c.replaceAll("\\s+","").equals(currentString))
                .collect(Collectors.toList());

        return duplicates.size() > 0;
    }


//    public List<WardEntity> ingestWards(List<IngestedFileModel> ingestedRows) {
//
//        List<SubCountyEntity> savedSubCounties = subCountiesRepository.findAll();
//
//        List<WardEntity> wardsList = new ArrayList<>();
//        List<String> alreadyRecordedwards = new ArrayList<>();
//
//        for (SubCountyEntity currentSubCounty : savedSubCounties) {
//
//            for (IngestedFileModel currentRow : ingestedRows) {
//                String savedSubCountyName = currentSubCounty.getSubCountyName().replaceAll("\\s+","");
//                String excellSubCountyName = currentRow.getCurrentConstituency().replaceAll("\\s+","");
//                if (savedSubCountyName.equals(excellSubCountyName)) {
//                    if (!isRecordAlredyCaptured(alreadyRecordedwards, currentRow.getCurrentWard().replaceAll("\\s+",""))) {
//                        wardsList.add(new WardEntity(
//                                currentSubCounty.getSubCountyId(),
//                                currentRow.getCurrentWard(),
//                                0
//                        ));
//                        alreadyRecordedwards.add(currentRow.getCurrentWard());
//                    }
//                }
//            }
//
//        }
//
//        return wardsList;
//
//    }


    public void saveCounties(List<IngestedFileModel> ingestedRows) {
        String currentCountyName = ingestedRows.get(0).getCounty();
        countiesRepository.save(new CountiesEntity(
                currentCountyName,
                ""
        ));

        for (IngestedFileModel currentRow : ingestedRows) {
            if (!currentRow.getCounty().equals(currentCountyName)) {
                countiesRepository.save(new CountiesEntity(
                        currentRow.getCounty(),
                        ""
                ));
                currentCountyName = currentRow.getCounty();
            }
        }
    }


//    public List<SubLocationEntity> ingestSubLocations(List<IngestedFileModel> ingestedRows) {
//
//        List<WardEntity> ingestedWards = wardsRepository.findAll();
//
//        List<SubLocationEntity> sublocations = new ArrayList<>();
//
//        List<String> alreadyRecordedSubLocations = new ArrayList<>();
//
//        for (WardEntity currentWard  : ingestedWards) {
//
//            for (IngestedFileModel currentRow : ingestedRows) {
//
//                String savedWardName = currentWard.getWardName().replaceAll("\\s+","");
//                String excelWardName = currentRow.getCurrentWard().replaceAll("\\s+","");
//
//                if (savedWardName.equals(excelWardName)) {
//
//                    if (!isRecordAlredyCaptured(alreadyRecordedSubLocations, currentRow.getOldSubLocation().replaceAll("\\s+",""))) {
//                        sublocations.add(new SubLocationEntity(
//                                currentWard.getWardId(),
//                                currentRow.getOldSubLocation(),
//                                0
//                        ));
//                        alreadyRecordedSubLocations.add(currentRow.getCurrentWard());
//                    }
//
//                }
//
//            }
//
//        }
//
//        return sublocations;
//    }
}
