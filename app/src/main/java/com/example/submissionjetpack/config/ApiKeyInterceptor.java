package com.example.submissionjetpack.config;

import androidx.annotation.NonNull;

import com.example.submissionjetpack.BuildConfig;
import com.example.submissionjetpack.constant.ApiConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl httpUrl = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();

        Request request = chain.request().newBuilder().url(httpUrl).build();

        return chain.proceed(request);
    }
}
