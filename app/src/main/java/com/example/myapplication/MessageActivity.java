package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        String message= getIntent().getStringExtra("message");
        String name= getIntent().getStringExtra("name");
        TextView textView =new TextView(this);
        textView.setText("Name:  "+name+ "\nMessage: "+message);
    }
}
