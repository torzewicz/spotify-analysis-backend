package com.app.controllers;

import com.app.models.user.User;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Log4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<User> getUser() {
        User user = userService.getUserFromContext();
        log.info("Getting user: " + user.getUsername());
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
