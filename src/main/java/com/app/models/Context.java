package com.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Context {

    @JsonProperty("external_urls")
    private ExternalUrl externalUrls;
    private String href;
    private String type;
    private String uri;
}
