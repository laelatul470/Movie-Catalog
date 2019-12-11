package com.example.moviecatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movies> listMovies = new ArrayList<>();
    private RecyclerView rvMovies;
    private mainViewModel mainViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.loadingMovie);
        rvMovies = findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        RequestQueue queue = Volley.newRequestQueue(this);

        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        showLoading(true);

        final MoviesAdapter moviesAdapter = new MoviesAdapter();
        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(mainViewModel.class);
        mainViewModel.setMovies(this, queue);
        mainViewModel.getMovies().observe(this, new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                if (movies != null) {
                    moviesAdapter.setData(movies);
                    showLoading(false);
                }
            }
        });


        rvMovies.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickCallback(new MoviesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movies data) {

                data.getTitle();
                data.getImages();
                data.getDeskripsi();
                data.getRating();
                data.getRilis();

                Intent detailsMovies = new Intent(MainActivity.this, DetailMovie.class);
                detailsMovies.putExtra(DetailMovie.EXTRA_MOVIES, data);
                startActivity(detailsMovies);
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
