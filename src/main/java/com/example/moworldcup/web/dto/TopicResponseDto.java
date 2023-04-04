package com.example.moworldcup.web.dto;

import java.time.LocalDateTime;

import com.example.moworldcup.domain.topic.Topic;

import lombok.Getter;

@Getter
public class TopicResponseDto {
    private final Long id;
    private final Long registrantId;
    private final String title;
    private final LocalDateTime modifiedDate;
    private final Long view_count;

    public TopicResponseDto(Topic entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.registrantId = entity.getRegistrant_id();
        this.modifiedDate = entity.getModifiedDate();
        this.view_count = entity.getView_count();
    }
}
