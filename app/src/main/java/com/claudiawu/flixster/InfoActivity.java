package com.claudiawu.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.claudiawu.flixster.adapters.MovieArrayAdapter;
import com.claudiawu.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class InfoActivity extends YouTubeBaseActivity {

    String title;
    String overview;
    String backdrop;
    String release_date;
    double rating;
    int movie_id = getIntent().getIntExtra("id",0);
    private AsyncHttpClient client;
    private String url;
    Movie trailers;
    MovieArrayAdapter trailerAdapter;
    String videoKey;
    ImageView backdropTrailer;


    public static final String YT_API_KEY = "AIzaSyDgk3XW7q_2nNHsVkECHJ7DZTmRRcsgoQ8";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.more_info);
        client = new AsyncHttpClient();
        url = "https://api.themoviedb.org/3/movie/" + movie_id + "videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        backdropTrailer = (ImageView) findViewById(R.id.ivPoster);
        //trailers = new ArrayList<>();
        //trailerAdapter = new MovieArrayAdapter(this, trailers);

        backdropTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = getIntent();
                Intent detail = new Intent(InfoActivity.this,VideoActivity.class);
                detail.putExtra("video_key",)
            }
        });


        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize(YT_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo(videoKey);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

        title = getIntent().getStringExtra("title");
        release_date = "Release Date: " + getIntent().getStringExtra("release_date");
        overview = getIntent().getStringExtra("overview");
        backdrop = getIntent().getStringExtra("backdrop");
        rating = getIntent().getDoubleExtra("rating",0);

        TextView name = (TextView) findViewById(R.id.tvTitle);
        TextView date = (TextView) findViewById(R.id.tvDate);
        ImageView image = (ImageView) findViewById(R.id.ivPoster);
        RatingBar vote = (RatingBar) findViewById(R.id.rbRating);
        TextView summary = (TextView) findViewById(R.id.tvOverview);

        name.setText(title);
        summary.setText(overview);
        vote.setRating((float)rating/2);
        date.setText(release_date);
        Picasso.with(this.getApplicationContext()).load(backdrop).into(image);
        summary.setMovementMethod(new ScrollingMovementMethod());

    }

    public void getTranslation(int page) {
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    trailers.clear();
                    trailers.addAll(Movie.fromJSONArray(movieJsonResults));
                    trailerAdapter.notifyDataSetChanged();
                    //Log.d("DEBUG", movies.toString());
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
