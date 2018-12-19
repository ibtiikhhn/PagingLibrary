package com.codeiatic.paginglibrary;

import com.codeiatic.paginglibrary.Movies.Result;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Result>> mutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource create() {
        MovieDataSource dataSource = new MovieDataSource();
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Result>> getMutableLiveData() {
        return mutableLiveData;
    }
}
