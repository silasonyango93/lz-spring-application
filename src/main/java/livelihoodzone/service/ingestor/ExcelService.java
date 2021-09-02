package livelihoodzone.service.ingestor;

import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.administrative_boundaries.subcounties.SubCountyEntity;
import livelihoodzone.entity.administrative_boundaries.sublocation.SubLocationEntity;
import livelihoodzone.entity.administrative_boundaries.ward.WardEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.LivelihoodZonesEntity;
import livelihoodzone.entity.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentEntity;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.repository.administrative_boundaries.countries.CountriesRepository;
import livelihoodzone.repository.administrative_boundaries.subcounties.SubCountiesRepository;
import livelihoodzone.repository.administrative_boundaries.sublocation.SubLocationRepository;
import livelihoodzone.repository.administrative_boundaries.wards.WardsRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.CountyLivelihoodZonesAssignmentRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.LivelihoodZonesRepository;
import livelihoodzone.repository.questionnaire.livelihoodzones.SubLocationsLivelihoodZoneAssignmentRepository;
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

    @Autowired
    LivelihoodZonesRepository livelihoodZonesRepository;

    @Autowired
    SubLocationsLivelihoodZoneAssignmentRepository subLocationsLivelihoodZoneAssignmentRepository;

    @Autowired
    CountyLivelihoodZonesAssignmentRepository countyLivelihoodZonesAssignmentRepository;

    public void save(MultipartFile file) {
        try {
            System.out.println();
            List<IngestedFileModel> ingestedRows = ExcelHelper.excelToTutorials(file.getInputStream());

//            List<SubLocationEntity> sublocations = ingestSubLocations(ingestedRows);
//
//            subLocationRepository.saveAll(sublocations);


//            List<CountiesEntity> counties = ingestCounties(ingestedRows);
//            countiesRepository.saveAll(counties);


            assignCountiesLivelihoodZones(ingestedRows);

            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


    public void assignCountiesLivelihoodZones(List<IngestedFileModel> rows) {
        List<CountyLivelihoodZonesAssignmentEntity> countyLivelihoodZonesAssignmentEntityList = new ArrayList<>();
        for (IngestedFileModel currentRow : rows) {
            if (currentRow.getLivelihoodZone() == null || currentRow.getLivelihoodZone() == "") {
                currentRow.setLivelihoodZone("Protected Areas");
            }
        }

        List<IngestedFileModel> specificZoneRows = extractRowsUnderSpecificLivelihoodZone("Mixed Farming: Dairy", rows);
        List<CountiesEntity> zoneCounties = extractActualCountiesFromZoneRows(specificZoneRows);

        for (CountiesEntity currentCounty : zoneCounties) {
            countyLivelihoodZonesAssignmentEntityList.add(new CountyLivelihoodZonesAssignmentEntity(
                    currentCounty.getCountyId(),
                    29,
                    1
            ));
        }

        //System.out.println();
        countyLivelihoodZonesAssignmentRepository.saveAll(countyLivelihoodZonesAssignmentEntityList);
    }


    public void assignSubLocationsLivelihoodZones(List<IngestedFileModel> rows) {
        List<SubLocationsLivelihoodZoneAssignmentEntity> subLocationsLivelihoodZoneAssignmentEntityList = new ArrayList<>();
        for (IngestedFileModel currentRow : rows) {
            if (currentRow.getLivelihoodZone() == null || currentRow.getLivelihoodZone() == "") {
                currentRow.setLivelihoodZone("Protected Areas");
            }
        }

        List<IngestedFileModel> specificZoneRows = extractRowsUnderSpecificLivelihoodZone("Mixed Farming: Dairy", rows);
        List<SubLocationEntity> zoneSubLocations = extractActualSublocationsFromZoneRows(specificZoneRows);

        for (SubLocationEntity currentSubLocation : zoneSubLocations) {
            subLocationsLivelihoodZoneAssignmentEntityList.add(new SubLocationsLivelihoodZoneAssignmentEntity(
                    currentSubLocation.getSubLocationId(),
                    29
            ));
        }
        //System.out.println();
        subLocationsLivelihoodZoneAssignmentRepository.saveAll(subLocationsLivelihoodZoneAssignmentEntityList);
    }


    public List<SubLocationEntity> extractActualSublocationsFromZoneRows(List<IngestedFileModel> zoneRows) {

        List<SubLocationEntity> allSubLocations = subLocationRepository.findAll();
        List<SubLocationEntity> zoneSubLocations = new ArrayList<>();

        for (IngestedFileModel currentRow : zoneRows) {
            for (SubLocationEntity currentSubLocation : allSubLocations) {
                if (currentRow.getSubLocation().equals(currentSubLocation.getSubLocationName())) {
                    if (!hasSubLocationBeenAddedToZoneList(zoneSubLocations, currentSubLocation)) {
                        zoneSubLocations.add(currentSubLocation);
                    }
                }
            }
        }

        return zoneSubLocations;
    }



    public List<CountiesEntity> extractActualCountiesFromZoneRows(List<IngestedFileModel> zoneRows) {
        List<CountiesEntity> allCounties = countiesRepository.findAll();
        List<CountiesEntity> zoneCounties = new ArrayList<>();

        for (IngestedFileModel currentRow : zoneRows) {
            for (CountiesEntity currentCounty : allCounties) {
                if (currentRow.getCounty().equals(currentCounty.getCountyName())) {
                    if (!hasCountyBeenAddedToZoneList(zoneCounties, currentCounty)) {
                        zoneCounties.add(currentCounty);
                    }
                }
            }
        }
        return zoneCounties;
    }


    public boolean hasSubLocationBeenAddedToZoneList(List<SubLocationEntity> zoneSubLocations, SubLocationEntity currentSubLocation) {
        List<SubLocationEntity> filteredRows = zoneSubLocations
                .stream()
                .filter(c -> c.getSubLocationId() == currentSubLocation.getSubLocationId())
                .collect(Collectors.toList());
        return filteredRows.size() > 0;
    }


    public boolean hasCountyBeenAddedToZoneList(List<CountiesEntity> zoneCounties, CountiesEntity currentCounty) {
        List<CountiesEntity> filteredRows = zoneCounties
                .stream()
                .filter(c -> c.getCountyId() == currentCounty.getCountyId())
                .collect(Collectors.toList());
        return filteredRows.size() > 0;
    }

    public List<IngestedFileModel> extractRowsUnderSpecificLivelihoodZone(String zoneName, List<IngestedFileModel> ingestedRows) {


        List<IngestedFileModel> filteredRows = ingestedRows
                .stream()
                .filter(c -> c.getLivelihoodZone().equals(zoneName))
                .collect(Collectors.toList());
        return filteredRows;

    }


    public void ingestLivelihoodZones(List<IngestedFileModel> rows) {
        List<String> alreadyRecordedLivelihoodZones = new ArrayList<>();
        List<LivelihoodZonesEntity> livelihoodZonesEntityList = new ArrayList<>();


        for (IngestedFileModel currentRow : rows) {
            if (currentRow.getLivelihoodZone() == null || currentRow.getLivelihoodZone() == "") {
                currentRow.setLivelihoodZone("Protected Areas");
            }
            if (!isRecordAlredyCaptured(alreadyRecordedLivelihoodZones, currentRow.getLivelihoodZone())) {
                livelihoodZonesEntityList.add(new LivelihoodZonesEntity(
                        currentRow.getLivelihoodZone(),
                        0
                ));
                alreadyRecordedLivelihoodZones.add(currentRow.getLivelihoodZone());
            }

        }

        livelihoodZonesRepository.saveAll(livelihoodZonesEntityList);


        System.out.println();

    }

    public List<CountiesEntity> ingestCounties(List<IngestedFileModel> rows) {
        List<String> alreadyRecordedCounties = new ArrayList<>();
        List<CountiesEntity> counties = new ArrayList<>();

        for (IngestedFileModel currentRow : rows) {

            if (!isRecordAlredyCaptured(alreadyRecordedCounties, currentRow.getCounty())) {
                counties.add(new CountiesEntity(
                        currentRow.getCounty(),
                        ""
                ));
                alreadyRecordedCounties.add(currentRow.getCounty());
            }
        }
        return counties;
    }

    public void ingestSubCounties(List<IngestedFileModel> ingestedRows) {

        List<CountiesEntity> counties = countiesRepository.findAll();

        for (CountiesEntity currentCounty : counties) {

            List<IngestedFileModel> specificCountyRows = filterOutACountySubCounties(currentCounty.getCountyName(), ingestedRows);

            List<SubCountyEntity> subCounties = new ArrayList<>();
            List<String> alreadyRecordedSubCounties = new ArrayList<>();


            for (IngestedFileModel currentRow : specificCountyRows) {
                if (!isRecordAlredyCaptured(alreadyRecordedSubCounties, currentRow.getSubCounty())) {
                    subCounties.add(new SubCountyEntity(
                            currentCounty.getCountyId(),
                            currentRow.getSubCounty(),
                            0
                    ));
                    alreadyRecordedSubCounties.add(currentRow.getSubCounty());
                }
            }

            subCountiesRepository.saveAll(subCounties);

        }

    }

    public void ingestWards(List<IngestedFileModel> ingestedRows) {

        List<SubCountyEntity> subCounties = subCountiesRepository.findAll();

        for (SubCountyEntity currentSubCounty : subCounties) {

            List<IngestedFileModel> specificSubCountyRows = filterOutASubCountyWards(currentSubCounty.getSubCountyName(), ingestedRows);
            List<WardEntity> wards = new ArrayList<>();
            List<String> alreadyRecordedWards = new ArrayList<>();

            for (IngestedFileModel currentRow : specificSubCountyRows) {
                if (!isRecordAlredyCaptured(alreadyRecordedWards, currentRow.getWard())) {
                    wards.add(new WardEntity(
                            currentSubCounty.getSubCountyId(),
                            currentRow.getWard(),
                            0
                    ));
                    alreadyRecordedWards.add(currentRow.getWard());
                }
            }

            wardsRepository.saveAll(wards);

        }

    }


    public void ingestSubLocations(List<IngestedFileModel> ingestedRows) {
        List<WardEntity> wards = wardsRepository.findAll();

        for (WardEntity currentWard : wards) {

            List<IngestedFileModel> specificWardRows = filterOutAWardSubLocations(currentWard.getWardName(), ingestedRows);
            List<SubLocationEntity> subLocations = new ArrayList<>();
            List<String> alreadyRecordedSubLocations = new ArrayList<>();

            for (IngestedFileModel currentRow : specificWardRows) {
                if (!isRecordAlredyCaptured(alreadyRecordedSubLocations, currentRow.getSubLocation())) {
                    subLocations.add(new SubLocationEntity(
                            currentWard.getWardId(),
                            currentRow.getSubLocation(),
                            0
                    ));
                    alreadyRecordedSubLocations.add(currentRow.getSubLocation());
                }
            }

            subLocationRepository.saveAll(subLocations);

        }
    }


    public boolean isRecordAlredyCaptured(List<String> recordedItems, String currentString) {
        List<String> duplicates = recordedItems
                .stream()
                .filter(c -> c.replaceAll("\\s+", "").equals(currentString.replaceAll("\\s+", "")))
                .collect(Collectors.toList());

        return duplicates.size() > 0;
    }

    public List<IngestedFileModel> filterOutACountySubCounties(String countyName, List<IngestedFileModel> ingestedRows) {
        List<IngestedFileModel> rows = ingestedRows
                .stream()
                .filter(c -> c.getCounty().equals(countyName))
                .collect(Collectors.toList());
        return rows;
    }

    public List<IngestedFileModel> filterOutASubCountyWards(String subCountyName, List<IngestedFileModel> ingestedRows) {
        List<IngestedFileModel> rows = ingestedRows
                .stream()
                .filter(c -> c.getSubCounty().equals(subCountyName))
                .collect(Collectors.toList());
        return rows;
    }


    public List<IngestedFileModel> filterOutAWardSubLocations(String wardName, List<IngestedFileModel> ingestedRows) {
        List<IngestedFileModel> rows = ingestedRows
                .stream()
                .filter(c -> c.getWard().equals(wardName))
                .collect(Collectors.toList());
        return rows;
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
