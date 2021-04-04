package livelihoodzone.util.excel;

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

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "CODE", "COUNTY", "OLD DIVISION", "OLD LOCATION", "OLD SUB LOCATION", "CURRENT CONSTITUENCY", "CURRENT WARD" };
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<IngestedFileModel> excelToTutorials(InputStream is) {
        try {
            System.out.println();
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<IngestedFileModel> tutorials = new ArrayList<IngestedFileModel>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                IngestedFileModel tutorial = new IngestedFileModel();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            tutorial.setCode((double) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            tutorial.setCounty(currentCell.getStringCellValue());
                            break;

                        case 2:
                            tutorial.setOldDivision(currentCell.getStringCellValue());
                            break;

                        case 3:
                            tutorial.setOldLocation(currentCell.getStringCellValue());
                            break;

                        case 4:
                            tutorial.setOldSubLocation(currentCell.getStringCellValue());
                            break;


                        case 5:
                            tutorial.setCurrentConstituency(currentCell.getStringCellValue());
                            break;


                        case 6:
                            tutorial.setCurrentWard(currentCell.getStringCellValue());
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
