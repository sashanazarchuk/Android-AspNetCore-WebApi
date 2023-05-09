package com.example.clientandroid;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.clientandroid.application.HomeApplication;
import com.example.clientandroid.constans.Urls;
import com.example.clientandroid.services.CategoryAdapter;
import com.example.clientandroid.services.NetworkService;
import com.example.clientandroid.services.OnCategoryClickListener;
import com.example.clientandroid.services.dto.CategoryItemDTO;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private CategoryAdapter adapter;
    private RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Завантаження зображення і встановлення його в ImageView
        ImageView avatar = findViewById(R.id.myImage);
        String url = Urls.BASIC_URL + "/images/Mouse.jpg";
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(avatar);

        // Ініціалізація RecyclerView та адаптера
        rc = findViewById(R.id.rcvCategories);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        adapter = new CategoryAdapter(new ArrayList<>(), MainActivity.this::onClickDelete);
        rc.setAdapter(adapter);

        // Виклик методу для отримання даних з сервера
        requestServer();
    }

    // Метод для виконання запиту на сервер для отримання списку категорій
    private void requestServer() {
        NetworkService.getInstance().getCategoriesApi().getList().enqueue(new Callback<List<CategoryItemDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                if (response.isSuccessful()) {
                    List<CategoryItemDTO> list = response.body();
                    adapter = new CategoryAdapter(list, MainActivity.this::onClickDelete);// Оновлення даних адаптера
                }
            }

            @Override
            public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {
                // Обробка помилки запиту
            }
        });
    }

    // Обробник натискання кнопки видалення категорії
    private void onClickDelete(CategoryItemDTO category) {
        NetworkService.getInstance().getCategoriesApi().delete(category.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Категорію видалено: " + category.getName(), Toast.LENGTH_SHORT).show();
                    // Перезавантаження активності
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Обробка помилки запиту
            }
        });
    }
}