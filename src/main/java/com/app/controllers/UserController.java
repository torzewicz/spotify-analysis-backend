package com.app.controllers;

import com.app.components.SpotifyConnectorComponent;
import com.app.models.CurrentPlayback;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Log4j
public class UserController {

    private final SpotifyConnectorComponent spotifyConnectorComponent;

    public UserController(SpotifyConnectorComponent spotifyConnectorComponent) {
        this.spotifyConnectorComponent = spotifyConnectorComponent;
    }

    @GetMapping("/current")
    public ResponseEntity<CurrentPlayback> getCurrentPlayback(@RequestParam String token) {
        return ResponseEntity.ok(spotifyConnectorComponent.getUsersCurrentPlayback(token));
    }
}
