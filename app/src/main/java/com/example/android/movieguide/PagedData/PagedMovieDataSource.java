package com.example.android.movieguide.PagedData;

import androidx.annotation.NonNull;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.android.movieguide.Constants;
import com.example.android.movieguide.Networkutil.ApiClient;
import com.example.android.movieguide.Networkutil.HttpCall;
import com.example.android.movieguide.Networkutil.Movie;
import com.example.android.movieguide.POJO.Genre;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PagedMovieDataSource extends RxPagingSource<Integer, Movie> {

    private ApiClient mbackend;
    private Integer pageNo = null;
    private String tag;

    public PagedMovieDataSource(String path) {
        mbackend = HttpCall.getRequest();
        tag = path;
    }

    @NotNull

    public Single<LoadResult<Integer, Movie>> loadSingle(
            @NotNull LoadParams<Integer> params) {
        // Start refresh at page 1 if undefined.
        Integer nextPageNumber = params.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
            pageNo = 1;
        } else {
            pageNo = nextPageNumber;
        }

        return mbackend.getMovieByTags(tag, Constants.getApiKey(), nextPageNumber)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable(movies -> movies.getResults())
                .flatMap((Function<Movie, ObservableSource<Movie>>) movie -> {
                    movie.setPosterPath("https://image.tmdb.org/t/p/w500" + movie.getPosterPath());
                    return getGenreObservable(movie);

                }).toList()
                .map(this::toLoadResult);


    }

    private LoadResult<Integer, Movie> toLoadResult(
            @NonNull List<Movie> movies) {

        return new LoadResult.Page(

                movies,
                null, // Only paging forward.
                pageNo + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    private Observable<Movie> getGenreObservable(Movie movie) {
        return HttpCall.getRequest().getMovieGenres("e258f83c03db057477e2d77af7b4e7db").subscribeOn(Schedulers.io())
                .map(movieGenreList -> movieGenreList.getGenres())
                .flatMap((Function<List<Genre>, ObservableSource<List<Genre>>>) genres -> Observable.fromIterable(genres).filter(genre -> {
                    for (Integer id : movie.getGenreIds()) {
                        if (id == genre.getId()) return true;
                    }
                    return false;
                }).toList().toObservable())
                .flatMap((Function<List<Genre>, ObservableSource<List<String>>>) genres -> Observable.fromIterable(genres)
                        .map(genre -> genre.getName()).toList().toObservable())
                .flatMap((Function<List<String>, ObservableSource<String>>) strings -> Observable.fromIterable(strings).reduce((s, s2) -> s + ", " + s2).toObservable())
                .map(s -> {
                    movie.setGenreNames(s);
                    return movie;
                });
    }


}
