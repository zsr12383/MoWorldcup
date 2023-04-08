package com.example.moworldcup.web.element.dto;

import com.example.moworldcup.domain.element.Element;
import com.example.moworldcup.domain.element.Types;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ElementListResponseDto {
    private final Integer id;
    private final Integer registrantId;
    private final Integer topicId;
    private final String title;
    private final String url;
    private final Integer winCount;
    private final Types types;
    private final LocalDateTime modifiedDate;
    
    public ElementListResponseDto(Element entity) {
        this.id = entity.getId();
        this.registrantId = entity.getRegistrantId();
        this.topicId = entity.getTopicId();
        this.title = entity.getTitle();
        this.url = entity.getUrl();
        this.types = entity.getTypes();
        this.modifiedDate = entity.getModifiedDate();
        this.winCount = entity.getWinCount();
    }
}
