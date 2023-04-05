package com.example.moworldcup.web;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.moworldcup.service.TopicService;
import com.example.moworldcup.web.dto.TopicListResponseDto;
import com.example.moworldcup.web.dto.TopicResponseDto;
import com.example.moworldcup.web.dto.TopicSaveRequestDto;
import com.example.moworldcup.web.dto.TopicUpdateRequestDto;

@RequiredArgsConstructor
@RestController
public class TopicApiController {
    private final TopicService topicService;

    @PostMapping("/api/v1/topic")
    public Integer save(@RequestBody TopicSaveRequestDto requestDto) {
        return topicService.save(requestDto);
    }

    @PutMapping("/api/v1/topic/{id}")
    public Integer update(@PathVariable Integer id, @RequestBody TopicUpdateRequestDto requestDto) {
        return topicService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/topic/{id}")
    public Integer delete(@PathVariable Integer id) {
        topicService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/topic/{id}")
    public TopicResponseDto findById(@PathVariable Integer id) {
        return topicService.findById(id);
    }

    @GetMapping("/api/v1/topic/list")
    public List<TopicListResponseDto> findAll() {
        return topicService.findAllDesc();
    }
}
