package com.example.clientandroid.services;

import com.example.clientandroid.Api;
import com.example.clientandroid.constans.Urls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService instance; // Статичний екземпляр класу NetworkService
    private Retrofit retrofit; // Retrofit для виконання мережевих запитів

    public NetworkService() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS) // Таймаут з'єднання
                .writeTimeout(20, TimeUnit.SECONDS) // Таймаут на запис даних
                .readTimeout(20, TimeUnit.SECONDS) // Таймаут на отримання даних
                .build();

        retrofit = new Retrofit.Builder()
                .client(httpClient) // Встановлюємо налаштування клієнта OkHttpClient
                .baseUrl(Urls.BASIC_URL) // Встановлюємо базову URL-адресу
                .addConverterFactory(GsonConverterFactory.create()) // Використовуємо Gson для конвертації JSON
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService(); // Ініціалізуємо екземпляр NetworkService, якщо він ще не існує
        }
        return instance;
    }

    public Api getCategoriesApi() {
        return retrofit.create(Api.class); // Створюємо інтерфейс Api для виконання запитів
    }
}