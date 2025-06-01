
package com.example.mybooksreviewapp.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mybooksreviewapp.data.local.BookDao;
import com.example.mybooksreviewapp.data.local.BookDatabase;
import com.example.mybooksreviewapp.data.local.BookEntity;
import com.example.mybooksreviewapp.data.model.Book;
import com.example.mybooksreviewapp.data.remote.BooksJsonParser;

import java.util.ArrayList;
import java.util.List;


public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<BookEntity>> favoriteBooksLiveData;
    private BooksJsonParser jsonParser;

    public BookRepository(Application application) {
        BookDatabase db = BookDatabase.getInstance(application);
        bookDao = db.bookDao();
        favoriteBooksLiveData = bookDao.getAllFavoriteBooks();
        jsonParser = new BooksJsonParser(application);
    }


    public List<Book> getAllBooksFromJson() {
        List<Book> parsed = jsonParser.parseBooksFromAssets();
        return (parsed != null) ? parsed : new ArrayList<Book>();
    }


    public LiveData<List<BookEntity>> getFavoriteBooks() {
        return favoriteBooksLiveData;
    }


    public void insertFavorite(BookEntity bookEntity) {
        new InsertFavoriteAsyncTask(bookDao).execute(bookEntity);
    }


    public void deleteFavorite(BookEntity bookEntity) {
        new DeleteFavoriteAsyncTask(bookDao).execute(bookEntity);
    }


    public boolean isBookFavorited(int bookId) {
        try {
            return new CheckFavoriteAsyncTask(bookDao).execute(bookId).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    private static class InsertFavoriteAsyncTask extends AsyncTask<BookEntity, Void, Void> {
        private BookDao dao;
        InsertFavoriteAsyncTask(BookDao dao) { this.dao = dao; }
        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            dao.insertFavorite(bookEntities[0]);
            return null;
        }
    }

    private static class DeleteFavoriteAsyncTask extends AsyncTask<BookEntity, Void, Void> {
        private BookDao dao;
        DeleteFavoriteAsyncTask(BookDao dao) { this.dao = dao; }
        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            dao.deleteFavorite(bookEntities[0]);
            return null;
        }
    }

    private static class CheckFavoriteAsyncTask extends AsyncTask<Integer, Void, Boolean> {
        private BookDao dao;
        CheckFavoriteAsyncTask(BookDao dao) { this.dao = dao; }
        @Override
        protected Boolean doInBackground(Integer... ints) {
            int count = dao.isBookFavorited(ints[0]);
            return count > 0;
        }
    }
}
