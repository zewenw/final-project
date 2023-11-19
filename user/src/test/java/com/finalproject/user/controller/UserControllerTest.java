package com.finalproject.user.controller;

import com.finalproject.user.dto.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@DataJpaTest
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    static String USER_REQUEST_URL = "/v1/users";

    @Test
    void getUserByPage() {
        String pageUrl = USER_REQUEST_URL.concat("/page");
        UserRequest request = UserRequest.builder().pageNo(1).pageSize(2).build();
        webTestClient.post()
                .uri(pageUrl)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void addUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getAllUser() {
    }

    @Test
    void testGetUserByPage() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}