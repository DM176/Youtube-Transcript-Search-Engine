package com.example.transcriptengine.transcriptengine.service;

import com.example.transcriptengine.transcriptengine.dto.ClickRequest;

import java.util.List;

public interface SearchAlgoInterface {
    public List<ClickRequest> searchVideo(String query) ;
}
