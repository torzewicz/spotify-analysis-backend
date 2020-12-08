package com.app.models;

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
public class CurrentPlayback {

    private Device device;
    @JsonProperty("shuffle_state")
    private boolean shuffleState;
    @JsonProperty("repeat_state")
    private String repeatState;
    private Long timestamp;
    private Context context;
    @JsonProperty("progress_ms")
    private Integer progressMs;
    private Item item;
    @JsonProperty("currently_playing_type")
    private String currentlyPlayingType;
    private Action actions;
    @JsonProperty("is_playing")
    private boolean isPlaying;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Item {
        private Album album;
        private List<Artist> artists;
        @JsonProperty("available_markets")
        private List<String> availableMarkets;
        @JsonProperty("duration_ms")
        private Integer durationMs;
        @JsonProperty("external_urls")
        private ExternalUrl externalUrls;
        private String name;
        private Integer popularity;
        @JsonProperty("preview_url")
        private String previewUrl;
    }

}
