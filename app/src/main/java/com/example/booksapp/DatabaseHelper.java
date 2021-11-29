package com.example.booksapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String BOOK_TABLE="Book";

    public static final String _ID="BookID";

    public static final String _Title="Title";
    public static final String _Author="Author";
    public static final String _Obs="Description";
    public static final String _Genre="Genre";
    public static final String _Toread="ToRead";
    public static final String _Tobuy="ToBuy";
    public static final String _Language="Language";
    public static final String _Read="Read";
    public static final String _Owned="Owned";
    public static final String _Progress="InProgress";
    public static final String _TotalPages="TotalPages";
    public static final String _ActualPage="ActualPage";

    public static final String _Rating="Rating";
    public static final String _ReadFrom="ReadFrom";
    public static final String _ReadDate="ReadDate";

    public static final String _Cover="CoverType";
    public static final String _Publisher="Publisher";
    public static final String _Year="YearOfPublication";
    public static final String _PurchaseDate="PurchaseDate";

    public static final String _Type="BookType";

    static final String DB_NAME="BOOKSAPP.DB";
    static final int DB_VERSION=1;

    static public String[] _COLUMNS_NAME={_Title,_Author,_Genre,
            _Obs,_Language,
            _Toread,_Tobuy,_Progress,_Read,_Owned,
            _Cover,_Publisher,_Year,_PurchaseDate,
            _TotalPages,_ActualPage,_Rating,_ReadFrom,_ReadDate,_Type};

    private static final String CREATE_TABLE =" create table "+BOOK_TABLE+"("+
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            _Title+" TEXT NOT NULL, "+
            _Author+" TEXT NOT NULL, "+
            _Toread+ " INTEGER DEFAULT 0, "+
            _Tobuy+ " INTEGER DEFAULT 0, "+
            _Read+" INTEGER DEFAULT 0,"+
            _Owned+" INTEGER DEFAULT 0,"+
            _Progress+" INTEGER DEFAULT 0,"+
            _Genre+ " TEXT, "+
            _Language+ " TEXT, "+
            _Rating+" REAL, "+
            _ReadFrom+ " TEXT, "+
            _ReadDate+ " DATE, "+
            _Cover+ " TEXT, "+
            _Publisher+" TEXT, "+
            _Year+ " TEXT, "+
            _PurchaseDate+" DATE, "+
            _TotalPages+" INT,"+
            _ActualPage+" INT,"+
            _Obs+ " TEXT,"+
            _Type+" INTEGER);";

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