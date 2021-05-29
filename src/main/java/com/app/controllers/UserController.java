package com.app.controllers;

import com.app.models.user.User;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.utils.LogUtils.getLogMessageWithUsername;


@RestController
@RequestMapping("/user")
@Log4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<User> getUser() {
        User user = userService.getUserFromContext();
        log.info(getLogMessageWithUsername(user, "Getting user"));
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
