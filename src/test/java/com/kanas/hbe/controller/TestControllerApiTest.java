package com.kanas.hbe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class TestControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Hello World Tests")
    @Nested
    class HelloWorldTests {

        @Test
        void whenCalling_throwNoErrors() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Hello World!"));
        }
    }
}
