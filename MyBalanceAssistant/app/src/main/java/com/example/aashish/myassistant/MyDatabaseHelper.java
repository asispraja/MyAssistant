package com.example.aashish.myassistant;

import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final  String DATABASENAME="mydatabase";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASENAME,null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS datatable(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,password VARCHAR,site VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS datatable");
        onCreate(db);

    }

    public void addrecord(DataModule m) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",m.getName());
        cv.put("site",m.getSite());
        cv.put("password",m.getPassword());
        //cv.put("id",m.getId());
        db.insert("datatable",null,cv);
        db.close();
    }

    public List<DataModule> readRecords() {
        List<DataModule> mydata=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM datatable",null);
        if (cursor.getCount()!=0){
            if (cursor.moveToFirst()){
                do {
                    DataModule m=new DataModule();
                   // m.setId(cursor.getInt(Integer.parseInt("id")));
                    m.setName(cursor.getString(cursor.getColumnIndex("name")));
                    m.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    m.setSite(cursor.getString(cursor.getColumnIndex("site")));

                    mydata.add(m);

                }while (cursor.moveToNext());
            }
        }
        return  mydata;
    }

    public void deletedata(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM datatable WHERE id="+id);
        db.close();
    }

    public void updateRecords(DataModule m) {
        SQLiteDatabase db=this.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put("name",m.getName());
                cv.put("password",m.getPassword());
                cv.put("site",m.getSite());
                db.update("datatable",cv,"id="+m.getId(),null);
                db.close();
    }
}
