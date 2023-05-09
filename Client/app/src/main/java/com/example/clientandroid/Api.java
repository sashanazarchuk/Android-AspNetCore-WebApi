package com.example.clientandroid;

import com.example.clientandroid.services.dto.CategoryCreateDTO;
import com.example.clientandroid.services.dto.CategoryItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @GET("/api/Category/GetAll")
    public Call<List<CategoryItemDTO>> getList();
    @POST("/api/categories/create")
    public Call<Void> create(@Body CategoryCreateDTO model);
    @DELETE("/api/categories/{id}")
    public Call<Void> delete(@Path("id")int id);


}
