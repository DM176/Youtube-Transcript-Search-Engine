package com.example.transcriptengine.transcriptengine.controller;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import com.example.transcriptengine.transcriptengine.service.LinkClickService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ResultController {

    private final LinkClickService linkClickService;

    // Constructor injection of LinkClickService
    public ResultController(LinkClickService linkClickService) {
        this.linkClickService = linkClickService;
    }

    // Endpoint to record link clicks
    @PostMapping("/record-click")
    public ResponseEntity<String> recordLinkClick(@RequestBody ClickRequest clickRequest) {
        linkClickService.recordLinkClick(clickRequest.getUserId(), clickRequest.getLinkUrl());
        return ResponseEntity.ok("Click recorded successfully");
    }
}
