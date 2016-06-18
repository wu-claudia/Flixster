package com.claudiawu.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
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
    int movie_id;
    private AsyncHttpClient client;
    private String url;
    //ArrayList<Movie> videos;
    //MovieArrayAdapter videoAdapter;
    ImageView backdropTrailer;
    String video_key;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.more_info);
        client = new AsyncHttpClient();
        movie_id = getIntent().getIntExtra("movie_id",0);
        Log.d("Movie ID: ", "" + movie_id);
        url = "https://api.themoviedb.org/3/movie/" + movie_id + "/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        backdropTrailer = (ImageView) findViewById(R.id.ivPoster);
        //videos = new ArrayList<>();
        //videoAdapter = new MovieArrayAdapter(this, videos);

        backdropTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoActivity.this, VideoActivity.class);
                i.putExtra("video_key", video_key);
                startActivity(i);
            }
        });

        fetchMovies(0);

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

    public void fetchMovies(int page) {
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    JSONObject result = movieJsonResults.getJSONObject(0);
                    video_key = result.getString("key");
                    //videos.clear();
                    //videos.addAll(Movie.fromJSONArray(movieJsonResults));
                    //videoAdapter.notifyDataSetChanged();
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
