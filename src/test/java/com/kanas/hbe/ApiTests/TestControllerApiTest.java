package com.kanas.hbe.ApiTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.kanas.hbe.controller.TestController;

@WebMvcTest(TestController.class)
@ExtendWith(SpringExtension.class)
// @TestPropertySource(locations = "classpath:application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestControllerApiTest extends BaseApiTest {

    @BeforeAll
    public void setup() {
        super.setUpWithSecurity();
    }

    @DisplayName("Hello World Tests")
    @Nested
    class HelloWorldTests {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenCalling_throwNoErrors() throws Exception {
            mvc.perform(MockMvcRequestBuilders.get("/test"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Hello World!"));
        }
    }

    @DisplayName("Goodby World Tests")
    @Nested
    class byebyeWorldTests {

        @Test
        @WithMockUser(roles = "ADMIN")
        void whenCalling_throwNoErrors() throws Exception {
            mvc.perform(MockMvcRequestBuilders.get("/bye"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Goodbye World!"));
        }
    }
}
