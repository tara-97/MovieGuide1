package com.example.android.movieguide;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieguide.Networkutil.ApiClient;
import com.example.android.movieguide.Networkutil.HttpCall;
import com.example.android.movieguide.Networkutil.Movie;
import com.example.android.movieguide.Networkutil.Movies;
import com.example.android.movieguide.POJO.Genre;
import com.example.android.movieguide.POJO.MovieDetail;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private static final DataRepository repoInstance=null;

    private MutableLiveData<List<Movie>> allMovies = new MutableLiveData<>();
    private MutableLiveData<Movie> movieOnly=new MutableLiveData<>();
    private MutableLiveData<MovieDetail> movieDetail=new MutableLiveData<>();

    public void setPath(String path) {
        mMovieObservable(path).subscribeOn(Schedulers.io())
                .flatMap((Function<Movie, ObservableSource<Movie>>) movie -> {
                    movie.setPosterPath("https://image.tmdb.org/t/p/w500"+movie.getPosterPath());
                    return getGenreObservable(movie);

                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Movie>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Movie movie) {
                movieOnly.setValue(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
    private Observable<Movie> mMovieObservable(String path){
        ApiClient mClient = HttpCall.getRequest();
        Observable<Movies> data=mClient.getMovieByTags(path,"e258f83c03db057477e2d77af7b4e7db");
        return data.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<Movies, ObservableSource<Movie>>) movies -> {
                    allMovies.setValue(movies.getResults());
                    return Observable.fromIterable(movies.getResults()).subscribeOn(Schedulers.io());
                });
    }
    /** This Helper Method gets all genres
     * and then filter Them using the movie genre id
     * and then maps to each movie
     **/
    private Observable<Movie> getGenreObservable(Movie movie){
        return HttpCall.getRequest().getMovieGenres("e258f83c03db057477e2d77af7b4e7db").subscribeOn(Schedulers.io())
                .map(movieGenreList -> movieGenreList.getGenres())
                .flatMap((Function<List<Genre>, ObservableSource<List<Genre>>>) genres -> Observable.fromIterable(genres).filter(genre -> {
                    for(Integer id:movie.getGenreIds()){
                        if(id == genre.getId()) return true;
                    }
                    return false;
                }).toList().toObservable())
                .flatMap((Function<List<Genre>, ObservableSource<List<String>>>) genres -> Observable.fromIterable(genres)
                        .map(genre -> genre.getName()).toList().toObservable())
                .flatMap((Function<List<String>, ObservableSource<String>>) strings -> Observable.fromIterable(strings).reduce((s, s2) -> s+", "+s2).toObservable())
                .map(s -> {
                    movie.setGenreNames(s);
                    return movie;
                });
    }
    
    
    public LiveData<List<Movie>> getAllMovies(){return allMovies;}
    public LiveData<Movie> getMovie(){return movieOnly;}
    public LiveData<MovieDetail> getMovieDetails(){return movieDetail;}

    // Return a single ton instance of this class
    public static DataRepository getInstance(){
        if(repoInstance == null){
            return new DataRepository();
        }
        return repoInstance;
    }

}
