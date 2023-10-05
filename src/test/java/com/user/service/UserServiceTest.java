package com.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.entities.User;
import com.user.repository.UserRepository;
import com.user.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private QueueMessagingTemplate queueMessagingTemplate;

    @Spy
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(userService, "endpoint", "");
    }

    @Test
    public void createUser() {
        User user = UserUtil.preapreUser();
        Mockito.doNothing().when(queueMessagingTemplate).send(Mockito.anyString(), Mockito.any());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        String response = userService.createUser(user);
        Assertions.assertNotNull(response);
    }

    @Test
    public void getUser() {
        Optional<User> optionalUser = Optional.of(UserUtil.preapreUser());
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(optionalUser);
        User user = userService.getUser(Long.valueOf(1));
        Assertions.assertNotNull(user);

    }

    @Test
    public void deleteUser() {
        userRepository.deleteById(Long.valueOf(1));
    }

    @Test
    public void updateUser() {
        User user = UserUtil.preapreUser();
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        String response = userService.updateUser(user);
        Assertions.assertNotNull(response);
    }

    @Test
    public void getUsers() {
        Mockito.when(userRepository.findByFirstNameAndLastName(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Arrays.asList(UserUtil.preapreUser()));
        List<User> users = userService.getUsers("test", "test");
        Assertions.assertNotNull(users);
    }

    @Test
    public void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(UserUtil.preapreUser()));
        List<User> users = userService.getAllUsers();
        Assertions.assertNotNull(users);
    }


}
