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

    public User fetchAccessToken(String code, User user) {
        log.info("Connecting user: " + user.getUsername() + " with Spotify account.");
        if (code == null || code.equalsIgnoreCase("")) {
            return null;
        }

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", "http://localhost:3000");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        httpHeaders.setBasicAuth(clientId, clientSecret);

        ResponseEntity<TokenResponse> exchange = REST_TEMPLATE.exchange("https://accounts.spotify.com/api/token", HttpMethod.POST, new HttpEntity<>(map, httpHeaders), TokenResponse.class);
        TokenResponse body = exchange.getBody();

        if (exchange.hasBody() && body != null) {
            user.setAccessToken(body.getAccessToken());
            user.setRefreshToken(body.getRefreshToken());
            user.setConnectedToSpotify(true);
            user.setTokenExpires(ZonedDateTime.now().plusSeconds(body.getExpiresIn()));
            User save = userRepository.save(user);
            save.setPassword(null);
            return save;

        } else return null;
    }

    public void refreshAccessToken(User user) {
        log.info("Refreshing spotify access token for: " + user.getUsername());
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
