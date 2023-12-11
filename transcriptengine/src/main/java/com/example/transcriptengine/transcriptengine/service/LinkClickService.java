package com.example.transcriptengine.transcriptengine.service;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import com.example.transcriptengine.transcriptengine.repository.LinkClickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkClickService {

    @Autowired
    private final LinkClickRepository linkClickRepository;

    @Autowired
    public LinkClickService(LinkClickRepository linkClickRepository) {
        this.linkClickRepository = linkClickRepository;
    }

    // Method to record link clicks
    public void recordLinkClick(String userId, String linkUrl) {
        // Create a ClickRequest object and save it using the repository
        ClickRequest clickRequest = null;
        if(linkClickRepository.findByLinkUrl(linkUrl).size()==0) {
            clickRequest = new ClickRequest(userId, linkUrl, 0, 0, 1);
            linkClickRepository.save(clickRequest);
        } else {
            clickRequest = linkClickRepository.findByLinkUrl(linkUrl).get(0);
        }

    }

    public void recordThumbsUp(String userId, String linkUrl) {
        ClickRequest clickRequest = null;
        if(linkClickRepository.findByLinkUrl(linkUrl).size()==0) {
            clickRequest = new ClickRequest(userId, linkUrl, 1, 0, 0);
            linkClickRepository.save(clickRequest);
        } else {
            clickRequest = linkClickRepository.findByLinkUrl(linkUrl).get(0);
            clickRequest.increaseThumbsUp();
            linkClickRepository.save(clickRequest);
        }
    }

    public void recordThumbsDown(String userId, String linkUrl) {
        ClickRequest clickRequest = null;
        if(linkClickRepository.findByLinkUrl(linkUrl).size()==0) {
            clickRequest = new ClickRequest(userId, linkUrl, 0, 1, 0);
            linkClickRepository.save(clickRequest);
        } else {
            clickRequest = linkClickRepository.findByLinkUrl(linkUrl).get(0);
            clickRequest.increaseThumbDown();
            linkClickRepository.save(clickRequest);

        }
    }
}
