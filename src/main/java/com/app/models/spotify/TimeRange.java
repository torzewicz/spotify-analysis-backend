package com.app.models.spotify;

public enum TimeRange {

    LONG_TERM("long_term"),
    MEDIUM_TERM("medium_term"),
    SHORT_TERM("short_term");

    public String value;

    private TimeRange(String value) {
        this.value = value;
    }

}
