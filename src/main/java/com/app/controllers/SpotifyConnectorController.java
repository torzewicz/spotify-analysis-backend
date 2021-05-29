package com.app.controllers;

import com.app.models.user.User;
import com.app.services.SpotifyConnectorService;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.app.utils.LogUtils.getLogMessageWithUsername;


@RestController
@RequestMapping("/auth")
@Log4j
@RequiredArgsConstructor
public class SpotifyConnectorController {

    private final SpotifyConnectorService spotifyConnectorService;
    private final UserService userService;

    @GetMapping("/connect")
    public User authorize(@RequestParam String code, @RequestParam String redirectUri) {
        User user = userService.getUserFromContext();
        log.info(getLogMessageWithUsername(user, "Getting access token"));
        return spotifyConnectorService.fetchAccessToken(code, user, redirectUri);
    }
}
