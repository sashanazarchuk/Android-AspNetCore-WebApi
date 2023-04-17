package com.example.clientandroid;

import com.example.clientandroid.models.Category;
import com.example.clientandroid.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api/Product/GetAll")
    public Call<List<Product>> getAllProducts();

    @GET("/api/Category/GetAll")
    public Call<List<Category>> getList();
}
