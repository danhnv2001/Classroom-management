package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.classmanagement.ADGvActivity;
import com.example.classmanagement.model.Teacher;
import com.example.classmanagement.R;
import java.util.ArrayList;

public class adapterGV extends BaseAdapter {
    private ADGvActivity context;
    private ArrayList<Teacher> ArrayListTeacher;

    public adapterGV(ADGvActivity context, ArrayList<Teacher> arrayListTeacher) {
        this.context = context;
        ArrayListTeacher = arrayListTeacher;
    }


    @Override
    public int getCount() {
        return ArrayListTeacher.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listgv, null);

        TextView TextViewTeacherName =(TextView) convertView.findViewById(R.id.TextViewTeacherName);
        TextView TextViewCode = (TextView) convertView.findViewById(R.id.TextViewCodeTeacher);

        ImageButton imgbtnDelete = (ImageButton) convertView.findViewById(R.id.gvdelete);
        ImageButton imgbtnUpdate = (ImageButton) convertView.findViewById(R.id.gvupdate);
        ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.gvinformation);

        Teacher teacher = ArrayListTeacher.get(position);

        TextViewCode.setText(teacher.getGv_code()+"");
        TextViewTeacherName.setText(teacher.getName());
        int id = teacher.getId_gv();
        imgbtnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);

            }
        });
        imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });
        return convertView;
    }
}



