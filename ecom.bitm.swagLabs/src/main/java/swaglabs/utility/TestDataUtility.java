package swaglabs.utility;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataUtility {

    public static String getData(String testCaseId, int columnNumber) {

        try {

            InputStream inputStream =
                    TestDataUtility.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "SauceDemo_TestData.xlsx"
                            );

            if (inputStream == null) {
                throw new RuntimeException(
                        "SauceDemo_TestData.xlsx not found in src/test/resources"
                );
            }

            Workbook workbook =
                    new XSSFWorkbook(inputStream);

            Sheet sheet =
                    workbook.getSheet("TestData");

            if (sheet == null) {
                workbook.close();
                inputStream.close();

                throw new RuntimeException(
                        "Sheet 'TestData' not found in SauceDemo_TestData.xlsx"
                );
            }

            DataFormatter formatter =
                    new DataFormatter();

            for (Row row : sheet) {

                String id =
                        formatter.formatCellValue(
                                row.getCell(0)
                        );

                if (id.equalsIgnoreCase(testCaseId)) {

                    String value =
                            formatter.formatCellValue(
                                    row.getCell(columnNumber)
                            );

                    workbook.close();
                    inputStream.close();

                    return value;
                }
            }

            workbook.close();
            inputStream.close();

            throw new RuntimeException(
                    "Test Case ID not found: " + testCaseId
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read test data from Excel",
                    e
            );
        }
    }
}