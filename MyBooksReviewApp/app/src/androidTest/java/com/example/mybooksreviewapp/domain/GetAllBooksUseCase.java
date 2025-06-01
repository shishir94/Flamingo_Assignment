
package com.example.mybooksreviewapp.domain;


import com.example.mybooksreviewapp.data.model.Book;
import com.example.mybooksreviewapp.data.repository.BookRepository;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class GetAllBooksUseCase {
    private BookRepository repository;

    public GetAllBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }


    public List<Book> execute() {
        FutureTask<List<Book>> task = new FutureTask<>(new Callable<List<Book>>() {
            @Override
            public List<Book> call() {
                return repository.getAllBooksFromJson();
            }
        });
        new Thread(task).start();
        try {
            return task.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
