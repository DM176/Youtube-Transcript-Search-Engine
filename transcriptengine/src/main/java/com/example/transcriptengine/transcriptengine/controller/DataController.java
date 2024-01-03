package com.example.transcriptengine.transcriptengine.controller;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import com.example.transcriptengine.transcriptengine.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @RequestMapping(value = "/readData")
    public Map<Integer, ClickRequest> getTranscriptData() throws IOException {
       return dataService.readFromFile();
    }
}
