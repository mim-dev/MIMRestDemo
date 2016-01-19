package com.mim_development.android.mimrestdemo.model.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class MovieEntity {

    private String genre;
    private String title;

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    @JsonCreator
    public MovieEntity(
            @JsonProperty("genre") String genre,
            @JsonProperty("title") String title) {
        this.genre = genre;
        this.title = title;
    }

}
