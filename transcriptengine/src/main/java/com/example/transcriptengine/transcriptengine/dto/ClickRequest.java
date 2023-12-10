package com.example.transcriptengine.transcriptengine.dto;



public class ClickRequest {

    private Long id; // Use Long instead of int for ID to match H2's handling of auto-generated IDs

    private String userId;

    private String linkUrl;

    // Constructor with all fields except id as it will be auto-generated
    public ClickRequest(String userId, String linkUrl) {
        this.userId = userId;
        this.linkUrl = linkUrl;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
