package com.example.classmanagement.model;


import java.util.ArrayList;
import java.util.List;

public class StudentViewModel  {
    private static StudentViewModel instance;
    private List<Student> studentsList;

    private StudentViewModel() {
        // Khởi tạo danh sách sinh viên
        studentsList = new ArrayList<>();
    }

    public static synchronized StudentViewModel getInstance() {
        if (instance == null) {
            instance = new StudentViewModel();
        }
        return instance;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }
}

