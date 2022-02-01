package com.example.democratics.News.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.democratics.ApiDetails.ModelClass;
import com.example.democratics.ApiDetails.Status;
import com.example.democratics.JSONrequest.ApiUtilities;
import com.example.democratics.News.Adapter;
import com.example.democratics.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFragment extends Fragment {


    private String apiKey = "3dc0a13799d4454b9b40d0abf753014f";
    private ArrayList<ModelClass> modelClassArrayList;
    private Adapter adapter;
    private String country= "in";
    private RecyclerView recyclerView;
    private String category = "business";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.business_fragment,null);

        recyclerView = v.findViewById(R.id.recycler_business);
        modelClassArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext() , modelClassArrayList);
        recyclerView.setAdapter(adapter);

        findNews();
        return v;
    }

    private void findNews() {

        ApiUtilities.apiInterface().getCategroryNews(country,category,40,apiKey).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Log.i("RequestUrl", call.request().url().toString());
                if(response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }
}
