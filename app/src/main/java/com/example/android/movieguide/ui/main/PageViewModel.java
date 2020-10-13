package com.example.android.movieguide.ui.main;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.movieguide.DataRepository;
import com.example.android.movieguide.Networkutil.Movie;



import java.util.List;


public class PageViewModel extends ViewModel {
    private static final String TAG = "PageViewModel";
    private DataRepository mRepository;
    private LiveData<List<Movie>> moviesList;
    private LiveData<Movie> singleMovie;
    public PageViewModel(){
        super();
        mRepository=DataRepository.getInstance();
        moviesList=mRepository.getAllMovies();
        singleMovie=mRepository.getMovie();
    }


    public LiveData<List<Movie>> getMovieList() {
        return moviesList;
    }

    public void setPath(String path) {
        mRepository.setPath(path);
    }
    public LiveData<Movie> getMovie(){
        return singleMovie;
    }
}


