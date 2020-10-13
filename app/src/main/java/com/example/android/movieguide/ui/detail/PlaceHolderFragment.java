package com.example.android.movieguide.ui.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movieguide.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceHolderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceHolderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlaceHolderFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(int position) {
        Fragment current=new Fragment();
        switch (position){
            case 0:
                current=MovieInfoFragment.newInstance();
                break;
            case 1:
                current=MovieCastFragment.newInstance();
                break;
            case 2:
                current=MovieReviewsFragment.newInstance();
                break;
        }
        return current;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_holder, container, false);
    }
}