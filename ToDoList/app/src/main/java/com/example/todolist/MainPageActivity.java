package com.example.todolist;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import at.markushi.ui.CircleButton;

public class MainPageActivity extends AppCompatActivity {

    private static int taskId = 0;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private static String currentCategory = "All";
    private static int i = 0;
    private static int j = 0;

    ListView list;
    String[] itemname ={
            "All",
            "Personal",
            "Entertainment",
            "Work",
            "Studies",
            "Family"
    };

    Integer[] imgid= {
            R.mipmap.dot1,
            R.mipmap.dot2,
            R.mipmap.dot3,
            R.mipmap.dot4,
            R.mipmap.dot5,
            R.mipmap.dot6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //navigation drawer
        // get list items
        CustomListAdapter adapter1=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.drawer);
        list.setAdapter(adapter1);

        //add header to nav drawer
        View headerView = View.inflate(getBaseContext(), R.layout.drawer_header, null);
        list.addHeaderView(headerView);

        // App Icon
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );

        // Set actionBarDrawerToggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setTitle("All");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // just styling option add shadow the right edge of the drawer
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        //set up header for nav drawer
        ImageView iv = (ImageView) findViewById(R.id.header);
        iv.setEnabled(false);

        final ImageView iv2 = (ImageView) findViewById(R.id.main);

        //set item click listener to navigation drawer items
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                try{
                    String selecteditem = itemname[position - 1];

                    android.support.v7.app.ActionBar bar = getSupportActionBar();
                    CircleButton cBtn = (CircleButton) findViewById(R.id.addBtn);
                    getSupportActionBar().setTitle(selecteditem);
                    if (selecteditem == "All") {
                        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25aaa0")));
                        cBtn.setColor(Color.parseColor("#25aaa0"));
                        displayAll(selecteditem);
                        currentCategory = selecteditem;
                        j = 0;
                        iv2.setImageResource(R.mipmap.main1);
                        // enable status bar tint
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#25aaa0"));
                        }
                    } else if (selecteditem == "Personal") {
                        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e94167")));
                        cBtn.setColor(Color.parseColor("#e94167"));
                        display(selecteditem);
                        currentCategory = selecteditem;
                        j = 1;
                        iv2.setImageResource(R.mipmap.main2);
                        // enable status bar tint
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#e94167"));
                        }
                    } else if (selecteditem == "Entertainment") {
                        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a025aa")));
                        cBtn.setColor(Color.parseColor("#a025aa"));
                        display(selecteditem);
                        currentCategory = selecteditem;
                        j = 2;
                        iv2.setImageResource(R.mipmap.main3);
                        // enable status bar tint
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#a025aa"));
                        }
                    } else if (selecteditem == "Work") {
                        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a599ac")));
                        cBtn.setColor(Color.parseColor("#a599ac"));
                        display(selecteditem);
                        currentCategory = selecteditem;
                        j = 3;
                        iv2.setImageResource(R.mipmap.main4);
                        // enable status bar tint
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#a599ac"));
                        }
                    } else if (selecteditem == "Studies") {
                        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2ab3e6")));
                        cBtn.setColor(Color.parseColor("#2ab3e6"));
                        display(selecteditem);
                        currentCategory = selecteditem;
                        j = 4;
                        iv2.setImageResource(R.mipmap.main5);
                        // enable status bar tint
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#2ab3e6"));
                        }
                    } else if (selecteditem == "Family") {
                        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25aa5e")));
                        cBtn.setColor(Color.parseColor("#25aa5e"));
                        display(selecteditem);
                        currentCategory = selecteditem;
                        j = 5;
                        iv2.setImageResource(R.mipmap.main6);
                        // enable status bar tint
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.parseColor("#25aa5e"));
                        }
                    }
                }catch(ArrayIndexOutOfBoundsException e){

                }
                drawerLayout.closeDrawer(list);
            }
        });

        //handle intent colour changes
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        CircleButton cBtn = (CircleButton) findViewById(R.id.addBtn);
        if(i == 0) {
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25aaa0")));
            bar.setTitle("All");
            cBtn.setColor(Color.parseColor("#25aaa0"));
            displayAll("All");

            iv2.setImageResource(R.mipmap.main1);
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#25aaa0"));
            }
        }
        else if(i == 1){
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e94167")));
            bar.setTitle("Personal");
            cBtn.setColor(Color.parseColor("#e94167"));
            display("Personal");

            iv2.setImageResource(R.mipmap.main2);
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#e94167"));
            }
        }
        else if(i == 2){
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a025aa")));
            bar.setTitle("Entertainment");
            cBtn.setColor(Color.parseColor("#a025aa"));
            display("Entertainment");

            iv2.setImageResource(R.mipmap.main3);
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#a025aa"));
            }
        }
        else if(i == 3){
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#a599ac")));
            bar.setTitle("Work");
            cBtn.setColor(Color.parseColor("#a599ac"));
            display("Work");

            iv2.setImageResource(R.mipmap.main4);
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#a599ac"));
            }
        }
        else if(i == 4){
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2ab3e6")));
            bar.setTitle("Studies");
            cBtn.setColor(Color.parseColor("#2ab3e6"));
            display("Studies");

            iv2.setImageResource(R.mipmap.main5);
            // enable status bar tint
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor("#2ab3e6"));
            }
        }
        else if(i == 5){
            bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25aa5e")));
            bar.setTitle("Family");
            cBtn.setColor(Color.parseColor("#25aa5e"));
            display("Family");

            iv2.setImageResource(R.mipmap.main6);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
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
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //individually delete task
    public void deleteTask(View v){

        String category = null;
        RelativeLayout rl = (RelativeLayout)v.getParent();
        
        TextView et = (TextView) rl.findViewById(R.id.et);
        String task = et.getText().toString();

        DBHelper dbh = new DBHelper(this);
        //delete alarm associated to the deleted task
        taskId = dbh.getIdOfTask(task);
        cancelAlarm(v);

        //retain the category page
        Cursor res = dbh.getData(task);
        if(res != null && res.moveToFirst()){
            category = res.getString(res.getColumnIndex(DBHelper.TODOLIST_COLUMN_CATEGORY));
        }

        //delete task from database
        String sql = String.format("DELETE FROM %s WHERE %s = '%s'", DBHelper.TODOLIST_TABLE_NAME, DBHelper.TODOLIST_COLUMN_NAME, task);

        SQLiteDatabase sqldb = dbh.getWritableDatabase();
        sqldb.execSQL(sql);
        Log.d("delete", "Task " + task + " deleted");

        //check if user deletes task from "All" tab or other tabs
        if(j == 0){
            displayAll("All");
        }
        else{
            display(category);
        }
    }

    //link to addTaskActivity upon function call
    public void addTask(View v){
        Intent intent = new Intent(MainPageActivity.this, AddTaskActivity.class);
        intent.putExtra("category", currentCategory);
        startActivity(intent);
    }

    //cancel alarm operations
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
            Log.d("deleteAlarm", "Alarm deleted!");
        }else{
            Log.d("deleteAlarm", "No alarm.");
        }
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.appicon);

        Intent notificationIntent = new Intent(this, MainPageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, taskId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setFullScreenIntent(pendingIntent, true);
        return builder.build();
    }

    public void displayAll(String category){
        //Activity retrieving data operations.
        DBHelper dbh = new DBHelper(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.wrapperLayout);

        //add all tasks to the homepage if table contains data
        //else add image button to prompt user to create new task
        if(dbh.isTableEmpty(DBHelper.TODOLIST_TABLE_NAME) > 0) {

            ArrayList<String> array_list = dbh.getAllTask();

            final ListView lv = (ListView) findViewById(R.id.mainLV);

            MyAdapter adapter = new MyAdapter(this, array_list);
            lv.setAdapter(adapter);
            Helper.getListViewSize(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Context context = view.getContext();

                    TextView txtitem = (TextView) view.findViewById(R.id.et);
                    String item = txtitem.getText().toString();

                    Intent intent = new Intent(MainPageActivity.this, EditTaskActivity.class);
                    intent.putExtra("name", item);
                    intent.putExtra("category", MainPageActivity.currentCategory);
                    startActivity(intent);
                }
            });
            //hide main image
            ImageView iv = (ImageView) findViewById(R.id.main);
            iv.setVisibility(View.GONE);
            //display list view
            lv.setVisibility(View.VISIBLE);
        }
        else{
            //hide listview
            ListView lv = (ListView) findViewById(R.id.mainLV);
            lv.setVisibility(View.GONE);
            //show main image
            ImageView iv = (ImageView) findViewById(R.id.main);
            iv.setVisibility(View.VISIBLE);
        }
    }

    //display method changes for navigation drawer item onclick
    public void display(String category){
        //Activity retrieving data operations.
        DBHelper dbh = new DBHelper(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.wrapperLayout);

        //add all tasks to the homepage if table contains data
        //else add image button to prompt user to create new task
        if(dbh.isCategoryExist(category) > 0) {

            ArrayList<String> array_list = dbh.getTypeTask(category);

            final ListView lv = (ListView) findViewById(R.id.mainLV);

            MyAdapter adapter = new MyAdapter(this, array_list);

            lv.setAdapter(adapter);
            Helper.getListViewSize(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Context context = view.getContext();

                    TextView txtitem = (TextView) view.findViewById(R.id.et);
                    String item = txtitem.getText().toString();

                    Intent intent = new Intent(MainPageActivity.this, EditTaskActivity.class);
                    intent.putExtra("name", item);
                    intent.putExtra("category", MainPageActivity.currentCategory);
                    startActivity(intent);
                }
            });
            //hide main image and display listview
            ImageView iv = (ImageView) findViewById(R.id.main);
            iv.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
        }
        else{
            //hide listview
            ListView lv = (ListView) findViewById(R.id.mainLV);
            lv.setVisibility(View.GONE);
            //show main image
            ImageView iv = (ImageView) findViewById(R.id.main);
            iv.setVisibility(View.VISIBLE);
        }
    }

    public static void handleIntent(Intent intent){
        if(intent != null && intent.hasExtra("category")){
            currentCategory = intent.getStringExtra("category");
            if(currentCategory.contains("All")){
                i = 0;
            }
            else if(currentCategory.contains("Personal")){
                i = 1;
            }
            else if(currentCategory.contains("Entertainment")){
                i = 2;
            }
            else if(currentCategory.contains("Work")){
                i = 3;
            }
            else if(currentCategory.contains("Studies")){
                i = 4;
            }
            else if(currentCategory.contains("Family")){
                i = 5;
            }
        }
        else{
            i = 0;
        }
    }
}