package com.app.controllers;

import com.app.components.SpotifyConnectorComponent;
import com.app.models.spotify.TimeRange;
import com.app.models.spotify.TopArtist;
import com.app.models.spotify.TopTrack;
import com.app.models.user.User;
import com.app.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.app.utils.LogUtils.getLogMessageWithUsername;

@RestController
@RequestMapping("/admin/top")
@Log4j
@RequiredArgsConstructor
public class TopForAdminController {

    private final SpotifyConnectorComponent spotifyConnectorComponent;
    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/artists/{username}")
    public List<TopArtist> getTopArtists(@RequestParam(required = false) TimeRange timeRange,
                                         @RequestParam(required = false) Integer limit,
                                         @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        log.info(getLogMessageWithUsername(user, "Admin is getting top artists with time range: " + timeRange + " and limit: " + limit));
        return spotifyConnectorComponent.getUserTopArtists(timeRange == null ? TimeRange.LONG_TERM : timeRange, limit == null ? 25 : limit, user.getAccessToken());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/tracks/{username}")
    public List<TopTrack> getTopTracks(@RequestParam(required = false) TimeRange timeRange,
                                       @RequestParam(required = false) Integer limit,
                                       @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        log.info(getLogMessageWithUsername(user, "Admin is getting top tracks with time range: " + timeRange + " and limit: " + limit));
        return spotifyConnectorComponent.getUserTopTracks(timeRange == null ? TimeRange.LONG_TERM : timeRange, limit == null ? 25 : limit, user.getAccessToken());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/genres/{username}")
    public Set<String> getTopGenres(@RequestParam(required = false) TimeRange timeRange, @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return spotifyConnectorComponent.getUserTopGenres(timeRange == null ? TimeRange.LONG_TERM : timeRange, user.getAccessToken());
    }

}
