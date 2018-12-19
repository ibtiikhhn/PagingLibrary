package com.codeiatic.paginglibrary;

import android.app.Application;

import com.codeiatic.paginglibrary.Movies.Result;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class MovieViewModel extends AndroidViewModel {

    LiveData<PagedList<Result>> moviePagedList;
    LiveData<PageKeyedDataSource<Integer, Result>> liveDataSource;


    public MovieViewModel(@NonNull Application application) {
        super(application);

        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory();
        liveDataSource = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build();

        moviePagedList = (new LivePagedListBuilder(movieDataSourceFactory, config)).build();

    }
}
