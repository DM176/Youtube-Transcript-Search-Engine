package com.example.transcriptengine.transcriptengine.service;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
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
    public List<ClickRequest> searchVideo(String query) {
        Map<Integer, ClickRequest> urlData = null;
        Map<String, Integer> urlCount = new HashMap<>(); // Track URL counts

        List<ClickRequest> resultRows = new ArrayList<>();
        try {
            urlData = dataService.readFromFile();
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Error reading from file", ioException);
            return resultRows;
        }

        for (Map.Entry<Integer, ClickRequest> entry : urlData.entrySet()) {
            ClickRequest rowData = entry.getValue();
            String largeText = rowData.getTranscriptData();

            Boolean result = largeText.contains(query);

            if (result==true) {
                String url = rowData.getLinkUrl(); // URL is in the first index of the row
                if (!urlCount.containsKey(url)) {
                    resultRows.add(rowData);
                }


                // Update URL count
                urlCount.put(url, urlCount.getOrDefault(url, 0) + 1);
            }
        }

        // Sort resultRows based on the counts of URLs using the urlCount map
            resultRows.sort((row1, row2) -> {
            String url1 = row1.getLinkUrl(); // Assuming URL is in the first index of the row
            String url2 = row2.getLinkUrl();

            return Integer.compare(urlCount.getOrDefault(url2, 0), urlCount.getOrDefault(url1, 0));
        });

        return resultRows;
    }

}
