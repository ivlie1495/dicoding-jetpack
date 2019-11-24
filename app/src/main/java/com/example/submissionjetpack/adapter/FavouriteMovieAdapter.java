package com.example.submissionjetpack.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submissionjetpack.R;
import com.example.submissionjetpack.constant.ApiConstants;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.view.DetailActivity;


public class FavouriteMovieAdapter extends PagedListAdapter<Movie, FavouriteMovieAdapter.MovieHolder> {

    public FavouriteMovieAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new FavouriteMovieAdapter.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        final Movie movie = getItem(position);
        if (movie != null) {
            holder.textViewTitle.setText(movie.getTitle());
            holder.textViewRelease.setText(movie.getReleaseDate());
            Glide.with(holder.itemView.getContext()).load(ApiConstants.API_POSTER + movie.getPosterPath()).into(holder.imageViewMovie);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("isMovie", true);
                    intent.putExtra("movieId", movie.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    class MovieHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewRelease;
        ImageView imageViewMovie;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.tvTitle);
            textViewRelease = itemView.findViewById(R.id.tvRelease);
            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
        }
    }
}
