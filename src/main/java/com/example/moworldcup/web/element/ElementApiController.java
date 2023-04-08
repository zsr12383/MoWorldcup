package com.example.moworldcup.web.element;

import com.example.moworldcup.config.auth.LoginUser;
import com.example.moworldcup.config.auth.dto.SessionUser;
import com.example.moworldcup.service.ElementService;
import com.example.moworldcup.web.element.dto.ElementListResponseDto;
import com.example.moworldcup.web.element.dto.ElementResponseDto;
import com.example.moworldcup.web.element.dto.ElementSaveRequestDto;
import com.example.moworldcup.web.element.dto.ElementUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ElementApiController {
    private final ElementService elementService;

    @PostMapping("/api/v1/element/")
    @PreAuthorize("isAuthenticated()")
    public Integer save(@RequestBody ElementSaveRequestDto requestDto, @LoginUser SessionUser currentUser) {
        return elementService.save(requestDto, currentUser.getId());
    }

    @PutMapping("/api/v1/element/{id}")
    @PreAuthorize("isAuthenticated() and @elementService.isAuthorOfElement(#id, #currentUser.getId())")
    public Integer update(@PathVariable Integer id, @RequestBody ElementUpdateRequestDto requestDto, @LoginUser SessionUser currentUser) {
        return elementService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/element/{id}")
    @PreAuthorize("isAuthenticated() and @elementService.isAuthorOfElement(#id, #currentUser.getId())")
    public Integer delete(@PathVariable Integer id, @LoginUser SessionUser currentUser) {
        elementService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/element/{id}")
    public ElementResponseDto findById(@PathVariable Integer id) {
        return elementService.findById(id);
    }

    @GetMapping("/api/v1/element/list")
    public List<ElementListResponseDto> findAll() {
        return elementService.findAllDesc();
    }

    @PatchMapping("/api/v1/element/{id}")
    public Integer patchWinCount(@PathVariable Integer id) {
        return elementService.patchWinCount(id);
    }
}
