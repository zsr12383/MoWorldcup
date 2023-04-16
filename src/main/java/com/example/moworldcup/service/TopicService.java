package com.example.moworldcup.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moworldcup.domain.topic.Topic;
import com.example.moworldcup.domain.topic.TopicRepository;
import com.example.moworldcup.web.topic.dto.TopicListResponseDto;
import com.example.moworldcup.web.topic.dto.TopicResponseDto;
import com.example.moworldcup.web.topic.dto.TopicSaveRequestDto;
import com.example.moworldcup.web.topic.dto.TopicUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TopicService {
    private final TopicRepository TopicRepository;

    @Transactional
    public Integer save(TopicSaveRequestDto requestDto, Integer registrantId) {
        return TopicRepository.save(requestDto.toBuilder().registrantId(registrantId).build()).getId();
    }

    @Transactional
    public Integer update(Integer id, TopicUpdateRequestDto requestDto) {
        Topic topic = TopicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 주제가 없습니다. id=" + id));
        topic.updateTitle(requestDto.getTitle());
        return id;
    }

    @Transactional
    public void delete(Integer id) {
        Topic topic = TopicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 주제가 없습니다. id=" + id));

        TopicRepository.delete(topic);
    }

    @Transactional(readOnly = true)
    public TopicResponseDto findById(Integer id) {
        Topic entity = TopicRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 주제가 없습니다. id=" + id));

        return new TopicResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<TopicListResponseDto> findAllDesc() {
        return TopicRepository.findAllDesc().stream()
            .map(TopicListResponseDto::new)
            .collect(Collectors.toList());
    }

    public boolean isAuthorOfTopic(Integer topicId, Integer userId) {
        Optional<Topic> optionalPost = TopicRepository.findById(topicId);
        if (optionalPost.isPresent()) {
            Topic topic = optionalPost.get();
            return topic.getRegistrantId().equals(userId);
        } else {
            throw new IllegalArgumentException("해당 주제의 작성자가 아닙니다. id=" + topicId);
        }
    }
}
