package com.example.home.network;

import com.example.home.model.Page;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroService {

    @GET("/api/v1/search_by_date/")
    Single<Page> getRemotePage(@Query("tags") String tags, @Query("page") int page);

}
