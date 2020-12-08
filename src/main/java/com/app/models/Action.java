package com.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    private Disallow disallows;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Disallow {
        private boolean resuming;
    }
}
