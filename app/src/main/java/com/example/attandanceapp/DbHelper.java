package com.example.attandanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AttendanceDB";
    private static final int DATABASE_VERSION = 2; // Version incremented

    public static final String TABLE_STUDENTS = "students";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_ROLL = "roll_no";

    public static final String TABLE_ATTENDANCE = "attendance";
    public static final String COL_ATT_ID = "att_id";
    public static final String COL_STUDENT_ID = "student_id";
    public static final String COL_DATE = "date";
    public static final String COL_TIME = "punch_time"; // New Column for Punching System
    public static final String COL_STATUS = "status";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_STUDENTS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_ROLL + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_ATTENDANCE + " (" + COL_ATT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_STUDENT_ID + " INTEGER, " + COL_DATE + " TEXT, " + COL_TIME + " TEXT, " + COL_STATUS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_ATTENDANCE + " ADD COLUMN " + COL_TIME + " TEXT");
        }
    }

    public boolean addStudent(String name, String rollNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_ROLL, rollNo);
        return db.insert(TABLE_STUDENTS, null, values) != -1;
    }

    public boolean markAttendance(int studentId, String date, String time, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_STUDENT_ID, studentId);
        values.put(COL_DATE, date);
        values.put(COL_TIME, time);
        values.put(COL_STATUS, status);
        return db.insert(TABLE_ATTENDANCE, null, values) != -1;
    }

    public Cursor getAllStudents() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
    }
}
