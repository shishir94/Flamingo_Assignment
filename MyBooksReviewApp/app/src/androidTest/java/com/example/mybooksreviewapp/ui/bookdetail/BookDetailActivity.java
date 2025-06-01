package com.example.mybooksreviewapp.ui.bookdetail;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mybooksreviewapp.R;

public class BookDetailActivity extends AppCompatActivity {
    private BookDetailViewModel viewModel;
    private ImageView imageViewThumbnail;
    private TextView textViewTitle, textViewAuthor, textViewRating, textViewDescription;
    private Button buttonFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);


        imageViewThumbnail = findViewById(R.id.imageViewDetailThumbnail);
        textViewTitle = findViewById(R.id.textViewDetailTitle);
        textViewAuthor = findViewById(R.id.textViewDetailAuthor);
        textViewRating = findViewById(R.id.textViewDetailRating);
        textViewDescription = findViewById(R.id.textViewDetailDescription);
        buttonFavorite = findViewById(R.id.buttonFavorite);


        int bookId = getIntent().getIntExtra("book_id", -1);
        String title = getIntent().getStringExtra("book_title");
        String author = getIntent().getStringExtra("book_author");
        String description = getIntent().getStringExtra("book_description");
        double rating = getIntent().getDoubleExtra("book_rating", 0.0);
        String thumbnailUrl = getIntent().getStringExtra("book_thumbnail");


        viewModel = new ViewModelProvider(this).get(BookDetailViewModel.class);
        viewModel.initBook(bookId, title, author, description, rating, thumbnailUrl);


        viewModel.getTitle().observe(this, s -> textViewTitle.setText(s));
        viewModel.getAuthor().observe(this, s -> textViewAuthor.setText(s));
        viewModel.getRating().observe(this, r -> textViewRating.setText("Rating: " + r));
        viewModel.getDescription().observe(this, s -> textViewDescription.setText(s));

        viewModel.getIsFavorited().observe(this, isFav -> {
            if (isFav != null && isFav) {
                buttonFavorite.setText("Unfavorite");
            } else {
                buttonFavorite.setText("Favorite");
            }
        });


        buttonFavorite.setOnClickListener(v -> {
            viewModel.toggleFavorite();
            Toast.makeText(this, "Favorite status changed", Toast.LENGTH_SHORT).show();
        });


        imageViewThumbnail.setImageResource(R.drawable.ic_book_placeholder);
    }
}
