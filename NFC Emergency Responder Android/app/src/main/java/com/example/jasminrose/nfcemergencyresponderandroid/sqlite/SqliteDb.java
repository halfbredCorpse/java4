package com.example.jasminrose.nfcemergencyresponderandroid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jasminrose.nfcemergencyresponderandroid.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteDb {

    Context context;
    String timeStamp;
    DatabaseHelper dbh;

    public SqliteDb(Context context) {
        this.context = context;
        dbh = new DatabaseHelper(context);
    }

    public void writeRecordToLocalDatabase(String userId, String location) {
        timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        SQLiteDatabase db = dbh.getWritableDatabase();

        ContentValues localRecordValues = new ContentValues();
        localRecordValues.put(LocalRecords.USER_ID, userId);
        localRecordValues.put(LocalRecords.DATE_TIME, timeStamp);
        localRecordValues.put(LocalRecords.LOCATION, location);

        db.insert(LocalRecords.RECORDS_TABLE_NAME, null, localRecordValues);
    }

    public void readAllLocalRecords(ListView listView, String userId) {
        SQLiteDatabase db = dbh.getReadableDatabase();

        String[] projection = {
            LocalRecords.USER_ID,
            LocalRecords.DATE_TIME,
            LocalRecords.LOCATION
        };

        String selection = LocalRecords.USER_ID + " = ?";
        String[] selectionArgs = {userId};
        String sortOrder = LocalRecords.DATE_TIME + " DESC";

        Cursor cursor = db.query(
                LocalRecords.RECORDS_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List<Map<String, String>> records = new ArrayList<>();

        while(cursor.moveToNext()) {
            Map<String, String> record = new HashMap<>();
            record.put("dateTime", cursor.getString(cursor.getColumnIndexOrThrow(LocalRecords.DATE_TIME)));
            record.put("location", cursor.getString(cursor.getColumnIndexOrThrow(LocalRecords.LOCATION)));
            records.add(record);
        }

        cursor.close();

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, records,
                                        android.R.layout.simple_list_item_2,
                                        new String[] {"dateTime", "location"},
                                        new int[] {android.R.id.text1, android.R.id.text2});

        listView.setAdapter(simpleAdapter);
    }
}
