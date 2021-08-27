package livelihoodzone.service.ingestor.users;

import livelihoodzone.dto.user_management.RoleAssignmentDto;
import livelihoodzone.entity.administrative_boundaries.counties.CountiesEntity;
import livelihoodzone.entity.user_management.User;
import livelihoodzone.entity.user_management.UserRoles;
import livelihoodzone.repository.administrative_boundaries.counties.CountiesRepository;
import livelihoodzone.service.user_management.UserService;
import livelihoodzone.util.excel.ExcelHelper;
import livelihoodzone.util.excel.IngestedFileModel;
import livelihoodzone.util.excel.users.UsersExcelHelper;
import livelihoodzone.util.excel.users.UsersExcelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersExcelService {

    @Autowired
    UserService userService;

    @Autowired
    CountiesRepository countiesRepository;

    public void signUpIngestedUsers(MultipartFile file) {
        try {
            System.out.println();
            List<UsersExcelModel> ingestedRows = UsersExcelHelper.excelToTutorials(file.getInputStream());

            List<User> usersToBeIngested = new ArrayList<>();

            List<RoleAssignmentDto> rolesToBeAssigned = new ArrayList<>();

            rolesToBeAssigned.add(new RoleAssignmentDto(
                    1,
                    false
            ));
            rolesToBeAssigned.add(new RoleAssignmentDto(
                    2,
                    true
            ));
            rolesToBeAssigned.add(new RoleAssignmentDto(
                    3,
                    false
            ));

            for (UsersExcelModel currentUser : ingestedRows) {
                if (currentUser != null) {
                    if (currentUser.getStaffName() != null) {
                        String[] splitNames = currentUser.getStaffName().split("\\s+");
                        User user = new User();
                        user.setFirstName(splitNames[0]);
                        if (splitNames.length > 1) {
                            user.setSurname(splitNames[1]);
                        } else {
                            user.setSurname(splitNames[0]);
                        }
                        user.setCountyId(getAUserCountyId(currentUser.getCountyName()));
                        user.setUserEmail(currentUser.getEmail());
                        user.setOrganizationName(currentUser.getOrganizationName());
                        user.setUserPhoneNumber(String.valueOf(currentUser.getTelephoneNo()));
                        user.setEncryptedPassword("lz@2021");
                        usersToBeIngested.add(user);
                    }
                }
            }

            for (User currentItem : usersToBeIngested) {
                userService.signup(currentItem,rolesToBeAssigned);
            }

            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public int getAUserCountyId(String countyName) {
        List<CountiesEntity> allCounties = countiesRepository.findAll();
        List<CountiesEntity> filteredCounties = allCounties
                .stream()
                .filter(c -> c.getCountyName().equals(countyName))
                .collect(Collectors.toList());
        return filteredCounties.get(0).getCountyId();
    }
}
