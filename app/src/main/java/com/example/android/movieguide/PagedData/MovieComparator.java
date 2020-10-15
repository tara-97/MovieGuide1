package com.example.android.movieguide.PagedData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.android.movieguide.Networkutil.Movie;

public class MovieComparator extends DiffUtil.ItemCallback<Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem,
                                   @NonNull Movie newItem) {
        // Id is unique.
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem,
                                      @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }
}