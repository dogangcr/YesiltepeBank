package com.example.yesiltepebank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDataHandler  extends SQLiteOpenHelper{

	//All Static variables
    //Database Version
       private static final int DATABASE_VERSION = 1; 
	   public static final String DATABASE_NAME = "YesiltepeBank.db";
	   public static final String TABLE_NAME = "users";
	   public static final String COLUMN_ID = "id";
	   public static final String COLUMN_USERNAME = "username";
	   public static final String COLUMN_PASSWORD = "password";
	   
 
    public UserDataHandler(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
   	      // TODO Auto-generated method stub
   	      db.execSQL(
   	      "create table users " +
   	      "(id integer primary key autoincrement, username text,password text)"
   	      );
   	 }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           // TODO Auto-generated method stub
           db.execSQL("DROP TABLE IF EXISTS users");
           onCreate(db);
        }

        public void insertUser(Users user)
        {
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues contentValues = new ContentValues();

           contentValues.put("username", user.getUsername());
           contentValues.put("password", user.getPassword());
           db.insert("users", null, contentValues);
           db.close();
        }
 
        public boolean CheckUser(String name ,String pass){	
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?",new String[]{name,pass});
            if (!res.moveToFirst()) {
            	   return false;
            	  }
            int count = res.getInt(0);
            res.close();
            return count > 0;
        }
}
