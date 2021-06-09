package com.app.services;

import com.app.models.user.User;
import com.app.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j
public class UserService {

    private final SpotifyConnectorService spotifyConnectorService;
    private final UserRepository userRepository;

    public User getUserFromContext() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            refreshSpotifyToken(user);
            return user;
        } catch (Exception e) {
            log.error("Getting user from context error: " + e.getMessage());
            return null;
        }
    }

    public void refreshSpotifyToken(User user) {
        if (user.getTokenExpires() == null) {
            user.setTokenExpires(ZonedDateTime.now());
            try {
                spotifyConnectorService.refreshAccessToken(user);
            } catch (Exception e) {
                log.warn("Could not refresh Spotify access token: " + e.getMessage());
            }
        }
        if (ZonedDateTime.now().isAfter(user.getTokenExpires())) {
            try {
                spotifyConnectorService.refreshAccessToken(user);
            } catch (Exception e) {
                log.warn("Could not refresh Spotify access token: " + e.getMessage());
            }
        }
    }

    public void verifyUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(u -> {
            u.setVerified(true);
        });
        userRepository.save(user.get());
    }

    public boolean checkCodeByEmail(String email, String code) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getVerificationCode().equals(code);
    }

    public boolean checkVerificationByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() &&( user.get().getVerified() == null || user.get().getVerified());
    }

    public boolean checkExistenceByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean checkExistenceByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public User getUserByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            refreshSpotifyToken(byUsername.get());
            return byUsername.get();
        }
        return null;
    }

    public void deleteUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(value -> value.setEnabled(!user.get().getEnabled()));
        user.ifPresent(userRepository::save);
    }
}
