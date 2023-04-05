package com.example.moworldcup.domain.topic;

import org.hibernate.annotations.ColumnDefault;

import com.example.moworldcup.domain.BaseTimeEntity;

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
    private Integer registrant_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer view_count;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @Builder
    public Topic(Integer registrant_id, String title) {
        this.registrant_id = registrant_id;
        this.title = title;
        this.view_count = 0;
        this.status = Status.POST;
    }

    public void update(String title) {
        this.title = title;
    }
}
