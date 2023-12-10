package com.example.transcriptengine.transcriptengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SimpleSearchService implements SearchAlgoInterface{

    @Autowired
    DataService dataService;

    @Override
    public List<List<String>> searchVideo(String query) { //nucleus
        Map<Integer, List<String>> data = null;
        List<List<String>> resultRows = new ArrayList<>();
        Set<String > uniqueURLs = new HashSet<>(); // Store unique URLs
        Boolean found = false;

        try {
            data = dataService.readFromFile();
        } catch (IOException ioException) {
            // Handle the exception appropriately
        }
        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            List<String> rowData = entry.getValue();  //col1: youtube url, col2: youtube url with timestamp, col2:transcript
            String largeText = rowData.get(2);
            found = largeText.contains(query);

            if (found) {
                String url = rowData.get(0); // Assuming URL is in the first index of the row
                if (!uniqueURLs.contains(url)) {
                    resultRows.add(rowData);
                    uniqueURLs.add(url);
                }
            }
        }

        return resultRows;
    }


}
