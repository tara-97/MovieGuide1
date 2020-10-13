package com.example.android.movieguide.ui.detail;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.movieguide.R;

public class DetailSectionsPageAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public DetailSectionsPageAdapter(Context context,@NonNull FragmentManager fm) {
        super(fm);
        mContext=context;
    }
    private static final int[] TAB_TITLES = new int[]{R.string.movie_detail_tab_1,R.string.movie_detail_tab_2};

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new MovieInfoFragment();
                break;
            case 1:
                fragment=new MovieCastFragment();
                break;

        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
