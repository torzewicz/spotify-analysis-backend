package com.app.controllers;

import com.app.models.user.User;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsers() {
        User user = userService.getUserFromContext();
        List<User> users = userService.getUsers();
        log.info(getLogMessageWithUsername(user, "Request list of users"));
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(String username) {
        User user = userService.getUserFromContext();
        userService.deleteUser(username);
        log.info(getLogMessageWithUsername(user, "Delete user " + username));
        return ResponseEntity.ok().build();
    }
}
