package com.example.clientandroid.services;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clientandroid.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView textView; // Віджет для відображення назви категорії
    private ImageView imageView; // Віджет для відображення зображення категорії
    private Button btnDelete; // Кнопка видалення категорії

    public CategoryViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textview_item); // Ініціалізуємо віджет назви категорії
        imageView = itemView.findViewById(R.id.imageview_item); // Ініціалізуємо віджет зображення категорії
        btnDelete = itemView.findViewById(R.id.btnDelete); // Ініціалізуємо віджет кнопки видалення
    }

    public void bindData(String text, String imageUrl) {
        textView.setText(text); // Встановлюємо текст назви категорії
        Glide.with(itemView.getContext())
                .load(imageUrl)
                .into(imageView); // Завантажуємо зображення категорії за допомогою Glide у віджет
    }

    public Button getBtnDelete() {
        return btnDelete; // Повертаємо кнопку видалення категорії
    }

    public ImageView getCategoryImage() {
        return imageView; // Повертаємо віджет зображення категорії
    }

    public TextView getCategoryName() {
        return textView; // Повертаємо віджет назви категорії
    }
}