package com.tigeranalytics.Customers.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.tigeranalytics.Customers.model.Storeinfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static String[] headers = {"stor id", "sku", "product name", "price", "date" };
    static String pattern = "dd/MM/yyyy";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Storeinfo> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Storeinfo> jobs = new ArrayList<Storeinfo>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Storeinfo job = new Storeinfo();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            if(Objects.isNull(currentCell.getNumericCellValue()) ||
                              currentCell.getNumericCellValue() == 0) {
                                throw new RuntimeException("serial number not found");
                            }
                            job.setStore_id((long)currentCell.getNumericCellValue());
                            break;
                        case 1:
                            if(Objects.isNull(currentCell.getNumericCellValue()) ||
                                    currentCell.getNumericCellValue() == 0) {
                                throw new RuntimeException("serial number not found");
                            }
                            job.setSku((long)currentCell.getNumericCellValue());
                            break;
                        case 2:
                            nullAndEmptyCheck(currentCell.getStringCellValue(), 2);
                            job.setProduct_name(currentCell.getStringCellValue());
                            break;
                        case 3:
                            if(Objects.isNull(currentCell.getNumericCellValue()) ||
                                    currentCell.getNumericCellValue() == 0) {
                                throw new RuntimeException("serial number not found");
                            }
                            job.setPrice((long)currentCell.getNumericCellValue());
                            break;
                        case 4:
                            nullAndEmptyCheck(currentCell.getStringCellValue(), 4);
                            try {
                                job.setProduct_date(simpleDateFormat.parse(currentCell.getStringCellValue()));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                jobs.add(job);
            }
            workbook.close();
            return jobs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static void nullAndEmptyCheck(String name, int index) {
        if(Objects.isNull(name) || name.isEmpty()) {
            throw new RuntimeException(headers[index]+" is empty, please enter valid value");
        }
    }
}
