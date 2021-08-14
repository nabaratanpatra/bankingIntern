package com.example.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class AllTransActivity extends AppCompatActivity {
    private TransViewModel transViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trans);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_allTrans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TransAdapter transAdapter = new TransAdapter();
        recyclerView.setAdapter(transAdapter);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                transViewModel = ViewModelProviders.of(AllTransActivity.this).get(TransViewModel.class);
                transAdapter.setTransactions(transViewModel.getAllTrans());
            }
        });
        t1.start();



    }
}
