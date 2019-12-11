package com.example.moviecatalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailMovie extends AppCompatActivity {
    public static final String EXTRA_MOVIES = "extra_movie";
    Movies movies;
    TextView detailTitle, detailRelease, detailDescription, detailRatings;
    ImageView detailImg;
    String urlImg = "https://image.tmdb.org/t/p/w780/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Detail Movie");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        detailImg = findViewById(R.id.detail_img_mv);
        detailTitle = findViewById(R.id.detail_judul_mv);
        detailRatings = findViewById(R.id.detail_rating_mv);
        detailRelease = findViewById(R.id.detail_rilis_mv);
        detailDescription = findViewById(R.id.detail_desc_mv);
        movies = getIntent().getParcelableExtra(EXTRA_MOVIES);


        if (movies != null) {
            Glide.with(this)
                    .load(urlImg + movies.getImages())
                    .apply(new RequestOptions().override(500, 500))
                    .into(detailImg);
            detailTitle.setText(movies.getTitle());
            detailRelease.setText(movies.getRating());
            detailDescription.setText(movies.getDeskripsi());
            detailRatings.setText(movies.getRilis());

        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            this.finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
