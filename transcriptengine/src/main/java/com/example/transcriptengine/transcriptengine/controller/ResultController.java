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

    @PostMapping("/record-thumbsUp")
    public ResponseEntity<String> recordThumbsUp(@RequestBody ClickRequest clickRequest) {
        linkClickService.recordThumbsUp(clickRequest.getUserId(), clickRequest.getLinkUrl());
        return ResponseEntity.ok("Thumbs Up recorded successfully");
    }
    @PostMapping("/record-thumbsDown")
    public ResponseEntity<String> recordThumbsDown(@RequestBody ClickRequest clickRequest) {
        linkClickService.recordThumbsDown(clickRequest.getUserId(), clickRequest.getLinkUrl());
        return ResponseEntity.ok("Thumbs Down recorded successfully");
    }
}
