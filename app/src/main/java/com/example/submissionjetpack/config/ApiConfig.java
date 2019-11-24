package com.example.submissionjetpack.config;

import com.example.submissionjetpack.constant.ApiConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    private static OkHttpClient getInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        ApiKeyInterceptor apiKeyInterceptor = new ApiKeyInterceptor();

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(apiKeyInterceptor)
                .build();
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Api getService() {
        return getRetrofit().create(Api.class);
    }
}
