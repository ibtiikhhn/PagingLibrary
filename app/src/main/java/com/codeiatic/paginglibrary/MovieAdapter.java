package com.codeiatic.paginglibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeiatic.paginglibrary.Movies.Result;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends PagedListAdapter<Result, MovieAdapter.MovieViewHolder> {

    Context context;

    protected MovieAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Result result = getItem(position);
        holder.movieName.setText(result.getTitle().toString());
        holder.movieDate.setText(result.getReleaseDate().toString());
    }

    private static DiffUtil.ItemCallback<Result> diffCallback = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        TextView movieDate;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieDate = itemView.findViewById(R.id.movieDate);
            movieName = itemView.findViewById(R.id.movieName);
        }
    }
}
