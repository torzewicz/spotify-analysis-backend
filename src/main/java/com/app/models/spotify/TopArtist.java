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
public class TopArtist {

    private String name;
    private Integer popularity;
    private String type;
    private String uri;
    private List<String> genres;
    private String href;
    private String id;
    @JsonProperty(value = "external_urls")
    private ExternalUrl externalUrls;
    private List<Image> images;
    private Followers followers;

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
