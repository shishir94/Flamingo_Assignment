
package com.example.mybooksreviewapp.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "books")
public class BookEntity {
    @PrimaryKey
    private int id;
    private String title;
    private String author;
    private String thumbnailUrl;
    private String description;
    private double rating;
    private boolean isFavorite;

    public BookEntity(int id, String title, String author, String thumbnailUrl,
                      String description, double rating, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.rating = rating;
        this.isFavorite = isFavorite;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
