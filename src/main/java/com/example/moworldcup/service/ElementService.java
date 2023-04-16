package com.example.moworldcup.service;

import com.example.moworldcup.domain.Status;
import com.example.moworldcup.domain.element.Element;
import com.example.moworldcup.domain.element.ElementRepository;
import com.example.moworldcup.web.element.dto.ElementListResponseDto;
import com.example.moworldcup.web.element.dto.ElementResponseDto;
import com.example.moworldcup.web.element.dto.ElementSaveRequestDto;
import com.example.moworldcup.web.element.dto.ElementUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ElementService {
    private final ElementRepository elementRepository;

    @Transactional
    public Integer save(ElementSaveRequestDto requestDto, Integer registrantId) {
        Element element = requestDto
            .toBuilder()
            .registrantId(registrantId)
            .status(Status.POST)
            .winCount(0)
            .build();
        return elementRepository.save(element).getId();
    }

    @Transactional
    public Integer update(Integer id, ElementUpdateRequestDto requestDto) {
        Element element = elementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 후보가 없습니다. id=" + id));
        element.update(requestDto);
        return id;
    }

    @Transactional
    public void delete(Integer id) {
        Element element = elementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 후보가 없습니다. id=" + id));

        elementRepository.delete(element);
    }

    @Transactional(readOnly = true)
    public ElementResponseDto findById(Integer id) {
        Element entity = elementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 후보가 없습니다. id=" + id));

        return new ElementResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ElementListResponseDto> findAllDesc() {
        return elementRepository.findAllDesc().stream()
            .map(ElementListResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Integer patchWinCount(Integer id) {
        Element entity = elementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 후보가 없습니다. id=" + id));
        entity.addWinCount();
        return id;
    }

    public boolean isAuthorOfElement(Integer elementId, Integer userId) {
        Optional<Element> optionalPost = elementRepository.findById(elementId);
        if (optionalPost.isPresent()) {
            Element element = optionalPost.get();
            return element.getRegistrantId().equals(userId);
        } else {
            throw new IllegalArgumentException("해당 후보의 작성자가 아닙니다. id=" + elementId);
        }
    }
}
