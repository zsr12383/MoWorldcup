package com.example.moworldcup.web.element.dto;

import com.example.moworldcup.domain.element.Element;
import com.example.moworldcup.domain.element.Types;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ElementSaveRequestDto {
    private Integer topicId;
    private String title;
    private Types types;
    private String url;

    @Builder
    public ElementSaveRequestDto(Integer topicId, String title, Types types, String url) {
        this.topicId = topicId;
        this.title = title;
        this.types = types;
        this.url = url;
    }

    public Element.ElementBuilder toBuilder() {
        return Element.builder()
            .topicId(topicId)
            .title(title)
            .types(types)
            .url(url);
    }

}
