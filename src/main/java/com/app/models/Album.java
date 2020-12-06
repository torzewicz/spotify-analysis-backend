package com.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private String name;
    @JsonProperty("release_date")
    private String releaseDate;
    private List<Artist> artists;
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    private String uri;
    private String type;
    @JsonProperty("total_tracks")
    private Integer totalTracks;
    @JsonProperty("album_type")
    private String albumType;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
}
