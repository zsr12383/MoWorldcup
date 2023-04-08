package com.example.moworldcup.web.element.dto;

import com.example.moworldcup.domain.Status;
import com.example.moworldcup.domain.element.Types;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElementUpdateRequestDto {
    private String title;
    private String url;
    private Types types;
    private Status status;

    @Builder
    public ElementUpdateRequestDto(String title, String url, Status status, Types types) {
        this.title = title;
        this.url = url;
        this.status = status;
        this.types = types;
    }
}
