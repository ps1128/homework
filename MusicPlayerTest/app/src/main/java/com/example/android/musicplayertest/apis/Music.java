package com.example.android.musicplayertest.apis;


import com.example.android.musicplayertest.responsebody.MusicResponsebody;
import com.example.android.musicplayertest.responsebody.Resource;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Music {

    @GET("music/_table/music")
    Call<Resource<MusicResponsebody>> getall();

}
