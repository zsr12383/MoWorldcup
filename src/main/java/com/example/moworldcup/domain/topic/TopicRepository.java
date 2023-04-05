package com.example.moworldcup.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query("SELECT p FROM Topic p ORDER BY p.id DESC")
    List<Topic> findAllDesc();
}
