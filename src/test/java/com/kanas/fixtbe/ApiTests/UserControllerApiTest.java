package com.kanas.fixtbe.ApiTests;

import com.kanas.fixtbe.controller.UserController;
import com.kanas.fixtbe.event.publisher.EventPublisher;
import com.kanas.fixtbe.exception.UsernameAlreadyExistsException;
import com.kanas.fixtbe.fixtures.UserFixtures;
import com.kanas.fixtbe.service.ConfirmationTokenService;
import com.kanas.fixtbe.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Clock;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerApiTest extends BaseApiTest {

    private final String PATH = "/api/v1/users";

    @MockBean
    private Clock clock;

    @MockBean
    private UserService userService;

    @MockBean
    private EventPublisher eventPublisher;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @BeforeAll
    public void setup() {
        super.setUpWithNoSecurity();
    }

    @Nested
    @DisplayName("Register User Tests")
    class RegisterUserTests {

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

        @Test
        void whenCreating_throwErrors() throws Exception {

            // Given
            var request = MockMvcRequestBuilders.post(PATH + "/register")
                    .contentType("application/json")
                    .content(toJson(UserFixtures.createRegistrationDto()));

            when(userService.registerNewUserAccount(any())).thenThrow(new UsernameAlreadyExistsException("test"));

            // When Then
            mvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().is(400));
        }
    }

    @Nested
    @DisplayName("User Email Confirmation Tests")
    class UserEmailConfirmationTests {

        @Test
        void whenConfirming_throwNoErrors() throws Exception {

            // Given
            var request = MockMvcRequestBuilders
                    .get(PATH + "/confirm-registration?token=" + UUID.randomUUID().toString())
                    .contentType("application/json");

            // When Then
            mvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().is(303));
        }

        @Test
        void whenResending_throwNoErrors() throws Exception {

            // Given
            var request = MockMvcRequestBuilders
                    .get(PATH + "/resend-token?token=" + UUID.randomUUID().toString())
                    .contentType("application/json");

            // When Then
            mvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().is(303));
        }
    }
}
