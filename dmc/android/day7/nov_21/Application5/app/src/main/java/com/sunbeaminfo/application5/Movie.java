package com.sunbeaminfo.application5;

import androidx.annotation.NonNull;

public class Movie {
    String title;
    String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    @NonNull
    @Override
    public String toString() {
        return "Movie [title: " + this.title + ", genre: " + this.genre + "]";
    }
}
