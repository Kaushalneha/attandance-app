package com.example.attandanceapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MarkAttendanceActivity extends AppCompatActivity {

    ListView lvStudents;
    Button btnSubmitAttendance, btnBackMark;
    DbHelper dbHelper;
    ArrayList<Student> studentList;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        lvStudents = findViewById(R.id.lvStudents);
        btnSubmitAttendance = findViewById(R.id.btnSubmitAttendance);
        btnBackMark = findViewById(R.id.btnBackMark);
        dbHelper = new DbHelper(this);
        studentList = new ArrayList<>();

        loadStudents();

        btnSubmitAttendance.setOnClickListener(v -> {
            if (studentList.isEmpty()) {
                Toast.makeText(this, "No students to mark attendance", Toast.LENGTH_SHORT).show();
            } else {
                saveAttendance();
            }
        });

        if (btnBackMark != null) {
            btnBackMark.setOnClickListener(v -> finish());
        }
    }

    private void loadStudents() {
        Cursor cursor = dbHelper.getAllStudents();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COL_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COL_NAME));
                    String roll = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COL_ROLL));
                    studentList.add(new Student(id, name, roll));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Please add students first!", Toast.LENGTH_LONG).show();
        }

        adapter = new StudentAdapter(this, studentList);
        lvStudents.setAdapter(adapter);
    }

    private void saveAttendance() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String time = "Manual";
        boolean success = true;

        for (Student student : studentList) {

            boolean result = dbHelper.markAttendance(student.getId(), date, time, student.getStatus());
            if (!result) success = false;
        }

        if (success) {
            Toast.makeText(this, "Attendance Saved Successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error saving attendance", Toast.LENGTH_SHORT).show();
        }
    }
}
