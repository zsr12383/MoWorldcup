package com.example.moworldcup.web.dto;

import java.time.LocalDateTime;

import com.example.moworldcup.domain.topic.Topic;

import lombok.Getter;

@Getter
public class TopicResponseDto {
    private final Integer id;
    private final Integer registrantId;
    private final String title;
    private final LocalDateTime modifiedDate;
    private final Integer view_count;

    public TopicResponseDto(Topic entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.registrantId = entity.getRegistrant_id();
        this.modifiedDate = entity.getModifiedDate();
        this.view_count = entity.getView_count();
    }
}
