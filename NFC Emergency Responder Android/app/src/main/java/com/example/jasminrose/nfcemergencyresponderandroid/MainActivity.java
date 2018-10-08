package com.example.jasminrose.nfcemergencyresponderandroid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    Tag mytag;
    Context context;
    TextView txt_nfc_serial, txt_firstname;
    IntentFilter[] intentFiltersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nfc_serial = findViewById(R.id.txt_nfc_serial);
        txt_firstname = findViewById(R.id.txt_firstname);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            Toast.makeText(this, "Device doesn't support NFC", Toast.LENGTH_LONG);
            finish();
            return;
        }

        if (!nfcAdapter.isEnabled())
            Toast.makeText(this, "NFC is disabled", Toast.LENGTH_LONG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        intentFiltersArray = new IntentFilter[] {tagDetected};

        if(nfcAdapter != null)
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        readFromIntent(intent);
    }


    private void readFromIntent(Intent intent) {
        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            byte[] nfcId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
            String hexdump = new String();

            for (int i = 0; i < nfcId.length; i++) {
                String x = Integer.toHexString(((int) nfcId[i] & 0xff));

                if (x.length() == 1)
                    x = '0' + x;

                hexdump += x + ' ';
            }

            txt_nfc_serial.setText(hexdump);

            Parcelable[] rawMessges = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] messages = null;

            if (rawMessges != null) {
                messages = new NdefMessage[rawMessges.length];

                for (int i = 0; i < rawMessges.length; i++)
                    messages[i] = (NdefMessage) rawMessges[i];
            }

            buildTagViews(messages);
        }
    }

    private void buildTagViews(NdefMessage[] messages) {
        StringBuilder records = new StringBuilder();

        for(int i=0; i<messages[0].getRecords().length; i++) {
            records.append(returnTextRecord(messages, i)).append("\n");
        }

        txt_firstname.setText(records.toString());

        Toast.makeText(this, "Successfully read tag.", Toast.LENGTH_SHORT).show();
    }

    public String returnTextRecord(NdefMessage[] messages, int recordIndex) {
        if (messages == null || messages.length == 0)
            return null;

        String text;

        byte[] payload = messages[0].getRecords()[recordIndex].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
        int langCodeLength = payload[0] & 0063;
        try {
            text = new String(payload, langCodeLength + 1, payload.length - langCodeLength - 1,
                    textEncoding);

            return text;
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        return null;
    }
}
