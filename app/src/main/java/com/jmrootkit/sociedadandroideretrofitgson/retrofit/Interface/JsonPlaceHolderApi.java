package com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface;

import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>> getPosts();

}
