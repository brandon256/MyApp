package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

import static android.os.Build.VERSION_CODES.M;

public class DisplayFile extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        textView=findViewById(R.id.textV);

        String text="";

        try{
            InputStream input=getAssets().open("CFILE.c");
            int size=input.available();
            byte[] buffer=new byte[size];
            input.read(buffer);
            input.close();

            text = new String(buffer);

        }catch (Exception ex){
            ex.printStackTrace();

        }
        textView.setText((CharSequence) text);

    }

}
