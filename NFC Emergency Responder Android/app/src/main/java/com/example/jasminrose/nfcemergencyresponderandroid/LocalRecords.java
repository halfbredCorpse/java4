package com.example.jasminrose.nfcemergencyresponderandroid;

public class LocalRecords {
    public static final String TABLE_NAME = "local_records";

    public static final String USER_ID = "userId";
    public static final String DATE_TIME = "timestamp";
    public static final String LOCATION = "location";

    public static final String CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_ID + " VARCHAR," +
                    DATE_TIME + " TIMESTAMP," +
                    LOCATION + " VARCHAR)";

    public static final String DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
