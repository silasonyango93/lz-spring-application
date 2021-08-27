package livelihoodzone.util.excel.users;

import livelihoodzone.util.excel.IngestedFileModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsersExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "County", "Name", "Mobile phone number", "Sector/Department", "Email" };
    static String SHEET = "ASALs";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<UsersExcelModel> excelToTutorials(InputStream is) {
        try {
            System.out.println();
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<UsersExcelModel> tutorials = new ArrayList<UsersExcelModel>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                UsersExcelModel tutorial = new UsersExcelModel();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            tutorial.setCountyName(currentCell.getStringCellValue());
                            break;

                        case 1:
                            tutorial.setStaffName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            //tutorial.setTelephoneNo((int)currentCell.getNumericCellValue());
                            break;

                        case 3:
                            tutorial.setOrganizationName(currentCell.getStringCellValue());
                            break;

                        case 4:
                            tutorial.setEmail(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                tutorials.add(tutorial);
            }

            workbook.close();

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
