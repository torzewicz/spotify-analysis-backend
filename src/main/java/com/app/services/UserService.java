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

    public void verifyUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(u -> {
            u.setVerified(true);
        });
        userRepository.save(user.get());
    }

    public boolean checkCodeByEmail(String email, String code) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getVerificationCode().equals(code)) {
            return true;
        }
        return false;
    }

    public boolean checkVerificationByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getVerified()) {
            return true;
        }
        return false;
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
}
