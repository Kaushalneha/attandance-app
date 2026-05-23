package com.example.attandanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> studentList;

    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        }

        Student student = studentList.get(position);

        TextView tvName = convertView.findViewById(R.id.tvStudentName);
        RadioGroup rgStatus = convertView.findViewById(R.id.rgStatus);
        RadioButton rbPresent = convertView.findViewById(R.id.rbPresent);
        RadioButton rbAbsent = convertView.findViewById(R.id.rbAbsent);

        // Professional way: Showing Name and Roll No.
        String displayText = student.getName() + " [" + student.getRollNo() + "]";
        tvName.setText(displayText);

        // Reset listener before setting state to avoid scrolling bugs
        rgStatus.setOnCheckedChangeListener(null);

        if ("Present".equals(student.getStatus())) {
            rbPresent.setChecked(true);
        } else {
            rbAbsent.setChecked(true);
        }

        rgStatus.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbPresent) {
                student.setStatus("Present");
            } else if (checkedId == R.id.rbAbsent) {
                student.setStatus("Absent");
            }
        });

        return convertView;
    }
}
