package com.app.utils;

import com.app.models.user.User;

public class LogUtils {

    public static String getLogMessageWithUsername(User user, String message) {
        return "[" + user.getUsername() + "]: " + message;
    }
}
