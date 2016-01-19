package com.mim_development.android.mimrestdemo.model.services.movie;


import com.mim_development.android.mimrest.model.services.base.service.Service;
import com.mim_development.android.mimrest.model.services.base.service.callback.ServiceCallback;
import com.mim_development.android.mimrestdemo.model.services.movie.operations.GetMoviesOperation;
import com.mim_development.android.mimrestdemo.model.services.movie.requests.GetMoviesRequest;

import java.util.UUID;

public class MovieSvc extends Service {

    // singleton plumbing
    private static MovieSvc instance = new MovieSvc();
    public static MovieSvc getInstance(){return instance;}

    public UUID getMovies(String genre, String actor, ServiceCallback callback){
        GetMoviesOperation op = new GetMoviesOperation(
                new GetMoviesRequest(genre, actor),
                getOperationCallback());

        return invokeOperation(op, callback);
    }

}
