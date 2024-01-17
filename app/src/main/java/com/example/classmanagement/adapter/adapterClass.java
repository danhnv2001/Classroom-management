package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.classmanagement.ADClassActivity;
import com.example.classmanagement.model.Classroom;
import com.example.classmanagement.R;

import java.util.ArrayList;

public class adapterClass extends BaseAdapter {
    private ADClassActivity context;

    //Mảng subject
    private ArrayList<Classroom> ArrayListClass;

    public adapterClass(ADClassActivity context, ArrayList<Classroom> arrayListClass) {
        this.context = context;
        ArrayListClass = arrayListClass;
    }



    @Override
    public int getCount() {
        return ArrayListClass.size();
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

        convertView = inflater.inflate(R.layout.listclassroom, null);

        TextView TextViewClassTitle =(TextView) convertView.findViewById(R.id.TextViewClassTitle);
        TextView TextViewNumbersv = (TextView) convertView.findViewById(R.id.TextViewNumbersv);

        ImageButton imgbtnDelete = (ImageButton) convertView.findViewById(R.id.classdelete);
        ImageButton imgbtnUpdate = (ImageButton) convertView.findViewById(R.id.classupdate);
        ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.classinformation);

        Classroom classroom = ArrayListClass.get(position);

        TextViewNumbersv.setText(classroom.getNumbersv()+"");
        TextViewClassTitle.setText(classroom.getTenlop());

        int id = classroom.getId_class();
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
