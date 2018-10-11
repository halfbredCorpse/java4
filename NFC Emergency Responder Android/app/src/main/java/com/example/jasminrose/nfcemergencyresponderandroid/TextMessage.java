package com.example.jasminrose.nfcemergencyresponderandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

public class TextMessage {
    SmsManager smsManager;
    Context context;
    private String phoneNum;
    private String message;
    final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    public TextMessage(Context context, String phoneNum, String message) {
        smsManager = SmsManager.getDefault();
        this.setPhoneNum(phoneNum);
        this.setMessage(message);
        this.context = context;
    }

    public void sendSms() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            smsManager.sendTextMessage(getPhoneNum(), null, getMessage(), null, null);

            Toast.makeText(context, "SMS has been sent!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(context, "Needs your permission to send SMS.", Toast.LENGTH_LONG).show();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
