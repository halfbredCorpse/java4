package com.example.sms_test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SmsManager manager;
    String phoneNum, message;
    EditText txt_phoneNum, txt_message;
    final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = SmsManager.getDefault();
        txt_phoneNum = findViewById(R.id.txt_phoneNum);
        txt_message = findViewById(R.id.txt_message);
    }

    public void sendSms(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},
                MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            phoneNum = txt_phoneNum.getText().toString();
            message = txt_message.getText().toString();

            manager.sendTextMessage(phoneNum, null, message, null, null);

            Toast.makeText(MainActivity.this, "SMS has been sent!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    phoneNum = txt_phoneNum.getText().toString();
                    message = txt_message.getText().toString();

                    manager.sendTextMessage(phoneNum, null, message, null, null);

                    Toast.makeText(MainActivity.this, "SMS has been sent!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Needs your permission to send SMS.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
