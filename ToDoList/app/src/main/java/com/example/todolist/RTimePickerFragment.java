package com.example.todolist;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;


public class RTimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public static int currentHour = 0;
    public static int currentMin = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        EditText editText = (EditText) getActivity().findViewById(R.id.rtime);
        //Calendar cal = Calendar.getInstance();
        //cal.set(hour, minute);
        currentHour = hour;
        currentMin = minute;
        editText.setText(pad(formatHour(hour)) + ":" + pad(minute) + ampm(hour));
    }

    private static String ampm(int hour){
        if(hour < 12){
            return "AM";
        }
        else{
            return "PM";
        }
    }

    private static int formatHour(int hour){
        if(hour == 0){
            return hour+12;
        }

        else if(hour >= 13){
            return hour-12;
        }
        else{
            return hour;
        }
    }

    private static String pad(int a) {
        if (a >= 10)
            return String.valueOf(a);
        else
            return "0" + String.valueOf(a);
    }
}
