package com.example.moviecatalog;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class mainViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private static MutableLiveData<ArrayList<Movies>> listMovies = new MutableLiveData<>();


    public static void setMovies(final Context context, RequestQueue queue) {

        final ArrayList<Movies> listItems = new ArrayList<>();

        String language = "en-US";
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=" + language;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject response) {
                JSONArray data = null;
                try {
                    data = response.getJSONArray("results");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        Movies movies = new Movies();

                        movies.setTitle(jsonObject.getString("title"));

                        movies.setDeskripsi(jsonObject.getString("overview"));
                        movies.setImages(jsonObject.getString("poster_path"));


                        listItems.add(movies);

                    }
                    listMovies.postValue(listItems);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    static LiveData<ArrayList<Movies>> getMovies() {
        return listMovies;
    }

}
