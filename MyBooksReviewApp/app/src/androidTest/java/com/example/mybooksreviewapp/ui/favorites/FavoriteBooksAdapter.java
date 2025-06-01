
package com.example.mybooksreviewapp.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mybooksreviewapp.R;
import com.example.mybooksreviewapp.data.local.BookEntity;

import java.util.List;


public class FavoriteBooksAdapter extends RecyclerView.Adapter<FavoriteBooksAdapter.FavViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(BookEntity bookEntity);
    }

    private Context context;
    private List<BookEntity> favoriteList;
    private OnItemClickListener listener;

    public FavoriteBooksAdapter(Context context, List<BookEntity> favoriteList, OnItemClickListener listener) {
        this.context = context;
        this.favoriteList = favoriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_book, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        BookEntity book = favoriteList.get(position);
        holder.textViewTitle.setText(book.getTitle());
        holder.textViewAuthor.setText(book.getAuthor());


        holder.imageViewThumbnail.setImageResource(R.drawable.ic_book_placeholder);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(book));
    }

    @Override
    public int getItemCount() {
        return (favoriteList != null) ? favoriteList.size() : 0;
    }

    public void updateFavorites(List<BookEntity> favorites) {
        this.favoriteList = favorites;
        notifyDataSetChanged();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        TextView textViewTitle, textViewAuthor;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewFavThumbnail);
            textViewTitle = itemView.findViewById(R.id.textViewFavTitle);
            textViewAuthor = itemView.findViewById(R.id.textViewFavAuthor);
        }
    }
}
