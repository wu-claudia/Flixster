package com.claudiawu.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity{

    String title;
    String overview;
    String backdrop;
    String release_date;
    double rating;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.more_info);

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
}
