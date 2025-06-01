package com.example.mybooksreviewapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(BookEntity bookEntity);

    @Delete
    void deleteFavorite(BookEntity bookEntity);


    @Query("SELECT * FROM books")
    LiveData<List<BookEntity>> getAllFavoriteBooks();


    @Query("SELECT COUNT(*) FROM books WHERE id = :bookId")
    int isBookFavorited(int bookId);
}
