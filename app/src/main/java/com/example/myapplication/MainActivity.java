package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CALL_PHONE = 34;
    private EditText msg,name;
    private Button send;
    Double latitude,longitude;

    BroadcastReceiver myreciever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int y=intent.getIntExtra("level",0);
            TextView view=(TextView)findViewById(R.id.textView4);
            view.setText("Battery At  :"+ Integer.toString(y)+"%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg=findViewById(R.id.message);
        name=findViewById(R.id.editTextTextPersonName);
        send = findViewById(R.id.sendButton);
        registerReceiver(myreciever,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        try {
            Intent intent = new Intent(getApplicationContext(), LocationTrackingService.class);
            startService(intent);
            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));
            Toast.makeText(this, "Lat "+latitude, Toast.LENGTH_SHORT).show();
        }catch (Exception p){
            Toast.makeText(this, ""+p.getMessage(), Toast.LENGTH_LONG).show();
        }

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
                startActivity(new Intent(MainActivity.this, DisplayFile.class));
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

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));
            try {
                Toast.makeText(context, "Longitude "+longitude+" Lat: "+latitude, Toast.LENGTH_SHORT).show();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(LocationTrackingService.str_receiver));
    }

    public void setAlarm(View view) {
        EditText check=(EditText) findViewById(R.id.editTextTextPersonName3);
        String ck=check.getText().toString();
        if(ck.isEmpty()){
            Toast.makeText(this,"set alarm first",Toast.LENGTH_SHORT).show();
        }else {
            EditText editText = (EditText) findViewById(R.id.editTextTextPersonName3);
            int i = Integer.parseInt(editText.getText().toString());
            Intent intent = new Intent(getApplicationContext(), AlarmReciever.class);
            PendingIntent pendingIntent;
            pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);
            Toast.makeText(this, "the time set was " + i + " seconds", Toast.LENGTH_LONG).show();
        }
    }
}
