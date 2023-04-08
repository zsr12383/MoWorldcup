package com.example.moworldcup.web.topic;

import java.util.List;

import com.example.moworldcup.config.auth.LoginUser;
import com.example.moworldcup.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.moworldcup.service.TopicService;
import com.example.moworldcup.web.topic.dto.TopicListResponseDto;
import com.example.moworldcup.web.topic.dto.TopicResponseDto;
import com.example.moworldcup.web.topic.dto.TopicSaveRequestDto;
import com.example.moworldcup.web.topic.dto.TopicUpdateRequestDto;

@RequiredArgsConstructor
@RestController
public class TopicApiController {
    private final TopicService topicService;

    @PostMapping("/api/v1/topic")
    @PreAuthorize("isAuthenticated()")
    public Integer save(@RequestBody TopicSaveRequestDto requestDto, @LoginUser SessionUser currentUser) {
        return topicService.save(requestDto, currentUser.getId());
    }

    @PutMapping("/api/v1/topic/{id}")
    @PreAuthorize("isAuthenticated() and @topicService.isAuthorOfTopic(#id, #currentUser.getId())")
    public Integer update(@PathVariable Integer id, @RequestBody TopicUpdateRequestDto requestDto, @LoginUser SessionUser currentUser) {
        return topicService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/topic/{id}")
    @PreAuthorize("isAuthenticated() and @topicService.isAuthorOfTopic(#id, #currentUser.getId())")
    public Integer delete(@PathVariable Integer id, @LoginUser SessionUser currentUser) {
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
