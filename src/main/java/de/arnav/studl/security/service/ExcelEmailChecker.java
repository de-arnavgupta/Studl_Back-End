package de.arnav.studl.security.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelEmailChecker {

    public boolean isEmailInExcel(String emailToSearch,String EXCEL_FILE_PATH) {
        try (FileInputStream fis = new FileInputStream(new File(EXCEL_FILE_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // Assuming emails are in the first column (index 0)

                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String email = cell.getStringCellValue().trim();
                    if (email.equalsIgnoreCase(emailToSearch)) {
                        return true; // Email found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Email not found
    }
}

