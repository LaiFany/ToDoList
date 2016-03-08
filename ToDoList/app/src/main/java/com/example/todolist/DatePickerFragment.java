package com.example.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //use the current date as defualt date in picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //create new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    //puts selected date into edit text
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        EditText editText = (EditText) getActivity().findViewById(R.id.date);
        Calendar cal = Calendar.getInstance();
        cal.set(year,monthOfYear,dayOfMonth);
        editText.setText(dayOfMonth + " " + String.format(Locale.US, "%tB", cal) + " " + year);
    }

}
