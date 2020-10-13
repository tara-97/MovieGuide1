package com.example.android.movieguide.ui.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movieguide.MovieDetailModel;
import com.example.android.movieguide.POJO.Cast;
import com.example.android.movieguide.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieCastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieCastFragment extends Fragment {
    private static final String TAG = "MovieCastFragment";
    private MovieDetailModel mDetailModel;
    public MovieCastFragment() {
        // Required empty public constructor
    }
    public static MovieCastFragment newInstance() {
        MovieCastFragment fragment = new MovieCastFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Called");
        super.onCreate(savedInstanceState);
        mDetailModel=new ViewModelProvider(requireActivity()).get(MovieDetailModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_cast, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_cast_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CastListAdapter adapter = new CastListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        mDetailModel.getMovieCasts().observe(getViewLifecycleOwner(), casts -> {
            adapter.setCastDetails(casts);
        });
        return view;
    }
}