
package com.example.mybooksreviewapp.domain;

import androidx.lifecycle.LiveData;

import com.example.mybooksreviewapp.data.local.BookEntity;
import com.example.mybooksreviewapp.data.repository.BookRepository;

import java.util.List;


public class GetFavoriteBooksUseCase {
    private BookRepository repository;

    public GetFavoriteBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }


    public LiveData<List<BookEntity>> execute() {
        return repository.getFavoriteBooks();
    }
}
