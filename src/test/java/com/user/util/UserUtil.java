package com.user.util;

import com.user.entities.User;

public class UserUtil {
    public static User preapreUser() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setFirstName("Alex");
        return user;
    }
}
