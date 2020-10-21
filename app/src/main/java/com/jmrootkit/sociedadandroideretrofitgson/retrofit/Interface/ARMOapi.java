package com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface;


import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Puestos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ARMOapi {
    @GET("ARMOWS/Armo_obtener_puesto_laboral_historial/index.php")
    Call<List<Puestos>> getPuestos();

}
