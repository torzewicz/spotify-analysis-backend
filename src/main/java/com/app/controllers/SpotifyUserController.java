package com.app.controllers;

import com.app.components.SpotifyConnectorComponent;
import com.app.models.spotify.CurrentPlayback;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/spotify-user")
@Log4j
@RequiredArgsConstructor
public class SpotifyUserController {

    private final SpotifyConnectorComponent spotifyConnectorComponent;
    private final UserService userService;

    @GetMapping("/current")
    public ResponseEntity<CurrentPlayback> getCurrentPlayback() {
        return ResponseEntity.ok(spotifyConnectorComponent.getUsersCurrentPlayback(userService.getUserFromContext().getAccessToken()));
    }
}
