package com.example.android.movieguide.ui.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movieguide.MovieDetailModel;
import com.example.android.movieguide.POJO.Genre;
import com.example.android.movieguide.POJO.MovieDetail;
import com.example.android.movieguide.POJO.ProductionCompany;
import com.example.android.movieguide.POJO.ProductionCountry;
import com.example.android.movieguide.R;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieInfoFragment extends Fragment {
    private static final String TAG = "MovieInfoFragment";
    private MovieDetailModel movieDetailModel;
    public MovieInfoFragment() {
        // Required empty public constructor
    }

    public static MovieInfoFragment newInstance() {
        MovieInfoFragment fragment = new MovieInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailModel=new ViewModelProvider(requireActivity()).get(MovieDetailModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_info, container, false);
        ImageView poster=view.findViewById(R.id.iv_movie_poster);
        TextView movie_name=view.findViewById(R.id.tv_movie_title);
        TextView movie_summary=view.findViewById(R.id.tv_movie_summary);
        TextView movie_desc=view.findViewById(R.id.movie_description);
        movieDetailModel.getMovieDetails().observe(getViewLifecycleOwner(), new Observer<MovieDetail>() {
            @Override
            public void onChanged(MovieDetail movieDetail) {
                Glide.with(getContext()).load("https://image.tmdb.org/t/p/w780"+movieDetail.getBackdropPath()).into(poster);
                Log.d(TAG, "onChanged: Movie name is "+movieDetail.getTitle());
                movie_name.setText(movieDetail.getTitle()+"\n"+movieDetail.getRuntime()+"\n");
                for(Genre genre:movieDetail.getGenres()){
                    movie_name.append(genre.getName()+",");
                }
                Log.d(TAG, "onChanged: Movie Detail is "+movieDetail.getOverview());
                movie_summary.setText(movieDetail.getOverview());

                movie_desc.setText(movieFacts(movieDetail));

            }
            public String movieFacts(MovieDetail movieDetail){
                StringBuilder detail=new StringBuilder();
                detail.append("Facts\n");
                detail.append("Original Title\n");
                detail.append(movieDetail.getOriginalTitle()+"\n");
                detail.append("Original Language\n");
                detail.append(movieDetail.getOriginalLanguage()+"\n");
                detail.append("Budget\n");
                detail.append(movieDetail.getBudget()+"\n");
                detail.append("Revenue\n");
                detail.append(movieDetail.getRevenue()+"\n");
                detail.append("Homepage\n");
                detail.append(movieDetail.getHomepage()+"\n");
                detail.append("Product Company\n");
                for(ProductionCompany company:movieDetail.getProductionCompanies()){
                    detail.append(company.getName()+",");
                }
                detail.append("\nReleases\n");
                detail.append(movieDetail.getReleaseDate());

                return detail.toString();
            }
        });
        return view;
    }

}