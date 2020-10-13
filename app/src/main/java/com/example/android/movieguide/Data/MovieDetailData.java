package com.example.android.movieguide.Data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieguide.Constants;
import com.example.android.movieguide.POJO.Cast;
import com.example.android.movieguide.POJO.Credits;
import com.example.android.movieguide.POJO.MovieDetail;
import com.example.android.movieguide.Networkutil.HttpCall;
import com.example.android.movieguide.POJO.Review;
import com.example.android.movieguide.POJO.ReviewList;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailData {
    private static final String TAG = "MovieDetailData";
    private static final MovieDetailData mInstance=null;
    private MutableLiveData<MovieDetail> mMovieDetailLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Review>> mReviewsLiveData=new MutableLiveData<>();
    private MutableLiveData<List<Cast>> mCastLiveData=new MutableLiveData<>();

    private void getMovieFullLive(int id){
        HttpCall.getRequest().getMovieDetail(id, Constants.getApiKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MovieDetail movieDetail) {
                        mMovieDetailLiveData.setValue(movieDetail);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getMovieCommentsLive(int id){
        HttpCall.getRequest().getMovieReviews(id,Constants.getApiKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReviewList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ReviewList reviewList) {
                        Log.d(TAG, "onNext:Called ");
                        mReviewsLiveData.setValue(reviewList.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getMovieCastListLive(int id){
        HttpCall.getRequest().getMovieCredits(id,Constants.getApiKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Credits>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Credits credits) {
                        mCastLiveData.setValue(credits.getCast());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<MovieDetail> getMovieDetailData(){
        return mMovieDetailLiveData;
    }
    public LiveData<List<Review>> getMovieReviews(){return mReviewsLiveData;}
    public LiveData<List<Cast>> getMovieCast(){return mCastLiveData;}

    public void loadData(int id){
        getMovieFullLive(id);
        getMovieCastListLive(id);
        getMovieCommentsLive(id);
    }
    public static MovieDetailData getInstance(){
        if(mInstance == null){
            return new MovieDetailData();
        }
        return mInstance;
    }


}
