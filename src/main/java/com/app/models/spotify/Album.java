package com.app.models.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Image> images;
}
