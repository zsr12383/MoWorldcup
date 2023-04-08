package com.example.moworldcup.domain.element;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementRepository extends JpaRepository<Element, Integer> {
    @Query("SELECT p FROM Topic p ORDER BY p.id DESC")
    List<Element> findAllDesc();
}
