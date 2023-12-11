package com.example.transcriptengine.transcriptengine.service;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    public Map<Integer, List<String>>  readFromFile() throws IOException {
        FileInputStream file = new FileInputStream(
                new File("C:\\Users\\HP\\Documents\\Projects\\J-Class\\Transcription Engine\\transcriptengine\\Transcript.xls"));

        // Change from XSSFWorkbook to HSSFWorkbook
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i).add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        data.get(i).add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        data.get(i).add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case FORMULA:
                        data.get(i).add(cell.getCellFormula());
                        break;
                    default:
                        data.get(i).add(" ");
                }

            }
            i++;
        }
//        printData(data);
        return data;
    }

    public void printData(Map<Integer, List<String>> data){
        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            int rowNumber = entry.getKey();
            List<String> rowData = entry.getValue();

            System.out.println("Row " + rowNumber + ":");
            for (String cellData : rowData) {
                System.out.print(cellData + "\t");
            }
            System.out.println(); // Move to the next line for the next row
        }

    }
}
