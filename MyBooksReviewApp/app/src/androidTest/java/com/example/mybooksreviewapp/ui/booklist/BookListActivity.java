
package com.example.mybooksreviewapp.ui.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybooksreviewapp.R;
import com.example.mybooksreviewapp.data.model.Book;
import com.example.mybooksreviewapp.ui.bookdetail.BookDetailActivity;
import com.example.mybooksreviewapp.ui.favorites.FavoriteBooksActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {
    private BookListViewModel viewModel;
    private RecyclerView recyclerViewBooks;
    private BookListAdapter adapter;
    private FloatingActionButton fabFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerViewBooks = findViewById(R.id.recyclerViewBooks);
        fabFavorites = findViewById(R.id.fabFavorites);


        adapter = new BookListAdapter(this, new ArrayList<>(), book -> {

            Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
            intent.putExtra("book_id", book.getId());
            intent.putExtra("book_title", book.getTitle());
            intent.putExtra("book_author", book.getAuthor());
            intent.putExtra("book_thumbnail", book.getThumbnailUrl());
            intent.putExtra("book_description", book.getDescription());
            intent.putExtra("book_rating", book.getRating());
            startActivity(intent);
        });
        recyclerViewBooks.setAdapter(adapter);


        viewModel = new ViewModelProvider(this).get(BookListViewModel.class);

        viewModel.getAllBooksLiveData().observe(this, books -> {
            if (books != null) {
                adapter.updateBooks(books);
            }
        });


        viewModel.getFavoriteBooksLiveData().observe(this, favoriteEntities -> {

        });


        viewModel.loadAllBooks();


        fabFavorites.setOnClickListener(v -> {
            Intent favIntent = new Intent(BookListActivity.this, FavoriteBooksActivity.class);
            startActivity(favIntent);
        });
    }
}
