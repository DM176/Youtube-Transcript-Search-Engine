package com.example.transcriptengine.transcriptengine.service;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import com.example.transcriptengine.transcriptengine.repository.LinkClickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkClickService {

    private final LinkClickRepository linkClickRepository;

    @Autowired
    public LinkClickService(LinkClickRepository linkClickRepository) {
        this.linkClickRepository = linkClickRepository;
    }

    // Method to record link clicks
    public void recordLinkClick(String userId, String linkUrl) {
        // Create a ClickRequest object and save it using the repository
        ClickRequest clickRequest = new ClickRequest(userId, linkUrl);
        linkClickRepository.save(clickRequest);
    }
}
