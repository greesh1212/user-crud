package com.user.controller;

import com.user.entities.User;
import com.user.service.UserService;
import com.user.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;



    @Test
    public void ping() {
        Assertions.assertEquals(userController.ping() ,"pong");
    }

    @Test
    public void createUser() {
        Mockito.when(userService.createUser(Mockito.any())).thenReturn("user saved successfully");
        String status = userController.createUser(UserUtil.preapreUser());
        Assertions.assertNotNull(status);
        Assertions.assertEquals(status ,"user saved successfully");
    }

    @Test
    public void getUser() {
        Mockito.when(userService.getUser(Long.valueOf(1))).thenReturn(UserUtil.preapreUser());
        User user = userController.getUser(Long.valueOf(1));
        Assertions.assertNotNull(user);
    }

    @Test
    public void deleteUser() {
        Mockito.doNothing().when(userService).deleteUser(Mockito.anyLong());
        String status = userController.deleteUser(Long.valueOf(1));
        Assertions.assertNotNull(status);
        Assertions.assertEquals("user deleted successfully", status);
    }

    @Test
    public void updateUser() {
        Mockito.when(userService.updateUser(Mockito.any())).thenReturn("Success");
        String status = userController.updateUser(UserUtil.preapreUser());
        Assertions.assertNotNull(status);
        Assertions.assertEquals("Success", status);
    }

    @Test
    public void getUsers() {
        List<User> users = new ArrayList<>();
        users.add(UserUtil.preapreUser());
        Mockito.when(userService.getUsers("test", "test")).thenReturn(users);
        List<User> usersResponse =  userController.getUsers("test", "test");
        Assertions.assertNotNull(usersResponse);
    }

    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(UserUtil.preapreUser());
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        List<User> usersResponse = userController.getAllUsers();
        Assertions.assertNotNull(usersResponse);
    }

}
