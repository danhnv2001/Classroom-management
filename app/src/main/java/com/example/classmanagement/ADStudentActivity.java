package com.example.classmanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.adapter.adapterStudent;
import com.example.classmanagement.model.Student;
import com.example.classmanagement.model.StudentViewModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ADStudentActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private Button btnAdd,btnDelete;
    private List<Student> studentsList;
    private adapterStudent studentAdapter;
    DBHelper databaseHelper;
    int id_student=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adstudent);
        databaseHelper = new DBHelper(this);
        // Kiểm tra quyền đọc từ bộ nhớ
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    PICK_FILE_REQUEST_CODE);
        }

        studentsList = new ArrayList<>() ;
        recyclerView = findViewById(R.id.rcv_student);
        btnAdd = findViewById(R.id.btn_upload);
        btnDelete= findViewById(R.id.btn_delete);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickExcelFile();
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new adapterStudent(new adapterStudent.StudentListener() {
            @Override
            public void onClick(Student student) {
                showDialog(student);
            }
        });
        recyclerView.setAdapter(studentAdapter);
    }

    private void showDialog(Student student) {
        final CustomDialog dialog = new CustomDialog(student, this);
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                readExcelFile(uri);
            }
        }
    }

    private void readExcelFile(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String stt = getCellValue(row.getCell(0));
                String ten = getCellValue(row.getCell(1));
                String ma = getCellValue(row.getCell(2));
                String gioiTinh = getCellValue(row.getCell(3));
                String nganh = getCellValue(row.getCell(4));
                String diem1 = getCellValue(row.getCell(5));
                String diem2 = getCellValue(row.getCell(6));
                String diem3 = getCellValue(row.getCell(7));

                Student student = new Student();
                student.setStt(stt.substring(0,stt.length()-2));
                student.setTen(ten);
                student.setMa(ma);
                student.setGioiTinh(gioiTinh);
                student.setNganh(nganh);
                student.setDiem1(removeDecimal(diem1));
                student.setDiem2(removeDecimal(diem2));
                student.setDiem3(removeDecimal(diem3));
                String diem4=String.format("%.1f",(Float.parseFloat(diem1) * 0.1f + Float.parseFloat(diem2) * 0.3f  +Float.parseFloat(diem3)* 0.6f));
                student.setDiem4(removeDecimal(diem4));


                studentsList.add(student);
                databaseHelper.AddStudent(student);
            }
            studentAdapter.setData(studentsList);

            recyclerView.setAdapter(studentAdapter);

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String removeDecimal(String str) {
        if (str.endsWith(".0")) {
            return str.substring(0, str.length() - 2);
        }
        return str;
    }



    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private void pickExcelFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Chấp nhận mọi loại tệp
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);

    }

}




