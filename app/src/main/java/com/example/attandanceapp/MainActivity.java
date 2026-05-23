package com.example.attandanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAddStudent, btnMarkAttendance, btnViewRecords, btnScanQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements initialization
        btnScanQR = findViewById(R.id.btnScanQR);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);
        btnViewRecords = findViewById(R.id.btnViewRecords);

        // QR Scanner / Punching System
        btnScanQR.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ScannerActivity.class));
        });

        btnAddStudent.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
        });

        btnMarkAttendance.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MarkAttendanceActivity.class));
        });

        btnViewRecords.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ViewRecordsActivity.class));
        });
    }
}
