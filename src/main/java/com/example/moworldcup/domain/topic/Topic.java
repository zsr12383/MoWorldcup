package com.example.moworldcup.domain.topic;

import com.example.moworldcup.domain.BaseTimeEntity;

import com.example.moworldcup.domain.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Topic extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer registrantId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer viewCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @Builder
    public Topic(Integer registrantId, String title) {
        this.registrantId = registrantId;
        this.title = title;
        this.viewCount = 0;
        this.status = Status.POST;
    }

    public void addViewCount() {this.viewCount++;}
    
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}
