package com.claudiawu.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.claudiawu.flixster.R;
import com.claudiawu.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView name;
        TextView overview;
        ImageView image;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);
        ViewHolder viewHolder;

        // check the existing view being reused
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.poster);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // find the image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.poster);

        // clear out image from convertView
        //ivImage.setImageResource(0);

        //populate data
        viewHolder.name.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (isLandscape) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).transform(new RoundedCornersTransformation(10, 10)).placeholder(R.drawable.clapboard).into(ivImage);
        } else {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(10, 10)).placeholder(R.drawable.clapboard).into(ivImage);
        }

        // return the view
        return convertView;
    }
}
