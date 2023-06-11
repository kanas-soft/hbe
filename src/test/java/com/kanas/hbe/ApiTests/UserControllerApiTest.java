package com.kanas.hbe.ApiTests;

import com.kanas.hbe.controller.UserController;
import com.kanas.hbe.fixtures.UserFixtures;
import com.kanas.hbe.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerApiTest extends BaseApiTest {

    private final String PATH = "/api/v1/users";

    @MockBean
    private UserService userService;

    @BeforeAll
    public void setup() {
        super.setUpWithNoSecurity();
    }

    @Nested
    @DisplayName("Register User Tests")
    class registerUserTests {

        @Test
        void whenCreating_throwNoErrors() throws Exception {

            // Given
            var request = MockMvcRequestBuilders.post(PATH + "/register")
                    .contentType("application/json")
                    .content(toJson(UserFixtures.createRegistrationDto()));

            // When Then
            mvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }
    }

    @Nested
    @DisplayName("User Email Confirmation Tests")
    class userEmailConfirmationTests {

        @Test
        void whenCreating_throwNoErrors() throws Exception {

            // Given
            var request = MockMvcRequestBuilders.post(PATH + "/register")
                    .contentType("application/json")
                    .content(toJson(UserFixtures.createRegistrationDto()));

            // When Then
            mvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }
    }
}
