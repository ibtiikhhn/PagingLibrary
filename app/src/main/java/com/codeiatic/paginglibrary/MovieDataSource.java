package com.codeiatic.paginglibrary;

import android.util.Log;

import com.codeiatic.paginglibrary.Movies.Example;
import com.codeiatic.paginglibrary.Movies.Result;
import com.codeiatic.paginglibrary.NetworkUtils.NetworkClient;
import com.codeiatic.paginglibrary.NetworkUtils.TopRatedMovies;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieDataSource extends PageKeyedDataSource<Integer, Result> {

    public static final int firstPage = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {
        Retrofit retrofit = NetworkClient.getRetrofit();
        TopRatedMovies topRatedMovies = retrofit.create(TopRatedMovies.class);
        topRatedMovies.getMovies("getyourselfakey", String.valueOf(firstPage))
        .enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.body() != null) {
                    callback.onResult(response.body().getResults(), null, firstPage + 1);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        Retrofit retrofit = NetworkClient.getRetrofit();
        TopRatedMovies topRatedMovies = retrofit.create(TopRatedMovies.class);
        topRatedMovies.getMovies("getyourselfakey", String.valueOf(params.key))
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        Retrofit retrofit = NetworkClient.getRetrofit();
        TopRatedMovies topRatedMovies = retrofit.create(TopRatedMovies.class);
        topRatedMovies.getMovies("getyourselfakey", String.valueOf(params.key))
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.body() != null) {
                            Integer key = (params.key < response.body().getTotalPages()) ? params.key + 1 : null;
                            Log.i("KEY", "onResponse: "+key);
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {

                    }
                });
    }
}
