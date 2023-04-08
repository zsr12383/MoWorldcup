package com.example.moworldcup.domain.element;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Types {
    IMAGE("TYPES_IMAGE", "이미지"),
    VIDEO("TYPES_VIDEO", "영상");

    private final String key;
    private final String title;
}
