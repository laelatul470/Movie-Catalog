package com.example.moviecatalog;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);
        RequestQueue queue = Volley.newRequestQueue(this);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        final MoviesAdapter moviesAdapter = new MoviesAdapter();
        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(mainViewModel.class);
        mainViewModel.setMovies(this, queue);
        mainViewModel.getMovies().observe(this, new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                if (movies != null) {
                    moviesAdapter .setData(movies);

                }
            }
        });


        rvMovies.setAdapter(moviesAdapter);
    }
}
