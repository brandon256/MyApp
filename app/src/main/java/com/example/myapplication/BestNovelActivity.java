package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BestNovelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_novel);

        ListView myListView=findViewById(R.id.novel_list);
        final ArrayList<String> myArrayList=new ArrayList<String>();
        myArrayList.add("Oliver Twist");
        myArrayList.add("Treasure Island");
        myArrayList.add("River Between");
        myArrayList.add("Her mother's daughter");
        myArrayList.add("I will marry when I want");
        myArrayList.add("Things Fall am part");
        myArrayList.add("Tough times never last but tough people do");
        myArrayList.add("The winning altitude");
        myArrayList.add("Drunken master");
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        myListView.setAdapter(myAdapter);
    }
}