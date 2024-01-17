package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.classmanagement.GVListStudentActivity;
import com.example.classmanagement.R;
import com.example.classmanagement.model.StudentFault;

import java.util.ArrayList;

public class adapterStudentFault extends BaseAdapter {
    private GVListStudentActivity context;
    private ArrayList<StudentFault> ArrayListSt;

    public adapterStudentFault(GVListStudentActivity context, ArrayList<StudentFault> arrayListSt) {
        this.context = context;
        ArrayListSt = arrayListSt;
    }

    @Override
    public int getCount() {
        return  ArrayListSt .size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.liststudentfault, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.tvlisttensv);
        TextView txtMa = (TextView) convertView.findViewById(R.id.tvlistma);

        ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.stinformation);
        ImageButton imgbtnUpdate = (ImageButton) convertView.findViewById(R.id.stupdate);

        StudentFault student = ArrayListSt.get(position);

        txtName.setText(student.getName());
        txtMa.setText(student.getMa());

        int id = student.getId_st();



        return convertView;
    }
}


