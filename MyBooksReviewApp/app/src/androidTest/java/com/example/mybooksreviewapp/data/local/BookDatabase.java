
package com.example.mybooksreviewapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {BookEntity.class}, version = 1, exportSchema = true)
public abstract class BookDatabase extends RoomDatabase {
    private static final String DB_NAME = "book_database";
    private static BookDatabase instance;

    public abstract BookDao bookDao();

    public static synchronized BookDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            BookDatabase.class,
                            DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
