package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CALL_PHONE = 34;
    private EditText msg,name;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg=findViewById(R.id.message);
        name=findViewById(R.id.editTextTextPersonName);
        send = findViewById(R.id.sendButton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(msg.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) ) {
                    Toast.makeText(MainActivity.this, "oops you left that empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    String message = msg.getText().toString();
                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    intent.putExtra("message", message);
                    intent.putExtra("name", name.getText().toString());
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
                startActivity(new Intent(MainActivity.this, MusicActivity.class));

                return true;
            case R.id.c_file:
                Toast.makeText(this, "C FILE", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.email:
              sendEmail();
                return true;

            case R.id.recycler:
                startActivity(new Intent(MainActivity.this, RecycleActivity.class));
                return true;
            case R.id.list:
               startActivity(new Intent(MainActivity.this, BestNovelActivity.class));
                return true;
            case R.id.call_phone:
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE);
                } else {

                        try {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + "0759157060"));
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                }
                return true;
                default:
                    return true;
        }
    }

    public void openMenu(View view) {
        startActivity(new Intent());
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"kyorigarurab@gmail.com"};
        String[] CC = {"kyorigarurabrandon@gmail.com"};
        String[] TOTO = {"jimmytrevor@fuelupug.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, CC);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TOTO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Greetings");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "How are you");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("mail", "Sending");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
