package com.example.submissionjetpack.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.submissionjetpack.model.Movie;

import java.util.List;

public class DiffCallback extends DiffUtil.Callback {

    private final List<Movie> mOldNoteList;
    private final List<Movie> mNewNoteList;

    public DiffCallback(List<Movie> oldNoteList, List<Movie> newNoteList) {
        this.mOldNoteList = oldNoteList;
        this.mNewNoteList = newNoteList;
    }

    @Override
    public int getOldListSize() {
        return mOldNoteList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewNoteList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldNoteList.get(oldItemPosition).getId() == mNewNoteList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Movie oldEmployee = mOldNoteList.get(oldItemPosition);
        final Movie newEmployee = mNewNoteList.get(newItemPosition);

        return oldEmployee.getTitle().equals(newEmployee.getTitle());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
