package br.com.rafaelportela.espresso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;


public class MainActivity extends Activity {

    private TextView label;
    private ProgressBar loading;

    interface Server {

        @GET("/")
        void hello(Callback<Response> callback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label = (TextView) findViewById(R.id.server_response);
        loading = (ProgressBar) findViewById(R.id.loading);

        Server server = new RestAdapter.Builder()
                .setEndpoint("http://10.0.3.2:4567")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(Server.class);

        server.hello(new Callback<Response>() {
            @Override
            public void success(Response response, Response ignore) {
                try {

                    String helloFromServer = new BufferedReader(
                            new InputStreamReader(response.getBody().in()))
                                .readLine();

                    label.setText(helloFromServer);

                } catch (IOException e) {
                    label.setText("something went wrong accessing the server: " + e.getMessage());
                }
                loading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }
}