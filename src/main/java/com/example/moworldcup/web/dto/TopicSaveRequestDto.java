package com.example.moworldcup.web.dto;

import com.example.moworldcup.domain.topic.Topic;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TopicSaveRequestDto {
    private Integer registrantId;
    private String title;

    @Builder
    public TopicSaveRequestDto(Integer registrantId, String title) {
        this.registrantId = registrantId;
        this.title = title;
    }

    public Topic toEntity() {
        return Topic.builder()
            .registrant_id(registrantId)
            .title(title)
            .build();
    }

}
