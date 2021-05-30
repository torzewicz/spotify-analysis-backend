package com.app.controllers;

import com.app.components.SpotifyConnectorComponent;
import com.app.models.spotify.TimeRange;
import com.app.models.spotify.TopArtist;
import com.app.models.spotify.TopTrack;
import com.app.models.user.User;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static com.app.utils.LogUtils.getLogMessageWithUsername;

@RestController
@RequestMapping("/top")
@Log4j
@RequiredArgsConstructor
public class TopController {

    private final SpotifyConnectorComponent spotifyConnectorComponent;
    private final UserService userService;


    @GetMapping
    @RequestMapping("/artists")
    public List<TopArtist> getTopArtists(@RequestParam(required = false) TimeRange timeRange, @RequestParam(required = false) Integer limit) {
        User user = userService.getUserFromContext();
        log.info(getLogMessageWithUsername(user, "Getting top artists with time range: " + timeRange + " and limit: " + limit));
        return spotifyConnectorComponent.getUserTopArtists(timeRange == null ? TimeRange.LONG_TERM : timeRange, limit == null ? 25 : limit, user.getAccessToken());
    }

    @GetMapping
    @RequestMapping("/tracks")
    public List<TopTrack> getTopTracks(@RequestParam(required = false) TimeRange timeRange, @RequestParam(required = false) Integer limit) {
        User user = userService.getUserFromContext();
        log.info(getLogMessageWithUsername(user, "Getting top tracks with time range: " + timeRange + " and limit: " + limit));
        return spotifyConnectorComponent.getUserTopTracks(timeRange == null ? TimeRange.LONG_TERM : timeRange, limit == null ? 25 : limit, user.getAccessToken());
    }

    @GetMapping
    @RequestMapping("/genres")
    public Set<String> getTopGenres(@RequestParam(required = false) TimeRange timeRange) {
        User user = userService.getUserFromContext();
        return spotifyConnectorComponent.getUserTopGenres(timeRange == null ? TimeRange.LONG_TERM : timeRange, user.getAccessToken());
    }

}
