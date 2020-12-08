package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        // data to populate the RecyclerView with
        ArrayList<String> movies = new ArrayList<>();
        movies.add("Drunken Master");
        movies.add("Sharon 1,2,3");
        movies.add("Private Resort");
        movies.add("Project Power");
        movies.add("Charle Angels");
        movies.add("Fast and Furious");
        movies.add("Black and Blue");
        movies.add("Gemini Man");
        movies.add("Protector by Johnny Jaa");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.movie_list);
        LinearLayoutManager layoutManager =new LinearLayoutManager(RecycleActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new MyRecyclerViewAdapter(this, movies);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}