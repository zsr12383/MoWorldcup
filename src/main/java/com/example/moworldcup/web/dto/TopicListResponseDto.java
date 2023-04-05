package com.example.moworldcup.web.dto;

import com.example.moworldcup.domain.topic.Topic;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TopicListResponseDto {
    private final Integer id;
    private final Integer registrantId;
    private final String title;
    private final LocalDateTime modifiedDate;
    private final Integer view_count;

    public TopicListResponseDto(Topic entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.registrantId = entity.getRegistrant_id();
        this.modifiedDate = entity.getModifiedDate();
        this.view_count = entity.getView_count();
    }
}
