package com.example.attandanceapp;

public class Student {
    private int id;
    private String name;
    private String rollNo;
    private String status; // Present/Absent

    public Student(int id, String name, String rollNo) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.status = "Absent"; // Default
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
