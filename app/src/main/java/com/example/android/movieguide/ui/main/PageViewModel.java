package com.example.android.movieguide.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.android.movieguide.Networkutil.Movie;
import com.example.android.movieguide.PagedData.PagedMovieDataSource;

import io.reactivex.rxjava3.core.Observable;


public class PageViewModel extends ViewModel {
    private static final String TAG = "PageViewModel";
    private Observable<PagingData<Movie>> movieObserverable;

    public PageViewModel() {
        super();
    }


    public void setPath(String path) {
        Pager<Integer, Movie> pager = new Pager(
                new PagingConfig(20),
                () -> new PagedMovieDataSource(path));
        movieObserverable = PagingRx.getObservable(pager);

    }

    public Observable<PagingData<Movie>> getMovieData() {
        return movieObserverable;
    }
}


