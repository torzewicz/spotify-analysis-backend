package com.app.services;

import com.app.models.spotify.TokenResponse;
import com.app.models.user.User;
import com.app.repositories.user.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

import static com.app.utils.LogUtils.getLogMessageWithUsername;

@Service
@Log4j
public class SpotifyConnectorService {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private final UserRepository userRepository;

    public SpotifyConnectorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${spotify_client_id}")
    private String clientId;

    @Value("${spotify_client_secret}")
    private String clientSecret;

    public User fetchAccessToken(String code, User user, String redirectUri) {
        log.info("Connecting user: " + user.getUsername() + " with Spotify account.");

        if (user.isConnectedToSpotify() || user.isConnectingToSpotify()) {
            log.warn(getLogMessageWithUsername(user, "User already connected or connecting now"));
            user.setPassword(null);
            return user;
        }

        user.setConnectingToSpotify(true);
        userRepository.save(user);

        if (code == null || code.equalsIgnoreCase("")) {
            log.warn(getLogMessageWithUsername(user, "Code is null"));
            user.setPassword(null);
            return user;
        }

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", redirectUri);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        httpHeaders.setBasicAuth(clientId, clientSecret);

        ResponseEntity<TokenResponse> exchange = REST_TEMPLATE.exchange("https://accounts.spotify.com/api/token", HttpMethod.POST, new HttpEntity<>(map, httpHeaders), TokenResponse.class);
        TokenResponse body = exchange.getBody();

        if (exchange.hasBody() && body != null) {
            user.setAccessToken(body.getAccessToken());
            user.setRefreshToken(body.getRefreshToken());
            user.setConnectedToSpotify(true);
            user.setConnectingToSpotify(false);
            user.setTokenExpires(ZonedDateTime.now().plusSeconds(body.getExpiresIn()));
            User save = userRepository.save(user);
            save.setPassword(null);
            return save;

        } else {
            user.setPassword(null);
            return user;
        }
    }

    public void refreshAccessToken(User user) {
        log.info(getLogMessageWithUsername(user, "Refreshing spotify access token"));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", user.getRefreshToken());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        httpHeaders.setBasicAuth(clientId, clientSecret);

        ResponseEntity<TokenResponse> exchange = REST_TEMPLATE.exchange("https://accounts.spotify.com/api/token", HttpMethod.POST, new HttpEntity<>(map, httpHeaders), TokenResponse.class);
        TokenResponse body = exchange.getBody();

        if (exchange.hasBody() && body != null) {
            user.setAccessToken(body.getAccessToken());
            user.setTokenExpires(ZonedDateTime.now().plusSeconds(body.getExpiresIn()));
            User save = userRepository.save(user);
            save.setPassword(null);
            log.info("Token refreshed!");
        } else {
            log.warn("Token not refreshed");
        }
    }

}
