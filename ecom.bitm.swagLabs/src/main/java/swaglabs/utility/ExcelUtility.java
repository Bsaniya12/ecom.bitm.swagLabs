package swaglabs.utility;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private Workbook workbook;
    private Sheet sheet;

    // Open Excel file
    public void openExcel(String fileName, String sheetName) {

        try {
            InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new RuntimeException(
                        "Excel file not found in src/main/resources: " + fileName
                );
            }

            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found in Excel file: " + sheetName
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Unable to open Excel file", e);
        }
    }

    // Get number of rows
    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    // Get number of columns
    public int getColumnCount(int rowNumber) {
        Row row = sheet.getRow(rowNumber);

        if (row == null) {
            return 0;
        }

        return row.getLastCellNum();
    }

    // Get cell data
    public String getCellData(int rowNumber, int columnNumber) {

        Row row = sheet.getRow(rowNumber);

        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(columnNumber);

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell);
    }

    // Close Excel file
    public void closeExcel() {

        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}