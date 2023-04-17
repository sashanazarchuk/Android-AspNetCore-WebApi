package com.example.clientandroid;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.clientandroid.constans.Urls;
import com.example.clientandroid.models.Category;
import com.example.clientandroid.services.CategoryAdapter;
import com.example.clientandroid.services.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private List<String> mData;
    private List<String> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();
        mImageUrls = new ArrayList<>();

        mAdapter = new CategoryAdapter(mData, mImageUrls);
        mRecyclerView.setAdapter(mAdapter);

        NetworkService.getInstance()
                .getApi()
                .getList()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {

                        List<Category> categories = response.body();

                        for (Category item : categories){
                            String url = Urls.BASIC_URL+item.getImage();
                            mAdapter.addData(item.getName(), url);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }


}

