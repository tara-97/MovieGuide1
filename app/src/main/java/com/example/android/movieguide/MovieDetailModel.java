package com.example.android.movieguide;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.movieguide.Data.MovieDetailData;
import com.example.android.movieguide.POJO.Cast;
import com.example.android.movieguide.POJO.MovieDetail;
import com.example.android.movieguide.POJO.Review;

import java.util.List;

public class MovieDetailModel extends ViewModel {

    private LiveData<MovieDetail> movieDetails;
    private LiveData<List<Review>> movieReviews;
    private LiveData<List<Cast>> movieCasts;
    private MovieDetailData movieDetailData;
    public MovieDetailModel() {
        super();
        movieDetailData=MovieDetailData.getInstance();
        movieDetails=movieDetailData.getMovieDetailData();
        movieCasts=movieDetailData.getMovieCast();
        movieReviews=movieDetailData.getMovieReviews();
    }

    public LiveData<List<Review>> getMovieReviews() {
        return movieReviews;
    }

    public LiveData<List<Cast>> getMovieCasts() {
        return movieCasts;
    }

    public LiveData<MovieDetail> getMovieDetails() {
        return movieDetails;
    }

    public void requestData(int id){
        movieDetailData.loadData(id);
    }
}
