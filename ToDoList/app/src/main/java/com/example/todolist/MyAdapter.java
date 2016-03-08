package com.example.todolist;

import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import at.markushi.ui.CircleButton;

public class MyAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> itemsArrayList;

    public MyAdapter(Context context, ArrayList<String> itemsArrayList) {

        super(context, R.layout.task_items, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DBHelper dbh = new DBHelper(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.task_items, parent, false);

        TextView et1 = (TextView) rowView.findViewById(R.id.et);
        TextView et2 = (TextView) rowView.findViewById(R.id.subet);
        CircleButton dBtn = (CircleButton) rowView.findViewById(R.id.doneButton);
        CardView cv = (CardView) rowView.findViewById(R.id.cv);


        String category = dbh.getDataCategory(itemsArrayList.get(position));
        String date = dbh.getDataDate(itemsArrayList.get(position));
        String time = dbh.getDataTime(itemsArrayList.get(position));

        String color = "#25aaa0";
        String bColor = "#f8fdfd";

        if(category.contains("Personal")){
            color = "#e94167";
            bColor = "#fdf1f4";
        }
        else if(category.contains("Entertainment")){
            color = "#a025aa";
            bColor = "#fdf8fd";
        }
        else if(category.contains("Work")){
            color = "#a599ac";
            bColor = "#fbfafb";
        }
        else if(category.contains("Studies")){
            color = "#2ab3e6";
            bColor = "#ebf8fd";
        }
        else if(category.contains("Family")){
            color = "#25aa5e";
            bColor = "#f8fdfa";
        }

        et1.setText(itemsArrayList.get(position));
        dBtn.setColor(Color.parseColor(color));
        cv.setBackgroundColor(Color.parseColor(bColor));

        if(date.isEmpty() && time.isEmpty()){
            et2.setText("No deadline set.");
        }
        else if(date.isEmpty()){
            et2.setText("Due " + time);
        }
        else if(time.isEmpty()){
            et2.setText("Due " + date);
        }
        else{
            et2.setText("Due " + date + ", " + time);
        }

        return rowView;
    }
}
