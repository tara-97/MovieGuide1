package com.example.android.movieguide;

import android.os.Bundle;

import com.example.android.movieguide.ui.detail.DetailSectionsPageAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.movieguide.ui.main.SectionsPagerAdapter;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieDetailModel movieModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movieModel=new ViewModelProvider(this).get(MovieDetailModel.class);
        movieModel.requestData(getIntent().getIntExtra(MainActivity.MOVIE_ID,0));
        DetailSectionsPageAdapter pagerAdapter = new DetailSectionsPageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.detail_view_pager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}