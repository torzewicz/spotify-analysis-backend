package com.app.services;

import com.app.models.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Log4j
public class UserService {

    private final SpotifyConnectorService spotifyConnectorService;

    public User getUserFromContext() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            if (user.getTokenExpires() == null) {
                user.setTokenExpires(ZonedDateTime.now());
            }
            if (ZonedDateTime.now().isAfter(user.getTokenExpires())) {
                try {
                    spotifyConnectorService.refreshAccessToken(user);
                } catch (Exception e) {
                    log.warn("Could not refresh Spotify access token: " + e.getMessage());
                }
            }
            return user;
        } catch (Exception e) {
            log.error("Getting user from context error: " + e.getMessage());
            return null;
        }
    }
}
