package com.example.moviecatalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.CardViewHolder> {

    public ArrayList<Movies> listMovies = new ArrayList<>();
    public OnItemClickCallback onItemClickCallback;

    public void setData(ArrayList<Movies> items) {
        listMovies.clear();
        listMovies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesAdapter.CardViewHolder holder, int position) {
        String urlImg = "https://image.tmdb.org/t/p/w780/";
        Movies movies = listMovies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(urlImg+movies.getImages())
                .apply(new RequestOptions().override(500, 500))
                .into(holder.imgPhoto);
        holder.title.setText(movies.getTitle());
        holder.deskripsi.setText(movies.getDeskripsi());
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listMovies.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView title, deskripsi;
        Button detail;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto =  itemView.findViewById(R.id.img_item);
            title = itemView.findViewById(R.id.tv_judul);
            deskripsi = itemView.findViewById(R.id.tv_deskripsi);
            detail = itemView.findViewById(R.id.buttonDetail);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public interface OnItemClickCallback {
        void onItemClicked(Movies movies);
    }
}
