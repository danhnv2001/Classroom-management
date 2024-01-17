package com.example.classmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.classmanagement.ADClassActivity;
import com.example.classmanagement.GVListClassActivity;
import com.example.classmanagement.R;
import com.example.classmanagement.model.Classroom;

import java.util.ArrayList;

public class adapterGvClass extends BaseAdapter{
        private GVListClassActivity context;

        //Mảng subject
        private ArrayList<Classroom> ArrayListClass;

        public adapterGvClass(GVListClassActivity context, ArrayList<Classroom> arrayListClass) {
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

            convertView = inflater.inflate(R.layout.listgv_classroom, null);

            TextView TvName =(TextView) convertView.findViewById(R.id.tvClassName);
            TextView TvDay = (TextView) convertView.findViewById(R.id.tvClassDay);
            ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.classgvinformation);
            Classroom classroom = ArrayListClass.get(position);

            TvName.setText(classroom.getTenlop());
            TvDay.setText(classroom.getThu()+"");

            int id = classroom.getId_class();
            imgbtnInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.informationclass(id);
                }
            });


            return convertView;
        }
}

