package com.example.jasminrose.nfcemergencyresponderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jasminrose.nfcemergencyresponderandroid.sqlite.SqliteDb;

public class RecordsActivity extends AppCompatActivity {

    ListView listRecords;
    SqliteDb sqliteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listRecords = findViewById(R.id.list_records);
        sqliteDb = new SqliteDb(this);

        sqliteDb.readAllLocalRecords(listRecords, getIntent().getExtras().getString("userId"));
    }


}
