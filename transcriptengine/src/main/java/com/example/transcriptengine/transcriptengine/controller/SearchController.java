package com.example.transcriptengine.transcriptengine.controller;

import com.example.transcriptengine.transcriptengine.service.FrequencyBasedSearchService;
import com.example.transcriptengine.transcriptengine.service.SimpleSearchService;
import com.example.transcriptengine.transcriptengine.service.ThreshHoldSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    SimpleSearchService simpleSearchService;
    @Autowired
    ThreshHoldSearchService threshHoldSearchService;
    @Autowired
    FrequencyBasedSearchService frequencyBasedSearchService;

    @RequestMapping(value = "/")
    public String goToSearchEngine() {
        return "searchEngine";
    }

    @PostMapping("/search")
    public String performSearch(Model model, @RequestParam String query) {
        List<List<String>> simpleSearchServiceResponse = simpleSearchService.searchVideo(query);
        List<List<String>> threshHoldSearchServiceResponse = threshHoldSearchService.searchVideo(query);
        List<List<String>> frequencyBasedSearchServiceResponse = frequencyBasedSearchService.searchVideo(query);

        List<List<String>> combinedResponse = new ArrayList<>();

        int maxSize = Math.max(Math.max(simpleSearchServiceResponse.size(), threshHoldSearchServiceResponse.size()), frequencyBasedSearchServiceResponse.size());
        for (int i = 0; i < maxSize; i++) {
            if (i < simpleSearchServiceResponse.size()) {
                combinedResponse.add(simpleSearchServiceResponse.get(i));
            }
            if (i < threshHoldSearchServiceResponse.size()) {
                combinedResponse.add(threshHoldSearchServiceResponse.get(i));
            }
            if (i < frequencyBasedSearchServiceResponse.size()) {
                combinedResponse.add(frequencyBasedSearchServiceResponse.get(i));
            }
        }
        model.addAttribute("response", combinedResponse);

        return "result";
    }

}
