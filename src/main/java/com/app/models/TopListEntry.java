package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopListEntry {
    private String id;
    private String name;
    private int topScore;
}
