package com.example.android.movieguide.Networkutil;
import com.example.android.movieguide.POJO.Credits;
import com.example.android.movieguide.POJO.MovieDetail;
import com.example.android.movieguide.POJO.MovieGenreList;
import com.example.android.movieguide.POJO.ReviewList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @GET("movie/{tag}?")
    Single<Movies> getMovieByTags(@Path("tag") String movieTag, @Query("api_key") String key, @Query("page") Integer page);

    @GET("genre/movie/list?")
    Observable<MovieGenreList> getMovieGenres(@Query("api_key") String key);

    @GET("movie/{movieId}?")
    Observable<MovieDetail> getMovieDetail(@Path("movieId") Integer id, @Query("api_key") String key);

    @GET("movie/{movieId}/reviews")
    Observable<ReviewList> getMovieReviews(@Path("movieId") Integer id, @Query("api_key") String key);

    @GET("movie/{movieId}/credits")
    Observable<Credits> getMovieCredits(@Path("movieId") Integer id, @Query("api_key") String key);

    @GET("movie/{tag}?")
    Single<Movies> getMovieByTagsAndPage(@Path("tag") String movieTag, @Query("api_key") String key, @Query("page") Integer page);
}
