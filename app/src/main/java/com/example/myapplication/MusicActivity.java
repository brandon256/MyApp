package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity {
    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Button play = findViewById(R.id.button);
        Button pause = findViewById(R.id.button2);

       play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   mp.setDataSource("https://firebasestorage.googleapis.com/v0/b/ambulance-36081.appspot.com/o/Music%2FAUD-20201029-WA0434.mp3?alt=media&token=9f5e11ba-167a-4b43-b3a5-e5d4b9f0cf92");
                   mp.prepare();
                   mp.start();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(mp.isPlaying()){
                        mp.pause();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

}