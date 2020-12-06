package com.app.components;

import com.app.models.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SpotifyConnectorComponent {

    private static final String BASE_URL = "https://api.spotify.com/v1/";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public List<TopArtist> getUserTopArtists(TimeRange timeRange, String token) {
        String url = BASE_URL + "me/top/artists?time_range=" + timeRange.value;
        ResponseEntity<TopArtistsResponse> responseEntity = REST_TEMPLATE.exchange(url, HttpMethod.GET, getHttpEntity(token), TopArtistsResponse.class);
        if (responseEntity.hasBody()) {
            TopArtistsResponse topArtistsResponse = responseEntity.getBody();
            if (topArtistsResponse != null) {
                return topArtistsResponse.getItems();
            }
        }
        return null;

    }

    public List<TopTrack> getUserTopTracks(TimeRange timeRange, String token) {
        String url = BASE_URL + "me/top/tracks?time_range=" + timeRange.value;
        ResponseEntity<TopTracksResponse> responseEntity = REST_TEMPLATE.exchange(url, HttpMethod.GET, getHttpEntity(token), TopTracksResponse.class);
        if (responseEntity.hasBody()) {
            TopTracksResponse topArtistsResponse = responseEntity.getBody();
            if (topArtistsResponse != null) {
                return topArtistsResponse.getItems();
            }
        }
        return null;
    }

    public Set<String> getUserTopGenres(TimeRange timeRange, String token) {
        List<TopArtist> userTopArtists = getUserTopArtists(timeRange, token);
        if (userTopArtists != null) {
            Set<String> collect = userTopArtists.stream().map(TopArtist::getGenres).flatMap(Collection::stream).collect(Collectors.toSet());
            return collect;
        }

        return null;
    }


    private HttpEntity<?> getHttpEntity(String token) {
        return new HttpEntity<>(getHeaders(token));
    }

    private HttpHeaders getHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        return httpHeaders;
    }
}
