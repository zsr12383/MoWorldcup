package com.example.moworldcup.web.topic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TopicUpdateRequestDto {
    private String title;

    @Builder
    public TopicUpdateRequestDto(String title) {
        this.title = title;
    }
}
