package com.nord.shorter.model;

public class Url {
    private String originalUrl;

    public Url() {
    }

    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
