package com.example.submissionjetpack.di;

import android.content.Context;

import com.example.submissionjetpack.data.local.LocalRepository;
import com.example.submissionjetpack.data.remote.RemoteRepository;
import com.example.submissionjetpack.data.source.DataRepository;

public class Injection {

    public static DataRepository dataRepository(Context context) {
        return DataRepository.getInstance(RemoteRepository.getInstance(), LocalRepository.getInstance(context));
    }
}
