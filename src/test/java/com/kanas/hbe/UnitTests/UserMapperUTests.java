package com.kanas.hbe.UnitTests;

import com.kanas.hbe.fixtures.UserFixtures;
import com.kanas.hbe.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserMapperUTests {

    @Nested
    @DisplayName("Convert RegistrationDto to User UTests")
    class ConvertRegistrationDtoToUserUTests {

        @Test
        void whenConvertingRegistrationDto_verifyCorrectConversions() {

            // Given
            var registrationDto = UserFixtures.createRegistrationDto();

            // When
            var result = UserMapper.toEntity(registrationDto);

            // Then
            assertEquals(result.getUsername(), registrationDto.getUsername());
            assertEquals(result.getEmail(), registrationDto.getEmail());
            assertEquals(result.getPassword(), registrationDto.getPassword());
        }

        @Test
        void whenConvertingUserDto_verifyCorrectConversions() {

            // Given
            var userDto = UserFixtures.createUserDto();

            // When
            var result = UserMapper.toEntity(userDto);

            // Then
            assertEquals(result.getUsername(), userDto.getUsername());
            assertEquals(result.getEmail(), userDto.getEmail());
            assertEquals(result.getPassword(), userDto.getPassword());
        }

        @Test
        void whenConvertingUserEntity_verifyCorrectConversions() {

            // Given
            var user = UserFixtures.createUser();

            // When
            var result = UserMapper.toDto(user);

            // Then
            assertEquals(result.getUsername(), user.getUsername());
            assertEquals(result.getEmail(), user.getEmail());
            assertEquals(result.getPassword(), user.getPassword());
        }
    }

}
