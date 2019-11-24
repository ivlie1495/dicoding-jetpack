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
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.view.DetailActivity;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private List<TvShow> tvShowList;
    private Context context;

    public TvShowAdapter(List<TvShow> tvShowList, Context context) {
        this.tvShowList = tvShowList;
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        return new TvShowAdapter.TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder holder, int position) {
        final TvShow tvShow = tvShowList.get(position);
        holder.textViewTitle.setText(tvShow.getName());
        holder.textViewRelease.setText(tvShow.getFirstAirDate());
        Glide.with(context).load(ApiConstants.API_POSTER + tvShow.getPosterPath()).into(holder.imageViewMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("isMovie", false);
                intent.putExtra("tvShowId", tvShow.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

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
