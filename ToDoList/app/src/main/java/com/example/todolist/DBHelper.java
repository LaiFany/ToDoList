package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 0126190799 on 8/30/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "todolist.db";
    public static final String TODOLIST_TABLE_NAME = "task";
    public static final String TODOLIST_COLUMN_ID = "id";
    public static final String TODOLIST_COLUMN_NAME = "taskName";
    public static final String TODOLIST_COLUMN_DESC = "taskDescription";
    public static final String TODOLIST_COLUMN_DATE = "taskDate";
    public static final String TODOLIST_COLUMN_TIME = "taskTime";
    public static final String TODOLIST_COLUMN_RTIME = "taskRTime";
    public static final String TODOLIST_COLUMN_CATEGORY = "taskCategory";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table task " +
                        "(id integer primary key, taskName text, taskDate text, taskTime text, taskDescription text, taskRTime text, taskCategory text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS task");
        onCreate(db);
    }

    public boolean addTask  (String name, String date, String time, String desc, String rtime, String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taskName", name);
        contentValues.put("taskDate", date);
        contentValues.put("taskTime", time);
        contentValues.put("taskDescription", desc);
        contentValues.put("taskRTime", rtime);
        contentValues.put("taskCategory", category);
        db.insert("task", null, contentValues);
        return true;
    }

    public Cursor getData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TODOLIST_TABLE_NAME+" where "+TODOLIST_COLUMN_NAME+"=" + "'" + name + "'", null );
        return res;
    }

    public String getDataCategory(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String category = "Personal";
        Cursor res =  db.rawQuery( "select * from "+TODOLIST_TABLE_NAME+" where "+TODOLIST_COLUMN_NAME+"=" + "'" + name + "'", null );
        if(res != null && res.moveToFirst()){
            category = res.getString(res.getColumnIndex(DBHelper.TODOLIST_COLUMN_CATEGORY));
        }
        return category;
    }

    public String getDataDate(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String date = null;
        Cursor res =  db.rawQuery( "select * from "+TODOLIST_TABLE_NAME+" where "+TODOLIST_COLUMN_NAME+"=" + "'" + name + "'", null );
        if(res != null && res.moveToFirst()){
            date = res.getString(res.getColumnIndex(DBHelper.TODOLIST_COLUMN_DATE));
        }
        return date;
    }

    public String getDataTime(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String time = null;
        Cursor res =  db.rawQuery( "select * from "+TODOLIST_TABLE_NAME+" where "+TODOLIST_COLUMN_NAME+"=" + "'" + name + "'", null );
        if(res != null && res.moveToFirst()){
            time = res.getString(res.getColumnIndex(DBHelper.TODOLIST_COLUMN_TIME));
        }
        return time;
    }

    public int getIdOfTask(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + TODOLIST_TABLE_NAME + " where " + TODOLIST_COLUMN_NAME + "=" + "'" + name + "'", null);
        int id = 0;
        if(res != null && res.moveToFirst()) {
            id = Integer.valueOf(res.getString(res.getColumnIndex(TODOLIST_COLUMN_ID)));
        }
        return id;
    }

    public void updateTask (String oriName, String name, String date, String time, String desc, String rtime, String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taskName", name);
        contentValues.put("taskDate", date);
        contentValues.put("taskTime", time);
        contentValues.put("taskDescription", desc);
        contentValues.put("taskRTime", rtime);
        contentValues.put("taskCategory", category);

        db.update(TODOLIST_TABLE_NAME, contentValues, TODOLIST_COLUMN_NAME + "=" + "'" + oriName + "'", null);
    }

    public void deleteAllTask(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TODOLIST_TABLE_NAME);
    }

    public ArrayList<String> getAllTask()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from task", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(TODOLIST_COLUMN_NAME)));
            //array_list.add(res.getString(res.getColumnIndex(TODOLIST_COLUMN_DATE)));
            //array_list.add(res.getString(res.getColumnIndex(TODOLIST_COLUMN_TIME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getTypeTask(String category)
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from task where " + TODOLIST_COLUMN_CATEGORY + " ='" + category + "'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(TODOLIST_COLUMN_NAME)));
            //array_list.add(res.getString(res.getColumnIndex(TODOLIST_COLUMN_DATE)));
            //array_list.add(res.getString(res.getColumnIndex(TODOLIST_COLUMN_TIME)));
            res.moveToNext();
        }
        return array_list;
    }

    public int isCategoryExist(String category){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select count(*) from task where " + TODOLIST_COLUMN_CATEGORY + " ='" + category + "'", null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }

    public int isTableEmpty(String table){
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String sql = "SELECT count(*) FROM " + table;
        Cursor res = sqldb.rawQuery(sql, null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }
}
