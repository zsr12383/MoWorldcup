package com.example.moworldcup.config.auth.dto;

import java.io.Serializable;

import com.example.moworldcup.domain.user.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private Integer id;
    private String name;
    private String email;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
