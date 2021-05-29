package com.app.models.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopTracksResponse {

    private List<TopTrack> items;

    public List<TopTrack> getItems() {
        return items;
    }

    public void setItems(List<TopTrack> items) {
        this.items = items;
    }
}
