package com.example.moworldcup.domain.element;

import com.example.moworldcup.domain.BaseTimeEntity;
import com.example.moworldcup.domain.Status;
import com.example.moworldcup.web.element.dto.ElementUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Element extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer registrantId;

    @Column(nullable = false)
    private Integer topicId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Integer winCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Types types;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Builder
    public Element(Integer registrantId, Integer topicId, Types types, String title, String url, Integer winCount, Status status) {
        this.registrantId = registrantId;
        this.topicId = topicId;
        this.status = status;
        this.types = types;
        this.title = title;
        this.url = url;
        this.winCount = winCount;
    }

    public void addWinCount() {
        this.winCount++;
    }

    public void update(ElementUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.url = requestDto.getUrl();
        this.status = requestDto.getStatus();
        this.types = requestDto.getTypes();
    }
}
