
package com.example.mybooksreviewapp.ui.bookdetail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mybooksreviewapp.data.local.BookEntity;
import com.example.mybooksreviewapp.data.repository.BookRepository;
import com.example.mybooksreviewapp.domain.AddFavoriteUseCase;
import com.example.mybooksreviewapp.domain.RemoveFavoriteUseCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BookDetailViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> bookId = new MutableLiveData<>();
    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> author = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<Double> rating = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFavorited = new MutableLiveData<>(false);

    private BookRepository repository;
    private AddFavoriteUseCase addFavoriteUseCase;
    private RemoveFavoriteUseCase removeFavoriteUseCase;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public BookDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        addFavoriteUseCase = new AddFavoriteUseCase(repository);
        removeFavoriteUseCase = new RemoveFavoriteUseCase(repository);
    }


    public void initBook(int id, String t, String a, String desc, double r, String thumbUrl) {
        bookId.setValue(id);
        title.setValue(t);
        author.setValue(a);
        description.setValue(desc);
        rating.setValue(r);


        executorService.execute(() -> {
            boolean fav = repository.isBookFavorited(id);
            isFavorited.postValue(fav);
        });
    }


    public LiveData<String> getTitle() { return title; }
    public LiveData<String> getAuthor() { return author; }
    public LiveData<String> getDescription() { return description; }
    public LiveData<Double> getRating() { return rating; }
    public LiveData<Boolean> getIsFavorited() { return isFavorited; }


    public void toggleFavorite() {
        if (bookId.getValue() == null) return;
        int id = bookId.getValue();


        BookEntity entity = new BookEntity(
                id,
                title.getValue(),
                author.getValue(),
                "",
                description.getValue(),
                rating.getValue(),
                true
        );


        boolean currentlyFav = isFavorited.getValue() != null && isFavorited.getValue();
        if (currentlyFav) {
            removeFavoriteUseCase.execute(entity);
            isFavorited.setValue(false);
        } else {
            addFavoriteUseCase.execute(entity);
            isFavorited.setValue(true);
        }
    }
}
