package com.example.moworldcup.web.topic.dto;

import com.example.moworldcup.domain.topic.Topic;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicSaveRequestDto {
    private String title;

    @Builder
    public TopicSaveRequestDto(String title) {
        this.title = title;
    }

    public Topic.TopicBuilder toBuilder() {
        return Topic.builder()
            .title(title);
    }

}
