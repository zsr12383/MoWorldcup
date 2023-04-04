package com.example.moworldcup.domain.topic;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicRepositoryTest {

    @Autowired
    TopicRepository topicRepository;

    @After
    public void cleanup() {
        topicRepository.deleteAll();
    }

    @Test
    public void 토픽_저장_불러오기() {
        //given
        String title = "테스트 토픽";
        Long registrantId = 456L;

        topicRepository.save(Topic.builder()
            .registrant_id(registrantId)
            .title(title)
            .build());

        //when
        List<Topic> topicList = topicRepository.findAll();

        //then
        Topic topic = topicList.get(0);
        assertThat(topic.getTitle()).isEqualTo(title);
        assertThat(topic.getRegistrant_id()).isEqualTo(registrantId);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        topicRepository.save(Topic.builder()
            .registrant_id(456L)
            .title("title")
            .build());
        //when
        List<Topic> topicList = topicRepository.findAll();

        //then
        Topic topic = topicList.get(0);

        System.out.println(
            ">>>>>>>>> createDate=" + topic.getCreatedDate() + ", modifiedDate=" + topic.getModifiedDate());

        assertThat(topic.getCreatedDate()).isAfter(now);
        assertThat(topic.getModifiedDate()).isAfter(now);
    }
}