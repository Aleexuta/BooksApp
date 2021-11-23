package com.example.booksapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.booksapp.Books.Book;
import com.example.booksapp.Books.BookType;
import com.example.booksapp.Books.CoverType;
import com.example.booksapp.Books.IBook;
import com.example.booksapp.Books.Language;
import com.example.booksapp.Books.ReadFrom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        //ContentValues cv=book.getValues();
        //m_database.insert(DatabaseHelper.BOOK_TABLE,null,cv);
    }

    @SuppressLint("SimpleDateFormat")
    public IBook getBook(long id)
    {
        Cursor cursor=m_database.query(true,DatabaseHelper.BOOK_TABLE,
                DatabaseHelper._COLUMNS_NAME,DatabaseHelper._ID+"=?",new String[] {String.valueOf(id) },
                null,null,null,null);
        IBook book=null;
        if (cursor.moveToFirst()){
            do{
                boolean tr= cursor.getString(5).equals("1");
                boolean tb= cursor.getString(6).equals("1");
                boolean p= cursor.getString(7).equals("1");
                boolean r= cursor.getString(8).equals("1");
                boolean o= cursor.getString(9).equals("1");

                Date readeddate= null;
                try {
                    readeddate = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(13));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date purchase= null;
                try {
                    purchase = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(18));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                book=new Book(cursor.getString(0),cursor.getString(1),
                        BookType.valueOf(cursor.getString(2)),cursor.getString(3),
                        Language.valueOf(cursor.getString(4)),tr,tb,p,r,o,
                        CoverType.valueOf(cursor.getString(10)),cursor.getString(11),
                        cursor.getString(12),readeddate
                        ,Integer.parseInt(cursor.getString(14)),
                        Integer.parseInt(cursor.getString(15)),
                        Integer.parseInt(cursor.getString(16)),
                        ReadFrom.valueOf(cursor.getString(17)),
                        purchase,
                        Integer.parseInt(cursor.getString(19)));
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        return book;
    }
    public void update(long id,IBook book)
    {
        //luam stringul de update pt carte.
        String query=book.getUpdateSqlString();
        query+= " Where "+DatabaseHelper._ID+" = "+id+";";
        m_database.execSQL(query);
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
