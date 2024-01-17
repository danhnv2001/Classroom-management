package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.classmanagement.GVExerciseActivity;
import com.example.classmanagement.GVListClassActivity;
import com.example.classmanagement.R;
import com.example.classmanagement.model.Classroom;

import java.util.ArrayList;
//hiện thông tin lớp học

public class adapterGVClassEx extends BaseAdapter {
    private GVExerciseActivity context;

    //Mảng subject
    private ArrayList<Classroom> ArrayListClass;

    public adapterGVClassEx(GVExerciseActivity context, ArrayList<Classroom> arrayListClass) {
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

        convertView = inflater.inflate(R.layout.listgv_classroomex, null);

        TextView TvName =(TextView) convertView.findViewById(R.id.TvClassNameex);
        TextView TvNumber = (TextView) convertView.findViewById(R.id.TvNumbersvex);

        Classroom classroom = ArrayListClass.get(position);

        TvName.setText(classroom.getTenlop());
        TvNumber.setText(classroom.getNumbersv()+"");

        int id = classroom.getId_class();


        return convertView;
    }
}
