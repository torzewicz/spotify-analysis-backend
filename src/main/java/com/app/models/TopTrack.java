package com.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
public class TopTrack {

    private String name;
    private List<Artist> artists;
    @JsonProperty("preview_url")
    private String previewUrl;
    private String uri;
    private String type;
    private Integer popularity;
    @JsonProperty("is_local")
    private Boolean isLocal;
    private String id;
    private String href;
    private Boolean explicit;
    @JsonProperty("duration_ms")
    private Integer duration;
    private Album album;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;

    private Integer polishTopListRank;
}
