package com.claudiawu.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.claudiawu.flixster.adapters.MovieArrayAdapter;
import com.claudiawu.flixster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;
    private SwipeRefreshLayout swipeContainer;
    private AsyncHttpClient client;
    private String url;
    private boolean isRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_movies);
        client = new AsyncHttpClient();
        url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        Log.d("DEBUG","START");

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        // Setup onClickListener to take to another page with more movie info
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movies.get(position);
                Intent i = new Intent(MoviesActivity.this, InfoActivity.class);
                i.putExtra("title", movie.getOriginalTitle());
                i.putExtra("overview",movie.getOverview());
                i.putExtra("rating",movie.getRating());
                i.putExtra("release_date",movie.getRelease_date());
                i.putExtra("backdrop",movie.getBackdropPath());
                startActivity(i);
            }
        });

        // SwipeRefreshLayout
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                Log.d("DEBUG","Refreshing");
                fetchMovies(0);
            }
        });

        // Configure refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchMovies(0);

        // 1. Get the actual movies
        // ArrayList<Movie> movies = com.claudiawu.flixster.Movie.getFakeMovies();

        // 2. Get the ListView that we want to populate
        // ListView lvMovies = (ListView) findViewById(R.id.lvMovies); // Make sure to cast as a ListView

        // 3. Create an ArrayAdapter
        //ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, movies); // Takes data and maps it to the view
        //MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 4. Associate the adapter with the ListView
        //if (lvMovies != null) {
        //    lvMovies.setAdapter(adapter);
        //}
    }

    public void fetchMovies(int page) {
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.clear();
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    if (isRefresh == true) {
                        swipeContainer.setRefreshing(false);
                    }
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

}
