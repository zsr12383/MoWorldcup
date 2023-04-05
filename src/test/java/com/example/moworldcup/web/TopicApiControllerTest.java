package com.example.moworldcup.web;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.moworldcup.domain.topic.Topic;
import com.example.moworldcup.domain.topic.TopicRepository;
import com.example.moworldcup.web.dto.TopicSaveRequestDto;
import com.example.moworldcup.web.dto.TopicUpdateRequestDto;

// For mockMvc

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @After
    public void tearDown() throws Exception {
        topicRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Topic_등록된다() throws Exception {
        //given
        String title = "title";
        Integer registrantId = (int)(Math.random()*10);
        TopicSaveRequestDto requestDto = TopicSaveRequestDto.builder()
            .registrantId(registrantId)
            .title(title)
            .build();

        String url = "http://localhost:" + port + "/api/v1/topic";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk());

        //then
        List<Topic> all = topicRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getRegistrant_id()).isEqualTo(registrantId);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Topic_수정된다() throws Exception {
        //given
        Topic savedTopic = topicRepository.save(Topic.builder()
            .registrant_id(0)
            .title("title")
            .build());

        Integer updateId = savedTopic.getId();
        String expectedTitle = "title2";

        TopicUpdateRequestDto requestDto = TopicUpdateRequestDto.builder()
            .title(expectedTitle)
            .build();

        String url = "http://localhost:" + port + "/api/v1/topic/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk());

        //then
        List<Topic> all = topicRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    }
}
