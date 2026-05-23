package com.example.attandanceapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    EditText etStudentName, etRollNo;
    Button btnSaveStudent, btnBack;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etStudentName = findViewById(R.id.etStudentName);
        etRollNo = findViewById(R.id.etRollNo);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);
        btnBack = findViewById(R.id.btnBack);
        dbHelper = new DbHelper(this);

        btnSaveStudent.setOnClickListener(v -> {
            String name = etStudentName.getText().toString().trim();
            String rollNo = etRollNo.getText().toString().trim();

            if (name.isEmpty() || rollNo.isEmpty()) {
                Toast.makeText(AddStudentActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isInserted = dbHelper.addStudent(name, rollNo);
                if (isInserted) {
                    Toast.makeText(AddStudentActivity.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Student add hote hi wapas home par chale jayenge
                } else {
                    Toast.makeText(AddStudentActivity.this, "Error adding student", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
