package com.app.controllers;

import com.app.models.TokenResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/auth")
@Log4j
public class SpotifyConnectorController {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    @Value("${spotify_client_id}")
    private String clientId;

    @Value("${spotify_client_secret}")
    private String clientSecret;

    @GetMapping("/connect")
    public TokenResponse authorize(@RequestParam String code) {
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

        return exchange.getBody();

    }
}
