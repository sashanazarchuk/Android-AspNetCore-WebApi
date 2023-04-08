package com.example.clientandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api/Product/GetAll")
    public Call<List<Product>> getAllProducts();

}
