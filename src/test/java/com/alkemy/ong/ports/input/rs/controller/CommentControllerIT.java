package com.alkemy.ong.ports.input.rs.controller;

import com.alkemy.ong.H2Config;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentControllerIT {

    @Autowired MockMvc mockMvc;

    @Test
    @Order(1)
    @WithUserDetails("admin@somosmas.org")
    void createComment() {
    }

    @Test
    @Order(4)
    @WithUserDetails("admin@somosmas.org")
    void updateComment() {
    }

    @Test
    @Order(2)
    @WithUserDetails("fbar@somosmas.org")
    void getComments() {
    }

    @Test
    @Order(5)
    @WithUserDetails("admin@somosmas.org")
    void deleteComment() {
    }

}