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
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.view.DetailActivity;

public class FavouriteTvShowAdapter extends PagedListAdapter<TvShow, FavouriteTvShowAdapter.TvShowViewHolder> {

    public FavouriteTvShowAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new FavouriteTvShowAdapter.TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        final TvShow tvShow = getItem(position);
        if (tvShow != null) {
            holder.textViewTitle.setText(tvShow.getName());
            holder.textViewRelease.setText(tvShow.getFirstAirDate());
            Glide.with(holder.itemView.getContext()).load(ApiConstants.API_POSTER + tvShow.getPosterPath()).into(holder.imageViewMovie);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("isMovie", false);
                    intent.putExtra("tvShowId", tvShow.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private static DiffUtil.ItemCallback<TvShow> DIFF_CALLBACK = new DiffUtil.ItemCallback<TvShow>() {
        @Override
        public boolean areItemsTheSame(@NonNull TvShow oldItem, @NonNull TvShow newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TvShow oldItem, @NonNull TvShow newItem) {
            return oldItem.equals(newItem);
        }
    };

    class TvShowViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewRelease;
        ImageView imageViewMovie;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.tvTitle);
            textViewRelease = itemView.findViewById(R.id.tvRelease);
            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
        }
    }
}
