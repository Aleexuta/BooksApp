package com.example.booksapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.RequiresPermission;

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
        @SuppressLint("Recycle") Cursor cursor=m_database.query(true,DatabaseHelper.BOOK_TABLE,
                DatabaseHelper._COLUMNS_NAME,DatabaseHelper._ID+"=?",new String[] {String.valueOf(id) },
                null,null,null,null);
        IBook book=null;

        if (cursor.moveToFirst()){
            do{
                String tit=cursor.getString(0);
                String aut=cursor.getString(1);
                BookType bt=BookType.valueOf(cursor.getString(2));
                String obs=cursor.getString(3);
                Language lg=Language.valueOf(cursor.getString(4));

                boolean tr= cursor.getString(5).equals("1");
                boolean tb= cursor.getString(6).equals("1");
                boolean p= cursor.getString(7).equals("1");
                boolean r= cursor.getString(8).equals("1");
                boolean o= cursor.getString(9).equals("1");

                String rd=cursor.getString(18);
                String pd=cursor.getString(13);

                String cv=cursor.getString(10);//cover
                String pub=cursor.getString(11);
                String year= cursor.getString(12);

                String tp=cursor.getString(14);
                String ap=cursor.getString(15);
                String rat=cursor.getString(16);

                String rf=cursor.getString(17);

                int tip=Integer.parseInt(cursor.getString(19));


                Date readeddate= new Date(rd);
                Date purchase= new Date(pd);


                //in functie de tipul cartii creez cartea
                switch (tip) {
                    case 0: {
                        book = Book.getSimpleBook(tit, aut, bt, obs, lg, tr, tb);
                        break;
                    }
                    case 1: {
                        book=Book.getProgressBook(tit, aut, bt, obs, lg, tr, tb,Integer.parseInt(tp),Integer.parseInt(ap));
                        break;
                    }
                    case 2:{
                        book=Book.getProgressReadBook(tit, aut, bt, obs, lg, tr, tb,Integer.parseInt(tp),
                                Integer.parseInt(ap),Integer.parseInt(rat), ReadFrom.valueOf(rf),readeddate);
                        break;
                    }
                    case 3:{
                        book=Book.getReadBook(tit, aut, bt, obs, lg, tr, tb,Integer.parseInt(tp),
                                Integer.parseInt(rat),ReadFrom.valueOf(rf),readeddate);
                        break;
                    }
                    case 4: {
                        book=Book.getOwnedBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),pub,year,purchase);
                        break;
                    }
                    case 5: {
                        book=Book.getOwnedProgressBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),
                                pub,year,purchase,Integer.parseInt(tp),Integer.parseInt(ap));
                        break;
                    }
                    case 6:{
                        book=Book.getOwnedProgressReadBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),
                                pub,year,purchase,Integer.parseInt(tp),Integer.parseInt(ap),
                                Integer.parseInt(rat),ReadFrom.valueOf(rf),readeddate);
                        break;
                    }
                    case 7:{
                        book=Book.getOwnedReadBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),
                                pub,year,purchase,Integer.parseInt(tp),Integer.parseInt(rat),
                                ReadFrom.valueOf(rf),readeddate);
                        break;
                    }
                    default:
                        break;

                }
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
        return list;
    }

}
