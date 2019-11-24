package com.example.submissionjetpack.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submissionjetpack.R;
import com.example.submissionjetpack.constant.ApiConstants;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.view.DetailActivity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewRelease.setText(movie.getReleaseDate());
        Glide.with(context).load(ApiConstants.API_POSTER + movie.getPosterPath()).into(holder.imageViewMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("isMovie", true);
                intent.putExtra("movieId", movie.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

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
