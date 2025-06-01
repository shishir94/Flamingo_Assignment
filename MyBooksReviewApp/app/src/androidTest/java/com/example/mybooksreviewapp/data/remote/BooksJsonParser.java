
package com.example.mybooksreviewapp.data.remote;

import android.content.Context;

import com.example.mybooksreviewapp.data.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;


public class BooksJsonParser {
    private Context context;

    public BooksJsonParser(Context context) {
        this.context = context.getApplicationContext();
    }


    public List<Book> parseBooksFromAssets() {
        try {
            InputStream is = context.getAssets().open("books.json");
            InputStreamReader reader = new InputStreamReader(is);
            Type listType = new TypeToken<List<Book>>() {}.getType();
            List<Book> books = new Gson().fromJson(reader, listType);
            reader.close();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
