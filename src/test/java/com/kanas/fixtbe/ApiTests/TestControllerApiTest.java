package com.kanas.fixtbe.ApiTests;

import com.kanas.fixtbe.controller.TestController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(TestController.class)
@ExtendWith(SpringExtension.class)
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
