package com.example.booksapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.booksapp.Books.IBook;

import java.util.ArrayList;

public class DBManager {
    DatabaseHelper m_dbHelper;
    private SQLiteDatabase m_database;
    static private DBManager instance=null;

    static public DBManager getInstance()
    {
        if(instance==null)
            instance=new DBManager();
        return instance;
    }

    private DBManager()
    {
    }
    public DBManager open(Context context) throws SQLException
    {
        m_dbHelper=new DatabaseHelper(context);
        m_database=m_dbHelper.getWritableDatabase();
        return this;
    }

    public Cursor fetch()
    {
        String [] columns=new String[]
                {
                        DatabaseHelper._ID,
                        DatabaseHelper._Title,
                        DatabaseHelper._Author
                       /* DatabaseHelper._TypeOfBook,
                        DatabaseHelper._Toread,
                        DatabaseHelper._Tobuy,
                        DatabaseHelper._Genre,
                        DatabaseHelper._Language,
                        DatabaseHelper._Rating,
                        DatabaseHelper._ReadFrom,
                        DatabaseHelper._Cover,
                        DatabaseHelper._Publisher,
                        DatabaseHelper._Year,
                        DatabaseHelper._PurchaseDate,
                        DatabaseHelper._ReadDate,
                        DatabaseHelper._BookMark,
                        DatabaseHelper._Obs*/
                };
        Cursor cursor=m_database.query(DatabaseHelper.BOOK_TABLE, columns,null,null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        return cursor;
    }
    public void close()
    {
        m_dbHelper.close();
    }

    public void insert(IBook book)
    {
        //luam stringul din book
        //ii dam un refresh in fragment
        String query=book.getInsertSqlString();
        m_database.execSQL(query);

    }

    public void update(int id)
    {
        //luam stringul de update pt carte.

    }

    public void delete(int id)
    {
        //stergem cartea cu id ul din book;
    }
    public ArrayList<BookViewForList> loadAllData()
    {
        ArrayList<BookViewForList> list=new ArrayList<>();
        Cursor cursor=fetch();
        while(cursor.moveToNext())
        {
            long id=cursor.getLong( cursor.getColumnIndexOrThrow(DatabaseHelper._ID));
            String title=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper._Title));
            String author=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper._Author));
            BookViewForList nou=new BookViewForList(id,title,author);
            list.add(nou);
        }
        list.add(new BookViewForList(1,"titlu1", "autor1"));
        list.add(new BookViewForList(2,"titlu1", "autor1"));
        list.add(new BookViewForList(3,"titlu1", "autor1"));

        return list;
    }

}
