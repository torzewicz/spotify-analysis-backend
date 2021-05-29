package com.app.models.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private Integer height;
    private Integer width;
    private String url;
}
