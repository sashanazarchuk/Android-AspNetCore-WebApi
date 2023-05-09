package com.example.clientandroid.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.clientandroid.R;
import com.example.clientandroid.application.HomeApplication;
import com.example.clientandroid.constans.Urls;
import com.example.clientandroid.services.dto.CategoryItemDTO;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<CategoryItemDTO> categories; // Список категорій
    private final OnCategoryClickListener onClickDeleteCategory; // Слухач для обробки події видалення категорії

    public CategoryAdapter(List<CategoryItemDTO> categories, OnCategoryClickListener onClickDeleteCategory) {
        this.categories = categories; // Ініціалізуємо список категорій
        this.onClickDeleteCategory = onClickDeleteCategory; // Ініціалізуємо слухач для обробки події видалення категорії
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Створюємо новий об'єкт CategoryViewHolder для кожного елемента в RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // Перевіряємо, чи список категорій не є пустим і чи позиція є дійсною
        if (categories != null && position < categories.size()) {
            // Отримуємо об'єкт CategoryItemDTO за поточною позицією
            CategoryItemDTO item = categories.get(position);

            // Встановлюємо назву категорії
            holder.getCategoryName().setText(item.getName());

            // Отримуємо URL зображення
            String url = Urls.BASIC_URL + item.getImage();

            // Завантажуємо зображення за допомогою Glide у віджет категорії
            Glide.with(holder.getCategoryImage().getContext())
                    .load(url)
                    .apply(new RequestOptions().override(600))
                    .into(holder.getCategoryImage());

            // Встановлюємо слухач натискання на кнопку видалення
            holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Викликаємо метод OnButtonClick слухача при натисканні на кнопку видалення
                    onClickDeleteCategory.OnButtonClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        // Повертаємо кількість елементів у списку категорій
        return categories.size();
    }
}