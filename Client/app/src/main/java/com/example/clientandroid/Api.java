package com.example.clientandroid;

import com.example.clientandroid.models.Category;
import com.example.clientandroid.models.Product;
import com.example.clientandroid.services.dto.CategoryCreateDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @GET("/api/Product/GetAll")
    public Call<List<Product>> getAllProducts();

    @GET("/api/Category/GetAll")
    public Call<List<Category>> getList();
    @POST("/api/categories/create")
    public Call<Void> create(@Body CategoryCreateDTO model);
}
