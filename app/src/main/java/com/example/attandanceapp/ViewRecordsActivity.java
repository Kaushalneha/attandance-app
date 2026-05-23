package com.example.attandanceapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ViewRecordsActivity extends AppCompatActivity {

    ListView lvAttendanceRecords;
    Button btnBackToHome;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        lvAttendanceRecords = findViewById(R.id.lvAttendanceRecords);
        btnBackToHome = findViewById(R.id.btnBackToHome);
        dbHelper = new DbHelper(this);

        displayRecords();

        btnBackToHome.setOnClickListener(v -> finish());
    }

    private void displayRecords() {
        try {

            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "SELECT a." + DbHelper.COL_ATT_ID + " AS _id, s." + DbHelper.COL_NAME + 
                    ", a." + DbHelper.COL_DATE + ", a." + DbHelper.COL_TIME + ", a." + DbHelper.COL_STATUS + 
                    " FROM " + DbHelper.TABLE_ATTENDANCE + " a INNER JOIN " + DbHelper.TABLE_STUDENTS + " s" +
                    " ON a." + DbHelper.COL_STUDENT_ID + " = s." + DbHelper.COL_ID + 
                    " ORDER BY a." + DbHelper.COL_DATE + " DESC, a." + DbHelper.COL_TIME + " DESC", null);

            if (cursor != null && cursor.getCount() > 0) {
                // Mapping the new COL_TIME to the tvRecordTime TextView
                String[] from = new String[]{DbHelper.COL_NAME, DbHelper.COL_DATE, DbHelper.COL_TIME, DbHelper.COL_STATUS};
                int[] to = new int[]{R.id.tvRecordName, R.id.tvRecordDate, R.id.tvRecordTime, R.id.tvRecordStatus};

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                        this, R.layout.record_item, cursor, from, to, 0);
                lvAttendanceRecords.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No attendance records found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Database Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
