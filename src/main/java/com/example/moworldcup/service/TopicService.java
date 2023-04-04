package com.example.moworldcup.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moworldcup.domain.topic.Topic;
import com.example.moworldcup.domain.topic.TopicRepository;
import com.example.moworldcup.web.dto.TopicListResponseDto;
import com.example.moworldcup.web.dto.TopicResponseDto;
import com.example.moworldcup.web.dto.TopicSaveRequestDto;
import com.example.moworldcup.web.dto.TopicUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TopicService {
    private final TopicRepository TopicRepository;

    @Transactional
    public Long save(TopicSaveRequestDto requestDto) {
        return TopicRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, TopicUpdateRequestDto requestDto) {
        Topic topic = TopicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        topic.update(requestDto.getTitle());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Topic topic = TopicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        TopicRepository.delete(topic);
    }

    @Transactional(readOnly = true)
    public TopicResponseDto findById(Long id) {
        Topic entity = TopicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new TopicResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<TopicListResponseDto> findAllDesc() {
        return TopicRepository.findAllDesc().stream()
            .map(TopicListResponseDto::new)
            .collect(Collectors.toList());
    }
}
