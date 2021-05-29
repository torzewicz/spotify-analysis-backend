package com.app.models.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    private String scope;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
