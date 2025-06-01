
package com.example.mybooksreviewapp.ui.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mybooksreviewapp.data.local.BookEntity;
import com.example.mybooksreviewapp.data.repository.BookRepository;
import com.example.mybooksreviewapp.domain.GetFavoriteBooksUseCase;

import java.util.List;


public class FavoriteBooksViewModel extends AndroidViewModel {
    private LiveData<List<BookEntity>> favoriteBooksLiveData;
    private BookRepository repository;
    private GetFavoriteBooksUseCase getFavoriteBooksUseCase;

    public FavoriteBooksViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        getFavoriteBooksUseCase = new GetFavoriteBooksUseCase(repository);
        favoriteBooksLiveData = getFavoriteBooksUseCase.execute();
    }

    public LiveData<List<BookEntity>> getFavoriteBooksLiveData() {
        return favoriteBooksLiveData;
    }
}
