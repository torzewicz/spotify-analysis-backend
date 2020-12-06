package com.app.controllers;

import com.app.components.SpotifyConnectorComponent;
import com.app.models.TimeRange;
import com.app.models.TopArtist;
import com.app.models.TopTrack;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/top")
@Log4j
public class TopController {

    private final SpotifyConnectorComponent spotifyConnectorComponent;


    public TopController(SpotifyConnectorComponent spotifyConnectorComponent) {
        this.spotifyConnectorComponent = spotifyConnectorComponent;
    }

    @GetMapping
    @RequestMapping("/artists")
    public List<TopArtist> getTopArtists(@RequestParam String token, @RequestParam(required = false) TimeRange timeRange, @RequestParam(required = false) Integer limit) {
        log.info("Gettting top artists with time ramge: " + timeRange);
        return spotifyConnectorComponent.getUserTopArtists(timeRange == null ? TimeRange.LONG_TERM : timeRange, limit == null ? 25 : limit, token);
    }

    @GetMapping
    @RequestMapping("/tracks")
    public List<TopTrack> getTopTracks(@RequestParam String token, @RequestParam(required = false) TimeRange timeRange, @RequestParam(required = false) Integer limit) {
        return spotifyConnectorComponent.getUserTopTracks(timeRange == null ? TimeRange.LONG_TERM : timeRange, limit == null ? 25 : limit, token);
    }

    @GetMapping
    @RequestMapping("/genres")
    public Set<String> getTopGenres(@RequestParam String token, @RequestParam(required = false) TimeRange timeRange) {
        return spotifyConnectorComponent.getUserTopGenres(timeRange == null ? TimeRange.LONG_TERM : timeRange, token);
    }

}
