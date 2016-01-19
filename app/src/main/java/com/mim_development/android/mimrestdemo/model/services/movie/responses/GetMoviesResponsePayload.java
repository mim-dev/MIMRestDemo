package com.mim_development.android.mimrestdemo.model.services.movie.responses;


import com.mim_development.android.mimrest.model.services.base.Payload;
import com.mim_development.android.mimrestdemo.model.entity.MovieEntity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class GetMoviesResponsePayload implements Payload {

    private List<MovieEntity> movies;

    public List<MovieEntity> getMovies(){
        return movies;
    }

    @JsonCreator
    public GetMoviesResponsePayload(
            List<MovieEntity> movies){
        this.movies = movies;
    }

}
