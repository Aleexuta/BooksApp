package com.example.booksapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String BOOK_TABLE="Book";

    public static final String _ID="BookID";
    public static final String _Title="Title";
    public static final String _Author="Author";
    public static final String _Toread="ToRead";
    public static final String _Tobuy="ToBuy";
    public static final String _Rating="Rating";
    public static final String _Language="Language";
    public static final String _Genre="Genre";
    public static final String _ReadFrom="ReadFrom";
    public static final String _Cover="CoverType";
    public static final String _Publisher="Publisher";
    public static final String _Year="YearOfPublication";
    public static final String _PurchaseDate="PurchaseDate";
    public static final String _ReadDate="ReadDate";
    public static final String _Obs="Description";
    public static final String _BookMark="Bookmark";
    public static final String _TypeOfBook="TypeOf";//0-carte normala
    //1-carte citita
    //2-carte detinuta
    //3-carte si citita si detinuta

    static final String DB_NAME="BOOKSAPP.DB";
    static final int DB_VERSION=1;

    private static final String CREATE_TABLE =" create table "+BOOK_TABLE+"("+
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            _Title+" TEXT NOT NULL, "+
            _Author+" TEXT NOT NULL, "+
            _TypeOfBook + " INTEGER DEFAULT 0, "+
            _Toread+ " INTEGER DEFAULT 0, "+
            _Tobuy+ " INTEGER DEFAULT 0, "+
            _Genre+ " TEXT, "+
            _Language+ " TEXT, "+
            _Rating+" INTEGER, "+
            _ReadFrom+ " TEXT, "+
            _Cover+ " TEXT, "+
            _Publisher+" TEXT, "+
            _Year+ " TEXT, "+
            _PurchaseDate+" DATE, "+
            _ReadDate+ " DATE, "+
            _BookMark+ "INTEGER,"+
            _Obs+ " TEXT);";

    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BOOK_TABLE);
        onCreate(sqLiteDatabase);
    }
}