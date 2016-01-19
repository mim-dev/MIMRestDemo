package com.mim_development.android.mimrestdemo.model.services.movie.requests;

import org.apache.commons.lang3.StringUtils;

public class GetMoviesRequest {

    private String genre;
    private String actor;

    public String getGenre() {
        return genre;
    }

    public String getActor() {
        return actor;
    }

    public GetMoviesRequest(String genre, String actor) {
        this.genre = genre;
        this.actor = actor;
    }

    public boolean isValid(){
        return StringUtils.isNotEmpty(getGenre()) && StringUtils.isNotEmpty(getActor());
    }
}
