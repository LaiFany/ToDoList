package com.example.todolist;

import com.example.todolist.R;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import at.markushi.ui.CircleButton;


public class AddTaskActivity extends ActionBarActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private static String category = "All";
    private static int taskId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.category);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        //date picker fragment
        EditText et = (EditText) findViewById(R.id.date);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dpf = new DatePickerFragment();
                dpf.show(getFragmentManager(), "datePicker");
            }
        });

        //time picker fragment
        EditText et2 = (EditText) findViewById(R.id.time);
        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment tpf = new TimePickerFragment();
                tpf.show(getFragmentManager(), "timePicker");
            }
        });

        //set visibility of reminder date and time textviews
        final EditText ett1 = (EditText) findViewById(R.id.rtime);

        ett1.setVisibility(View.GONE);

        CheckBox cb = (CheckBox) findViewById(R.id.reminder);
        cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox) v).isChecked()){
                    ett1.setVisibility(View.VISIBLE);
                }
                else{
                    ett1.setVisibility(View.GONE);
                }
            }
        });

        //reminder time picker fragment
        EditText et3 = (EditText) findViewById(R.id.rtime);

        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RTimePickerFragment rtpf = new RTimePickerFragment();
                rtpf.show(getFragmentManager(), "rTimePicker");
            }
        });

        //change theme colour according to category
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        category = getIntent().getStringExtra("category");

        CircleButton sBtn = (CircleButton) findViewById(R.id.save);
        if (category.contains("All")) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25aaa0")));
            sBtn.setColor(Color.parseColor("#25aaa0"));
            cb.setTextColor(Color.parseColor("#25aaa0"));
            spinner.setSelection(adapter.getPosition("Personal"));
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#25aaa0"));
            }
        } else if (category.contains("Personal")) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e94167")));
            sBtn.setColor(Color.parseColor("#e94167"));
            cb.setTextColor(Color.parseColor("#e94167"));
            spinner.setSelection(adapter.getPosition("Personal"));
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#e94167"));
            }
        } else if (category.contains("Entertainment")) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a025aa")));
            sBtn.setColor(Color.parseColor("#a025aa"));
            cb.setTextColor(Color.parseColor("#a025aa"));
            spinner.setSelection(adapter.getPosition(category));
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#a025aa"));
            }
        } else if (category.contains("Work")) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a599ac")));
            sBtn.setColor(Color.parseColor("#a599ac"));
            cb.setTextColor(Color.parseColor("#a599ac"));
            spinner.setSelection(adapter.getPosition(category));
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#a599ac"));
            }
        } else if (category.contains("Studies")) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2ab3e6")));
            sBtn.setColor(Color.parseColor("#2ab3e6"));
            cb.setTextColor(Color.parseColor("#2ab3e6"));
            spinner.setSelection(adapter.getPosition(category));
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#2ab3e6"));
            }
        } else if (category.contains("Family")) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25aa5e")));
            sBtn.setColor(Color.parseColor("#25aa5e"));
            cb.setTextColor(Color.parseColor("#25aa5e"));
            spinner.setSelection(adapter.getPosition(category));
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#25aa5e"));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainPageActivity.class);
                intent.putExtra("category", category);
                MainPageActivity.handleIntent(intent);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void save(View v){
        EditText et = (EditText) findViewById(R.id.task);
        EditText et1 = (EditText) findViewById(R.id.date);
        EditText et2 = (EditText) findViewById(R.id.time);
        EditText et3 = (EditText) findViewById(R.id.desc);
        EditText et4 = (EditText) findViewById(R.id.rtime);
        Spinner spinner = (Spinner) findViewById(R.id.category);
        DBHelper dbh = new DBHelper(this);
        CheckBox cb = (CheckBox) findViewById(R.id.reminder);
        if(cb.isChecked()){
                dbh.addTask(et.getText().toString(), et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), spinner.getSelectedItem().toString());

                taskId = Integer.valueOf(dbh.getIdOfTask(et.getText().toString()));
                setAlarm(v);
            }
        else{
            dbh.addTask(et.getText().toString(), et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), spinner.getSelectedItem().toString());

            taskId = Integer.valueOf(dbh.getIdOfTask(et.getText().toString()));
            cancelAlarm(v);
        }
        Intent intent = new Intent(this, MainPageActivity.class);
        intent.putExtra("category", category);
        MainPageActivity.handleIntent(intent);
        startActivity(intent);
    }

    //Alarm methods
    public void setAlarm(View v){
        scheduleNotification(getNotification("Unfinished task!"));
    }

    public void cancelAlarm(View v){
        cancelScheduleNotification(getNotification(""));
    }

    private void cancelScheduleNotification(Notification notification){
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, taskId);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, taskId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }else{
            Toast.makeText(this, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void scheduleNotification(Notification notification) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, taskId);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, taskId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        Calendar calnow = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, RTimePickerFragment.currentHour);
        cal.set(Calendar.MINUTE, RTimePickerFragment.currentMin);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if(cal.before(calnow)){//if its in the past increment
            cal.add(Calendar.DATE,1);
        }

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
    }

    private Notification getNotification(String content) {
        EditText et = (EditText) findViewById(R.id.task);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(et.getText().toString());
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.appicon);

        Intent notificationIntent = new Intent(this, MainPageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, taskId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setFullScreenIntent(pendingIntent, true);
        return builder.build();
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainPageActivity.class);
        intent.putExtra("category", category);
        MainPageActivity.handleIntent(intent);
        startActivity(intent);
    }
}
