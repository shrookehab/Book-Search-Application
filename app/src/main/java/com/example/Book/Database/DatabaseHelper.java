package com.example.Book.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.Book.R;

import java.util.ArrayList;

import com.example.Book.Models.BookDetails;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "books.db";
    private static final String tableName = "books_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Title";
    private static final String COL3 = "img";
    private static final String COL4 = "Author";
    private static final String COL5 = "Description";


    private ArrayList<String> Des ;
    private ArrayList<BookDetails> items ;


    public ArrayList<String> getDes() {
        return Des;
    }
    public ArrayList<BookDetails> getItems() {
        return items;
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context,databaseName, null,1);
        Des = new ArrayList<>();
        items = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + tableName + " ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2 +" TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT)";
        db.execSQL(statement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String stat = "DROP TABLE IF EXISTS books_table";
        db.execSQL(stat);
        onCreate(db);

    }

    public boolean addItems(String title, String img, String author, String description){


        ContentValues contentValues =  new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, img);
        contentValues.put(COL4, author);
        contentValues.put(COL5, description);
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.insert(tableName, null, contentValues);
        db.close();

        if (result == -1){
            return false;
        }
        else{
            return true;
        }



    }

    public void getData ()
    {

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + tableName, null );

        while (data.moveToNext())
        {
            String s1 = data.getString(1);
            String s2 = data.getString(2);
            String s3 = data.getString(3);
            String s4 = data.getString(4);

            items.add(new BookDetails(R.drawable.img1,R.drawable.facebook,R.drawable.instagram,R.drawable.twitter,R.drawable.img2,s2,s1,s3));
            Des.add(s4);

        }

    }

    public boolean checkIfExists (String titleName)
    {

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + tableName, null );

        while (data.moveToNext())
        {
            if (titleName.equals(data.getString(1)) ){
                return true;
            }

        }
        return false;
    }

}
