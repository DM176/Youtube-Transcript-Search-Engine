package com.example.transcriptengine.transcriptengine.service;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import com.example.transcriptengine.transcriptengine.repository.LinkClickRepository;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class LinkClickService {

//    @Autowired
//    private final LinkClickRepository linkClickRepository;
    @Autowired
    private Map<Integer, ClickRequest> urlData; // Inject the urlData bean here

    @Autowired
    private Map<String, Integer> urlMap;

    @Autowired
    DataService dataService;

//    @Autowired
//    public LinkClickService(LinkClickRepository linkClickRepository) {
//        this.linkClickRepository = linkClickRepository;
//    }

    // Method to record link clicks
    public void recordLinkClick(String userId, String linkUrl) {
        // Create a ClickRequest object and save it using the repository
          int rowId = urlMap.get(linkUrl);
          ClickRequest clickRequest = urlData.get(rowId);
          clickRequest.increaseClick();
        try {
            dataService.writeToSpecificRow(clickRequest,rowId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        ClickRequest clickRequest = null;
//        if(linkClickRepository.findByLinkUrl(linkUrl).size()==0) {
//            clickRequest = new ClickRequest(userId, linkUrl, 0, 0, 1);
//            linkClickRepository.save(clickRequest);
//        } else {
//            clickRequest = linkClickRepository.findByLinkUrl(linkUrl).get(0);
//            clickRequest.increaseClick();;
//            linkClickRepository.save(clickRequest);
//        }

    }

    public void recordThumbsUp(String userId, String linkUrl) {
        int rowId = urlMap.get(linkUrl);
        ClickRequest clickRequest = urlData.get(rowId);
        clickRequest.increaseThumbsUp();
        try {
            dataService.writeToSpecificRow(clickRequest,rowId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        ClickRequest clickRequest = null;
//        if(linkClickRepository.findByLinkUrl(linkUrl).size()==0) {
//            clickRequest = new ClickRequest(userId, linkUrl, 1, 0, 0);
//            linkClickRepository.save(clickRequest);
//        } else {
//            clickRequest = linkClickRepository.findByLinkUrl(linkUrl).get(0);
//            clickRequest.increaseThumbsUp();
//            linkClickRepository.save(clickRequest);
//        }
    }

    public void recordThumbsDown(String userId, String linkUrl) {
        int rowId = urlMap.get(linkUrl);
        ClickRequest clickRequest = urlData.get(rowId);
        clickRequest.increaseThumbDown();
        try {
            dataService.writeToSpecificRow(clickRequest,rowId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        ClickRequest clickRequest = null;
//        if(linkClickRepository.findByLinkUrl(linkUrl).size()==0) {
//            clickRequest = new ClickRequest(userId, linkUrl, 0, 1, 0);
//            linkClickRepository.save(clickRequest);
//        } else {
//            clickRequest = linkClickRepository.findByLinkUrl(linkUrl).get(0);
//            clickRequest.increaseThumbDown();
//            linkClickRepository.save(clickRequest);
//
//        }
    }
}
