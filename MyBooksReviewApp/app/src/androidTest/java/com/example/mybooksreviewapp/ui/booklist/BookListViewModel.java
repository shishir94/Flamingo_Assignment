
package com.example.mybooksreviewapp.ui.booklist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mybooksreviewapp.data.local.BookEntity;
import com.example.mybooksreviewapp.data.model.Book;
import com.example.mybooksreviewapp.data.repository.BookRepository;
import com.example.mybooksreviewapp.domain.GetAllBooksUseCase;
import com.example.mybooksreviewapp.domain.GetFavoriteBooksUseCase;

import java.util.ArrayList;
import java.util.List;


public class BookListViewModel extends AndroidViewModel {
    private MutableLiveData<List<Book>> allBooksLiveData = new MutableLiveData<>();
    private LiveData<List<BookEntity>> favoriteBooksLiveData;

    private BookRepository repository;
    private GetAllBooksUseCase getAllBooksUseCase;
    private GetFavoriteBooksUseCase getFavoriteBooksUseCase;

    public BookListViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        getAllBooksUseCase = new GetAllBooksUseCase(repository);
        getFavoriteBooksUseCase = new GetFavoriteBooksUseCase(repository);
        favoriteBooksLiveData = getFavoriteBooksUseCase.execute();
    }


    public void loadAllBooks() {
        new Thread(() -> {
            List<Book> books = getAllBooksUseCase.execute();
            if (books == null) books = new ArrayList<>();
            allBooksLiveData.postValue(books);
        }).start();
    }

    public LiveData<List<Book>> getAllBooksLiveData() {
        return allBooksLiveData;
    }

    public LiveData<List<BookEntity>> getFavoriteBooksLiveData() {
        return favoriteBooksLiveData;
    }
}
