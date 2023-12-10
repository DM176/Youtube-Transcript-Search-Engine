package com.example.transcriptengine.transcriptengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FrequencyBasedSearchService implements SearchAlgoInterface {

    @Autowired
    DataService dataService;
    private static final Logger logger = Logger.getLogger(FrequencyBasedSearchService.class.getName());


    @Override
    public List<List<String>> searchVideo(String query) {
        Map<Integer, List<String>> urlData = null;
        Map<String, Integer> urlCount = new HashMap<>(); // Track URL counts

        List<List<String>> resultRows = new ArrayList<>();
        try {
            urlData = dataService.readFromFile();
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Error reading from file", ioException);
            return resultRows;
        }

        for (Map.Entry<Integer, List<String>> entry : urlData.entrySet()) {
            List<String> rowData = entry.getValue();
            String largeText = rowData.get(2);

            Boolean result = largeText.contains(query);

            if (result==true) {
                String url = rowData.get(0); // URL is in the first index of the row
                if (!urlCount.containsKey(url)) {
                    resultRows.add(rowData);
                }


                // Update URL count
                urlCount.put(url, urlCount.getOrDefault(url, 0) + 1);
            }
        }

        // Sort resultRows based on the counts of URLs using the urlCount map
            resultRows.sort((row1, row2) -> {
            String url1 = row1.get(0); // Assuming URL is in the first index of the row
            String url2 = row2.get(0);

            return Integer.compare(urlCount.getOrDefault(url2, 0), urlCount.getOrDefault(url1, 0));
        });

        return resultRows;
    }

}
