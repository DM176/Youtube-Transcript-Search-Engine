package com.example.transcriptengine.transcriptengine.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;

@Service
public class DataService {

    public Map<Integer, ClickRequest> readFromFile() throws IOException {
        FileInputStream file = new FileInputStream(
                new File("C:\\Users\\HP\\Documents\\Projects\\J-Class\\Transcription Engine\\transcriptengine\\Transcript.xls"));

        // Change from XSSFWorkbook to HSSFWorkbook
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, ClickRequest> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            if (i == 0) {
                // Skip header row
                i++;
                continue;
            }

            ClickRequest clickRequest = new ClickRequest();
            clickRequest.setLinkUrl(row.getCell(0).getStringCellValue());
            clickRequest.settimeStampLink(row.getCell(1).getStringCellValue()); // Assuming linkUrl is the second column
            clickRequest.setTranscriptData(row.getCell(2).getStringCellValue()); // Assuming linkUrl is the second column
            clickRequest.setNumberOfClicks((int) row.getCell(3).getNumericCellValue()); // Assuming numberOfClicks is the fourth column
            clickRequest.setThumbsUpCount((int) row.getCell(4).getNumericCellValue()); // Assuming thumbsUpCount is the fifth column
            clickRequest.setThumbsDownCount((int) row.getCell(5).getNumericCellValue()); // Assuming thumbsDownCount is the sixth column

            data.put(i, clickRequest);
            i++;
        }
        return data;
    }
    public void writeToSpecificRow(ClickRequest clickRequest, int rowNum) throws IOException {
        FileInputStream file = new FileInputStream(
                new File("C:\\Users\\HP\\Documents\\Projects\\J-Class\\Transcription Engine\\transcriptengine\\Transcript.xls"));

        // Load the existing workbook
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        row.createCell(0).setCellValue(clickRequest.getLinkUrl());
        row.createCell(1).setCellValue(clickRequest.gettimeStampLink());
        row.createCell(2).setCellValue(clickRequest.getTranscriptData());
        row.createCell(3).setCellValue(clickRequest.getNumberOfClicks());
        row.createCell(4).setCellValue(clickRequest.getThumbsUpCount());
        row.createCell(5).setCellValue(clickRequest.getThumbsDownCount());

        file.close();

        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\HP\\Documents\\Projects\\J-Class\\Transcription Engine\\transcriptengine\\Transcript.xls");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
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
