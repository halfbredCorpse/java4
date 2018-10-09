package com.example.jasminrose.nfcemergencyresponderandroid;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter[] intentFiltersArray;

    TextView txtUserId, txtFullName;
    ListView listContact;
    ConstraintLayout nfcApproach;
    LinearLayout nfcDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcApproach = findViewById(R.id.layout_nfc_approach);
        nfcDetails = findViewById(R.id.layout_nfc_details);

        txtUserId = findViewById(R.id.txt_userid);
        txtFullName = findViewById(R.id.txt_fullname);

        listContact = findViewById(R.id.list_contact);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            Toast.makeText(this, "Device doesn't support NFC", Toast.LENGTH_LONG);
            finish();
            return;
        }

        if (!nfcAdapter.isEnabled())
            Toast.makeText(this, "NFC is disabled", Toast.LENGTH_LONG);

        readFromIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        intentFiltersArray = new IntentFilter[] {tagDetected, techDetected};

        if(nfcAdapter != null)
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, null);
    }

    /*
    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);
    }*/

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        readFromIntent(intent);
        nfcApproach.setVisibility(View.INVISIBLE);
        nfcDetails.setVisibility(View.VISIBLE);
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

            txtUserId.setText(hexdump.toUpperCase().trim());

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
        txtFullName.setText(returnTextRecord(messages, 0));
        List<Map<String, String>> records = new ArrayList<>();
        String r;

        for(int i=1; i<messages[0].getRecords().length; i++) {
            byte[] type = messages[0].getRecords()[i].getType();
            Log.i("typendef", type.toString());
            if(Arrays.equals(type,NdefRecord.RTD_TEXT)) {
                r = returnTextRecord(messages, i);
                Log.i("contactName", r);
                Map<String, String> record = new HashMap<>();
                record.put("name", r.substring(0, r.indexOf('-')));
                record.put("number", r.substring(r.indexOf('-') + 1));
                records.add(record);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this, records,
                                                    android.R.layout.simple_list_item_2,
                                                    new String[] {"name", "number"},
                                                    new int[] {android.R.id.text1, android.R.id.text2});
        listContact.setAdapter(adapter);
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
