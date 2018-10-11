package com.example.jasminrose.nfcemergencyresponderandroid.sqlite;

public class LocalRecords {
    public static final String RECORDS_TABLE_NAME = "local_records";
    public static final String USERS_TABLE_NAME = "local_users";
    public static final String CONTACTS_TABLE_NAME = "local_contacts";

    public static final String USER_ID = "user_id";
    public static final String DATE_TIME = "date_time";
    public static final String LOCATION = "location";

    public static final String LAST_NAME = "last_name";
    public static final String FIRST_NAME = "first_name";

    public static final String EMERGENCY_NAME = "emergency_name";
    public static final String EMERGENCY_CONTACT = "emergency_contact";

    public static final String CREATE_RECORDS =
            "CREATE TABLE " + RECORDS_TABLE_NAME + " (" +
                    USER_ID + " VARCHAR," +
                    DATE_TIME + " DATETIME," +
                    LOCATION + " VARCHAR)";

    public static final String DROP_RECORDS =
            "DROP TABLE IF EXISTS " + RECORDS_TABLE_NAME;

    public static final String CREATE_USERS =
            "CREATE TABLE " + USERS_TABLE_NAME + " (" +
                    USER_ID + " VARCHAR PRIMARY KEY NOT NULL," +
                    LAST_NAME + " VARCHAR," +
                    FIRST_NAME + " VARCHAR)";

    public static final String DROP_USERS =
            "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;

    public static final String CREATE_CONTACTS =
            "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" +
                    USER_ID + " VARCHAR," +
                    EMERGENCY_NAME + " VARCHAR," +
                    EMERGENCY_CONTACT + " VARCHAR)";

    public static final String DROP_CONTACTS =
            "DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME;
}
