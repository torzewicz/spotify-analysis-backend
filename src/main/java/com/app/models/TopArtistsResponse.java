package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopArtistsResponse {

    public List<TopArtist> getItems() {
        return items;
    }

    public void setItems(List<TopArtist> items) {
        this.items = items;
    }

    private List<TopArtist> items;
}
