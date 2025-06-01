
package com.example.bookreviewapp.ui.favorites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookreviewapp.R;
import com.example.bookreviewapp.data.local.BookEntity;
import com.example.bookreviewapp.ui.bookdetail.BookDetailActivity;

import java.util.ArrayList;

public class FavoriteBooksActivity extends AppCompatActivity {
    private FavoriteBooksViewModel viewModel;
    private RecyclerView recyclerViewFavorites;
    private FavoriteBooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);

        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);

        adapter = new FavoriteBooksAdapter(this, new ArrayList<>(), bookEntity -> {
            // On click, open detail screen for this bookEntity
            Intent intent = new Intent(FavoriteBooksActivity.this, BookDetailActivity.class);
            intent.putExtra("book_id", bookEntity.getId());
            intent.putExtra("book_title", bookEntity.getTitle());
            intent.putExtra("book_author", bookEntity.getAuthor());
            intent.putExtra("book_description", bookEntity.getDescription());
            intent.putExtra("book_rating", bookEntity.getRating());
            intent.putExtra("book_thumbnail", bookEntity.getThumbnailUrl());
            startActivity(intent);
        });
        recyclerViewFavorites.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FavoriteBooksViewModel.class);
        viewModel.getFavoriteBooksLiveData().observe(this, bookEntities -> {
            if (bookEntities != null) {
                adapter.updateFavorites(bookEntities);
            }
        });
    }
}
