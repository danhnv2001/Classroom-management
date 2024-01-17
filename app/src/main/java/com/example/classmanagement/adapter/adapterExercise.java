package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.classmanagement.GVListExerciseActivity;
import com.example.classmanagement.R;
import com.example.classmanagement.model.Exercise;
import java.util.ArrayList;

public class adapterExercise extends BaseAdapter {
    private GVListExerciseActivity context;
    private ArrayList<Exercise> ArrayListEx;

    public adapterExercise(GVListExerciseActivity context, ArrayList<Exercise> arrayListEx) {
        this.context = context;
        ArrayListEx = arrayListEx;
    }

    @Override
    public int getCount() {
        return  ArrayListEx .size();
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

        convertView = inflater.inflate(R.layout.listexercise, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.tvlisttenbai);
        TextView txtHan = (TextView) convertView.findViewById(R.id.tvlisthanlam);

        ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.exinformation);

        Exercise exercise = ArrayListEx.get(position);

        txtName.setText(exercise.getTenbai());
        txtHan.setText(exercise.getHanlam());

        int id = exercise.getId_ex();
        imgbtnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        return convertView;
    }
}
