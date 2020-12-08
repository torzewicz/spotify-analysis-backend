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
public class Device {

    private String id;
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty("is_private_session")
    private boolean isPrivateSession;
    @JsonProperty("is_restricted")
    private boolean isRestricted;
    private String name;
    private String type;
    @JsonProperty("volume_percent")
    private Integer volumePercent;
}
