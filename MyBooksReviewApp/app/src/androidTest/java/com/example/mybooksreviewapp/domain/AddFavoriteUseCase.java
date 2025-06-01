
package com.example.mybooksreviewapp.domain;

import com.example.mybooksreviewapp.data.local.BookEntity;
import com.example.mybooksreviewapp.data.repository.BookRepository;


public class AddFavoriteUseCase {
    private BookRepository repository;

    public AddFavoriteUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public void execute(BookEntity bookEntity) {
        repository.insertFavorite(bookEntity);
    }
}
