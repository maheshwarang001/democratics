package com.example.democratics.FragmentArticles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.democratics.ApiCOI.ApiUtilityCoi;
import com.example.democratics.COI.model.model;
import com.example.democratics.R;
import com.example.democratics.adapterCOI.adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArcticleActivity extends AppCompatActivity {


    private ArrayList<model> modelArrayList;
    private com.example.democratics.adapterCOI.adapter adapter;
    private RecyclerView recyclerView;
    int page = 0, limit = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coi_articles);




        recyclerView = findViewById(R.id.recycler_coi);

        modelArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false));

        adapter = new adapter(this, modelArrayList);

        recyclerView.setAdapter(adapter);






        try {
            getArticles();
        } catch (Exception e) {
            Log.e("error api", e.toString());
        }

    }


    private void getArticles() {
        ApiUtilityCoi.apiCoi().getList().enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                Log.i("RequestUrl", call.request().url().toString());
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    try {


                        List<model> jsonResponse = response.body();
                        modelArrayList.addAll(jsonResponse);
                        adapter.notifyDataSetChanged();
                    }catch (Exception e){
                        Log.e("onResponse", "There is an error");
                    }

                }
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {
                Log.e("onFailure", "onFailure: " + t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bookmark) {
            startActivity(new Intent(this, ArticleFavouriteCOi.class));
            return true;
        }
        return false;
    }
}