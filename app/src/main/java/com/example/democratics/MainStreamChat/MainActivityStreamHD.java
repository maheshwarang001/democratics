package com.example.democratics.MainStreamChat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.democratics.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityStreamHD extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StreamAdapter adapter;
    private DatabaseReference database;
    private ArrayList<ModelInputStream> list;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_main_reycle);


        TextView postbtn = findViewById(R.id.post_btn);
        recyclerView = findViewById(R.id.post_recycle_main);
        swipeRefreshLayout = findViewById(R.id.refresh);




        database = FirebaseDatabase
                .getInstance(" https://democratics-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("ModelInputStream");


        //main recycler
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        adapter = new StreamAdapter(this, list);
        recyclerView.setAdapter(adapter);


        postbtn.setOnClickListener(v -> post());


        try {
            getData();
        } catch (Exception e) {
            Log.e("Error Data Retrive", e.toString());
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                clear();
                // implement Handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // cancle the Visual indication of a refresh
                        swipeRefreshLayout.setRefreshing(false);
                      getData();
                    }
                }, 2000);
            }
        });

    }


    private void getData() {

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ModelInputStream modelInputStream = dataSnapshot.getValue(ModelInputStream.class);
                    list.add(modelInputStream);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void post() {
        Intent intent = new Intent(this, CustomStreamActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    public void clear() {
        int size = list.size();
        list.clear();
        adapter.notifyItemRangeRemoved(0, size);
    }

    @Override
    protected void onStop() {
        super.onStop();
        clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clear();
    }


}