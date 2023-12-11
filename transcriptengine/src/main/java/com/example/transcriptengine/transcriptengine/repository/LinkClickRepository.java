package com.example.transcriptengine.transcriptengine.repository;


import com.example.transcriptengine.transcriptengine.dto.ClickRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkClickRepository extends JpaRepository<ClickRequest, Integer> {

    List<ClickRequest> findByLinkUrl(String linkUrl);

}