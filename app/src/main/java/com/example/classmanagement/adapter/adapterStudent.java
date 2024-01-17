package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classmanagement.R;
import com.example.classmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;

public class adapterStudent extends RecyclerView.Adapter<adapterStudent.StudentHolder> {

    List<Student> students;

    StudentListener studentListener;
    public interface StudentListener{
        void onClick(Student student);
    }

    public adapterStudent(StudentListener studentListener){
        students = new ArrayList<>();
        this.studentListener = studentListener;
    }

    public void setData(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = students.get(position);
        holder.tvID.setText(student.getStt());
        holder.tvName.setText(student.getTen());
        holder.tvMssv.setText(student.getMa());
        holder.tvSex.setText(student.getGioiTinh());
        holder.tvMajor.setText(student.getNganh());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentListener.onClick(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder{
        TextView tvID, tvName, tvMssv, tvSex, tvMajor;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);

            this.tvID = itemView.findViewById(R.id.tv_id);
            this.tvName = itemView.findViewById(R.id.tv_name);
            this.tvMssv = itemView.findViewById(R.id.tv_mssv);
            this.tvSex = itemView.findViewById(R.id.tv_sex);
            this.tvMajor = itemView.findViewById(R.id.tv_major);

        }
    }
}
