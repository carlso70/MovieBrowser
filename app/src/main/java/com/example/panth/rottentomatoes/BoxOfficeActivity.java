package com.example.panth.rottentomatoes;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoxOfficeActivity extends AppCompatActivity {

    RottenTomatoesClient client;
    private ListView lvMovies;
    private BoxOfficeMovieAdapter movieAdapter;
    public static final String MOVIE_DETAIL_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);

        lvMovies = (ListView)findViewById(R.id.lvMovies);
        ArrayList<BoxOfficeMovie> movies = new ArrayList<BoxOfficeMovie>();
        movieAdapter = new BoxOfficeMovieAdapter(this, movies);
        lvMovies.setAdapter(movieAdapter);

        // Fetch the data remotely
        fetchBoxOfficeMovies();
        setupMovieSelectedListener();
    }

    // Executes an API call to the BoxOfficeEndpoint
    // Converts into an array of movie objects and then places them into the movie adapter
    private void fetchBoxOfficeMovies()
    {
        client = new RottenTomatoesClient();
        client.getBoxOfficeMovies(new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject responseBody)
            {
                JSONArray items = null;
                try
                {
                    items = responseBody.getJSONArray("movies");
                    // Parse json array into the model objects
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
                    // Load model objects into the adpater
                    for (BoxOfficeMovie movie : movies)
                    {
                        movieAdapter.add(movie);
                    }
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

    public void setupMovieSelectedListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // launch the detail view passing movie as an extra
                Intent intent = new Intent(BoxOfficeActivity.this, BoxOfficeDetailActivity.class);
                intent.putExtra(MOVIE_DETAIL_KEY, movieAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
