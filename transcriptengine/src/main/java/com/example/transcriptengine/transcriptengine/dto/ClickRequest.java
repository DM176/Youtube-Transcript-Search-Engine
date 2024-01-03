package com.example.transcriptengine.transcriptengine.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class ClickRequest {

//    @Id
//    private int id; // Use Long instead of int for ID to match H2's handling of auto-generated IDs

    private String userId;

    private String linkUrl;
    private int thumbsUpCount;
    private int thumbsDownCount;
    private int numberOfClicks;


    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setTranscriptData(String transcriptData) {
        this.transcriptData = transcriptData;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getTranscriptData() {
        return transcriptData;
    }

    private String timeStampLink;

    private String transcriptData;


    public ClickRequest() {
        this.userId = null;
        this.timeStampLink = null;
        thumbsUpCount = 0;
        thumbsDownCount = 0;
        numberOfClicks = 0;
    }
    // Constructor with all fields except id as it will be auto-generated
    public ClickRequest(String userId, String timeStampLink, int thumbsUp, int thumbsDown, int numberOfClicks) {
        this.userId = userId;
        this.timeStampLink = timeStampLink;
        this.thumbsUpCount = thumbsUp;
        this.thumbsDownCount = thumbsDown;
        this.numberOfClicks = numberOfClicks;
    }
    public void increaseClick(){
        this.numberOfClicks++;
    }
    public void increaseThumbsUp(){
        this.thumbsUpCount++;
    }
    public void increaseThumbDown(){
        this.thumbsDownCount++;
    }

    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter for linkUrl
    public String gettimeStampLink() {
        return timeStampLink;
    }

    public void settimeStampLink(String timeStampLink) {
        this.timeStampLink = timeStampLink;
    }

    public void setNumberOfClicks(int numericCellValue) {
    }

    public void setThumbsUpCount(int numericCellValue) {
    }

    public void setThumbsDownCount(int numericCellValue) {
    }
    public int getThumbsUpCount() {
        return thumbsUpCount;
    }
    public int getThumbsDownCount() {
        return  thumbsDownCount;
    }
    public int getNumberOfClicks() {
        return  numberOfClicks;
    }

}
