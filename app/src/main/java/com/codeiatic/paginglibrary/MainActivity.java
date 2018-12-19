package com.codeiatic.paginglibrary;

import android.os.Bundle;

import com.codeiatic.paginglibrary.Movies.Result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.myRecycler);
        movieAdapter = new MovieAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.moviePagedList.observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                movieAdapter.submitList(results);
            }
        });

        recyclerView.setAdapter(movieAdapter);
    }
}
