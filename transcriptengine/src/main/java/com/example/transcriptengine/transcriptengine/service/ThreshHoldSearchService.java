package com.example.transcriptengine.transcriptengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ThreshHoldSearchService implements SearchAlgoInterface{
    @Autowired
    DataService dataService;

    @Override
    public List<List<String>> searchVideo(String query) {
        Map<Integer, List<String>> data = null;
        List<List<String>> resultRows = new ArrayList<>();
        Set<String > uniqueURLs = new HashSet<>(); // Store unique URLs

        try {
            data = dataService.readFromFile();
        } catch (IOException ioException) {
            // Handle the exception appropriately
        }

        for (Map.Entry<Integer, List<String>> entry : data.entrySet()) {
            List<String> rowData = entry.getValue();
            String largeText = rowData.get(2);

            String result = findMatchesInLargeText(largeText, query, 0);

            if (result != null) {
                String url = rowData.get(0); // Assuming URL is in the first index of the row
                if (!uniqueURLs.contains(url)) {
                    resultRows.add(rowData);
                    uniqueURLs.add(url);
                }
            }
        }

        return resultRows;
    }

    public static String findMatchesInLargeText(String largeText, String searchText, int threshold) {
        String[] words = largeText.split("\\s+"); // Split the large text into words

        for (String word : words) {
            int distance = levenshteinDistance(searchText, word);
            if (distance <= threshold) {
                return word;
            }
        }
        return null;
    }

    public static int levenshteinDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] distance = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1),
                        distance[i - 1][j - 1] + cost);
            }
        }
        return distance[len1][len2];
    }
}
