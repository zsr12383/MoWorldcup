package com.example.moworldcup.domain.topic;

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
    private Long id;

    @Column(nullable = false)
    private Long registrant_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Long view_count;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'POST'")
    private Status status;


    @Builder
    public Topic(Long registrant_id, String title) {
        this.title = title;
        this.registrant_id = registrant_id;
    }

    public void update(Long registrant_id, String title) {
        this.title = title;
        this.registrant_id = registrant_id;
    }
}
