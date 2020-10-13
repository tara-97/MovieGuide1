package com.example.android.movieguide;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.movieguide.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements MovieRecyclerViewAdapter.OnDataClick {
    private static final String TAG = "MainActivity";
    public static final String MOVIE_ID="movieId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate:Called ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onDataClicked(int movieId) {
        Log.d(TAG, "onDataClicked: Id is "+movieId);
        Intent intent=new Intent(this,MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID,movieId);
        startActivity(intent);

    }
}