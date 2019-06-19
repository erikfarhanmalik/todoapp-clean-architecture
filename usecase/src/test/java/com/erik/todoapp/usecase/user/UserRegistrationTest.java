package com.erik.todoapp.usecase.user;

import com.erik.todoapp.domain.entity.User;
import com.erik.todoapp.domain.port.respository.UserRepository;
import com.erik.todoapp.usecase.helper.PasswordProtector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void userPasswordShouldBeHashedProperly() {
        String password = "ZXasqw12";
        User user = new User("erik.malik@devstack.com.au", password);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = new UserRegistration(userRepository, user).execute();
        assertThat(savedUser.getPassword()).isEqualTo(new PasswordProtector().encrypt(password));
        assertThat(savedUser.getPassword()).isNotEqualTo("just any string");
    }
}