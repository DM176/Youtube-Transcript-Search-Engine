package com.example.transcriptengine.transcriptengine.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import com.example.transcriptengine.transcriptengine.service.DataService;

@Configuration
public class AppConfig {

    private static final Logger logger = Logger.getLogger(AppConfig.class.getName());

    @Autowired
    DataService dataService;

    @Bean
    public Map<Integer, ClickRequest> urlData() {
        try {
            return dataService.readFromFile();
        } catch (IOException e) {
            // Handle the exception accordingly
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public  Map<String, Integer> urlMap() {
        Map<Integer, ClickRequest> urlDataFile = null;
        Map<String, Integer> urlCount = new HashMap<>(); // Track URL counts

        try {
            urlDataFile = dataService.readFromFile();
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Error reading from file", ioException);
            return null;
        }
        int rowNumber =1;

        for (Map.Entry<Integer, ClickRequest> entry : urlDataFile.entrySet()) {
            ClickRequest rowData = entry.getValue();

            String urlTimeStamp = rowData.getLinkUrl();

            urlCount.put(urlTimeStamp, rowNumber);
            rowNumber++;
        }

        return urlCount;

    }



}