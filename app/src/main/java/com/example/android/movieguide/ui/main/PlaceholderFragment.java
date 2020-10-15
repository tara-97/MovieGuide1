package com.example.android.movieguide.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieguide.MovieRecyclerViewAdapter;
import com.example.android.movieguide.PagedData.MovieComparator;
import com.example.android.movieguide.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private static final String TAG = "PlaceholderFragment";

    private static final String ARG_PATH_URl = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(String path) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PATH_URl,path);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        pageViewModel= new ViewModelProvider(this).get(PageViewModel.class);
        String path="top_rated";
        if(getArguments() != null){
            path=getArguments().getString(ARG_PATH_URl);
        }
        pageViewModel.setPath(path);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView mRecyclerView=root.findViewById(R.id.rv_movie_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieRecyclerViewAdapter movieAdapter = new MovieRecyclerViewAdapter(new MovieComparator(), getContext());
        mRecyclerView.setAdapter(movieAdapter);
        pageViewModel.getMovieData().subscribe(moviePagingData -> movieAdapter.submitData(getLifecycle(), moviePagingData));
        return root;
    }
}