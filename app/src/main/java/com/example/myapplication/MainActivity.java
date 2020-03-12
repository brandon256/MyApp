package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {

    private EditText msg;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg=findViewById(R.id.message);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(msg.getText().toString())) {
                    Toast.makeText(MainActivity.this, "oops you left that empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    String message= msg.getText().toString();
                    Intent intent =new Intent(MainActivity.this,MessageActivity.class);
                    intent.putExtra("message",message);
                    startActivity(intent);
                    }

            }
        });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.music:

                return true;
            case R.id.c_file:
                return true;
                default:
                    return true;
        }
    }
}
