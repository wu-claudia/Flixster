package com.claudiawu.flixster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // 1. Get the actual movies
        ArrayList<Movie> movies = Movie.getFakeMovies();

        // 2. Get the ListView that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies); // Make sure to cast as a ListView

        // 3. Create an ArrayAdapter
        //ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, movies); // Takes data and maps it to the view
        MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 4. Associate the adapter with the ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }
    }
}
