package com.example.democratics.MyMinisters;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.democratics.MyMinisters.API.ApiUtility;
import com.example.democratics.MyMinisters.ApiDet.Count;
import com.example.democratics.MyMinisters.ApiDet.Mps;
import com.example.democratics.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberParliamententActivity extends AppCompatActivity  {
    private ArrayList<Mps> arrayListMOP;
    private ArrayList<Mps> arrayListCustom;
    private MinAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewForCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_parliamentent);


        //main recycler
        recyclerView = findViewById(R.id.recycler_all_mop);
        arrayListMOP = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MinAdapter(this, arrayListMOP);
        recyclerView.setAdapter(adapter);


        try {
            findMp();
        } catch (Exception e) {
            Log.e("error api", e.toString());
        }

        //customRecycler
        custom();
    }

    private void custom() {
        recyclerViewForCustom = findViewById(R.id.custom_horizontal_recycler_view);
        arrayListCustom = new ArrayList<>();
        recyclerViewForCustom.setLayoutManager(new LinearLayoutManager
                (this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new MinAdapter(this, arrayListCustom);
        recyclerViewForCustom.setAdapter(adapter);

        customAPi();
    }

    private void customAPi() {
        ApiUtility.apiInt().getListMaha().enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                Log.i("RequestUrl", call.request().url().toString());
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    arrayListCustom.addAll(response.body().getMps());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                Log.e("error api on failure",call.request().url().toString() + t.toString());
            }
        });

    }

    private void findMp() {
        ApiUtility.apiInt().getList().enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                Log.i("RequestUrl", call.request().url().toString());
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    arrayListMOP.addAll(response.body().getMps());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                Log.e("error api on failure",call.request().url().toString() + t.toString());
            }
        });
    }
}