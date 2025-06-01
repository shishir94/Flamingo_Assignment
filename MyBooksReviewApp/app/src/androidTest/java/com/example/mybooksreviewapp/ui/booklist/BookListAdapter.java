
package com.example.mybooksreviewapp.ui.booklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mybooksreviewapp.R;
import com.example.mybooksreviewapp.data.model.Book;

import java.util.List;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    private Context context;
    private List<Book> bookList;
    private OnItemClickListener listener;

    public BookListAdapter(Context context, List<Book> bookList, OnItemClickListener listener) {
        this.context = context;
        this.bookList = bookList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.textViewTitle.setText(book.getTitle());
        holder.textViewAuthor.setText(book.getAuthor());



        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(book);
        });
    }

    @Override
    public int getItemCount() {
        return (bookList != null) ? bookList.size() : 0;
    }

    public void updateBooks(List<Book> books) {
        this.bookList = books;
        notifyDataSetChanged();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        TextView textViewTitle, textViewAuthor;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
        }
    }
}
