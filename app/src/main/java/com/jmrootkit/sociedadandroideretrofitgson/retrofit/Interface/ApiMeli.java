package com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface;

import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Item;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMeli {

    @GET("sites/MLU/search?q=maquina_cortadora")
    Call<List<Item>> getItem();

}
