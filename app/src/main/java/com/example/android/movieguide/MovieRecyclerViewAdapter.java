package com.example.android.movieguide;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.movieguide.Networkutil.Movie;
import com.example.android.movieguide.POJO.Genre;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {
    private static final String TAG = "MovieRecyclerViewAdapte";

    private Context context;

    private List<Movie> topRatedMovies;
    OnDataClick mcallback;
    interface OnDataClick{
        void onDataClicked(int movieId);
    }

    public MovieRecyclerViewAdapter(Context context) {
        Log.d(TAG, "MovieRecyclerViewAdapter: Constructor called");
        this.context=context;
        mcallback=(OnDataClick) context;
    }

     class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView moviePoster;
        private TextView movieTitle;
        private TextView movieYrOfRls;
        private TextView movieRating;
        private TextView movieGenre;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster=itemView.findViewById(R.id.img_mv_poster);
            movieTitle=itemView.findViewById(R.id.tv_mv_name);
            movieRating=itemView.findViewById(R.id.tv_mv_rating);
            movieYrOfRls=itemView.findViewById(R.id.tv_mv_yr);
            movieGenre=itemView.findViewById(R.id.tv_mv_genre);
            itemView.setOnClickListener(this);

        }
        public void bind(Movie movie,int pos){
            movieTitle.setText(movie.getTitle());
            movieYrOfRls.setText(movie.getReleaseDate());
            movieRating.setText(Double.toString(movie.getVoteAverage()));

            if(movie.getGenreNames() == null || movie.getGenreNames().length()==0) movieGenre.setText("");
            else{
                movieGenre.setText(movie.getGenreNames());
            }
            Glide.with(context).load(movie.getPosterPath()).placeholder(R.drawable.ic_launcher_background).fitCenter().into(moviePoster);
        }

         @Override
         public void onClick(View v) {
             mcallback.onDataClicked(topRatedMovies.get(getAdapterPosition()).getId());
         }

     }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_template_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(topRatedMovies.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(topRatedMovies==null || topRatedMovies.size() == 0) return 0;

        return topRatedMovies.size();
    }
    public void setData(List<Movie> movieList){
        if(movieList == null){
            Log.d(TAG, "setData: Called");
            return;
        }
        topRatedMovies=movieList;
        notifyDataSetChanged();
    }
    public void update(Movie movie){
        notifyItemChanged(topRatedMovies.indexOf(movie));
    }
}
