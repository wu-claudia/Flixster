package com.claudiawu.flixster;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends ArrayAdapter<Movie>{

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context,R.layout.item_movie,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false); // Defined custom layout ourselves
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ImageView poster = (ImageView) convertView.findViewById(R.id.poster);
        // Populate the data into the template view using the data object
        tvTitle.setText(movie.title);

        // Debugging
        Log.d("MoviesAdapter", "Position:" + position);

        // poster.set
        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        Picasso.with(getContext()).load(imageUri).into(poster);

        // Return the completed view to render on screen
        return convertView;
    }
}
