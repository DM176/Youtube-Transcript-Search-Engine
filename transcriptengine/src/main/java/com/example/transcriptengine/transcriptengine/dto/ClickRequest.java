package com.example.transcriptengine.transcriptengine.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClickRequest {

    @Id
    private int id; // Use Long instead of int for ID to match H2's handling of auto-generated IDs

    private String userId;

    private String linkUrl;

    private int thumbsUpCount;
    private int thumbsDownCount;
    private int numberOfClicks;

    public ClickRequest() {
        this.userId = null;
        this.linkUrl = null;
        thumbsUpCount = 0;
        thumbsDownCount = 0;
        numberOfClicks = 0;
    }
    // Constructor with all fields except id as it will be auto-generated
    public ClickRequest(String userId, String linkUrl, int thumbsUp, int thumbsDown, int numberOfClicks) {
        this.userId = userId;
        this.linkUrl = linkUrl;
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

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter for linkUrl
    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
