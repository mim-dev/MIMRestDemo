package com.mim_development.android.mimrestdemo.model.services.movie.operations;

import com.mim_development.android.mimrest.MIMRest;
import com.mim_development.android.mimrest.model.services.base.http.connection.HttpConnection;
import com.mim_development.android.mimrest.model.services.base.http.request.HttpRequest;
import com.mim_development.android.mimrest.model.services.base.operation.HttpVerbs;
import com.mim_development.android.mimrest.model.services.base.operation.OperationResultPayloadProcessor;
import com.mim_development.android.mimrest.model.services.base.operation.ServiceOperation;
import com.mim_development.android.mimrest.model.services.base.operation.callback.OperationCallback;
import com.mim_development.android.mimrest.model.services.base.operation.response.OperationSuccessResponse;
import com.mim_development.android.mimrestdemo.model.services.movie.requests.GetMoviesRequest;
import com.mim_development.android.mimrestdemo.model.services.movie.responses.GetMoviesResponsePayload;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GetMoviesOperation extends ServiceOperation{

    private static final String SERVICE_ACTION = "movie/movies";

    private static final String ACTOR_PARAMETER_NAME = "actor";
    private static final String GENRE_PARAMETER_NAME = "genre";

    private Map<String, String> plainTextParameters;

    public GetMoviesOperation(GetMoviesRequest request, OperationCallback callback){
        super(callback);

        if(request == null){
            throw new IllegalArgumentException("The 'request' argument cannot be null.");
        }

        if(!request.isValid()){
            throw new IllegalArgumentException("The 'request' argument must have an actor and genre specified.");
        }

        plainTextParameters = new HashMap<>(2);
        plainTextParameters.put(ACTOR_PARAMETER_NAME, request.getActor());
        plainTextParameters.put(GENRE_PARAMETER_NAME, request.getGenre());
    }

    @Override
    protected OperationResultPayloadProcessor getOperationResultPayloadProcessor() {
        return new OperationResultPayloadProcessor() {
            @Override
            public OperationSuccessResponse processResponse(
                    UUID identifier, byte[] responsePayload) throws Exception {

                String response = new String(responsePayload);

                ObjectMapper mapper = new ObjectMapper();
                GetMoviesResponsePayload payload = mapper.readValue(
                        responsePayload,
                        0,
                        responsePayload.length,
                        GetMoviesResponsePayload.class);

                OperationSuccessResponse successResponse = new OperationSuccessResponse(
                        identifier,
                        GetMoviesResponsePayload.class,
                        payload);

                return successResponse;
            }
        };
    }

    @Override
    protected HttpRequest getHttpRequest() {
        MIMRest restLib = MIMRest.getInstance();

        return new HttpRequest(
                new HttpConnection(
                        restLib.isServerSecure(),
                        restLib.getServer(),
                        restLib.getServerApplicationPath(),
                        SERVICE_ACTION),
                HttpVerbs.GET,
                buildRequestHeaders(),
                restLib.getConnectionTimeOutMillis(),
                plainTextParameters);
    }
}
