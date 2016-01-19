package com.mim_development.android.mimrestdemo.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mim_development.android.mimrest.model.services.base.service.callback.ServiceCallback;
import com.mim_development.android.mimrest.model.services.base.service.response.ServiceErrorResponse;
import com.mim_development.android.mimrest.model.services.base.service.response.ServiceSuccessResponse;
import com.mim_development.android.mimrestdemo.R;
import com.mim_development.android.mimrestdemo.model.services.movie.MovieSvc;
import com.mim_development.android.mimrestdemo.model.services.movie.responses.GetMoviesResponsePayload;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UUID getMoviesRequestIdentifier;
    private ProgressDialog getMoviesProgressDialog;

    private class GetMoviesCallback implements ServiceCallback {

        @Override
        public void success(UUID operationId, ServiceSuccessResponse response) {

            if (operationId.equals(getMoviesRequestIdentifier)) {
                GetMoviesResponsePayload responsePayload
                        = response.getPayload(GetMoviesResponsePayload.class);
                if (getMoviesProgressDialog != null) {
                    getMoviesProgressDialog.dismiss();
                }

                Toast.makeText(
                        MainActivity.this,
                        "Found " + responsePayload.getMovies().size() + " movies.",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void error(UUID operationId, ServiceErrorResponse response) {
            if (operationId.equals(getMoviesRequestIdentifier)) {
                if (getMoviesProgressDialog != null) {
                    getMoviesProgressDialog.dismiss();
                }

                Toast.makeText(
                        MainActivity.this,
                        "Error " + response.getException().toString() + ".",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private EditText genreEditText;
    private EditText actorEditText;
    private Button requestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genreEditText = (EditText) findViewById(R.id.activity_main_edit_text_genre);
        actorEditText = (EditText) findViewById(R.id.activity_main_edit_text_actor);

        requestButton = (Button) findViewById(R.id.activity_main_button_fetch);
        requestButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == requestButton) {
            getMoviesRequestIdentifier = MovieSvc.getInstance().getMovies(
                    genreEditText.getText().toString(),
                    actorEditText.getText().toString(),
                    new GetMoviesCallback());

            getMoviesProgressDialog = ProgressDialog.show(
                    this,
                    getResources().getString(R.string.activity_Main_get_movies_progress_dialog_title),
                    getResources().getString(R.string.activity_Main_get_movies_progress_dialog_content));
        }
    }
}
