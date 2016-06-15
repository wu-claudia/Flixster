package com.claudiawu.flixster;

import java.util.ArrayList;

public class Movie {
    public String title;
    public String posterUrl;
    public int rating;

    public Movie(String title, String posterUrl, int rating) {
        this.posterUrl = posterUrl;
        this.title = title;
        this.rating = rating;
    }

    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        for(int i = 0; i < 60; i++) {
            movies.add(new Movie("The Social Network", "",75));
            movies.add(new Movie("The Internship","",50));
            movies.add(new Movie("The Lion King","",100));
        }
        return movies;
    }

    @ Override
    public String toString() {
        return title + "-" + rating;
    }
}
