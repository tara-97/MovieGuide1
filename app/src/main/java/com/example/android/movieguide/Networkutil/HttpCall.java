package com.example.android.movieguide.Networkutil;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpCall {
    public static final String BASE_URL="https://api.themoviedb.org/3/";
    private static Retrofit mRetrofit= new Retrofit.Builder().
            baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();

    private static ApiClient request=mRetrofit.create(ApiClient.class);

    public static ApiClient getRequest(){return  request;
    }
}
