package com.stackroute.muzixService.exceptions;

public class TrackNotAvailable extends Exception {
    private String message;
    public TrackNotAvailable() {
    }

    public TrackNotAvailable(String message) {
        super(message);
        this.message = message;
    }
}