package com.example.moworldcup.domain.topic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    POST("STATUS_POST", "게시됨"),
    HIDE("STATUS_HIDE", "숨김"),
    DELETE("STATUS_DELETE", "삭제");

    private final String key;
    private final String title;

}
