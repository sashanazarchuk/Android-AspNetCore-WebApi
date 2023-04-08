package com.example.clientandroid;

import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.clientandroid.services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);

        NetworkService.getInstance()
                .getApi()
                .getAllProducts()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        List<Product> products = response.body();

                        for (Product product : products) {
                            textView.append(String.valueOf(product.getId()) + "\n");
                            textView.append("Name: "+product.getName() + "\n");
                            textView.append("Description: "+product.getDescription() + "\n");
                            textView.append("Price: "+String.valueOf(product.getPrice()) + " UAH" + "\n\n");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {

                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }
}