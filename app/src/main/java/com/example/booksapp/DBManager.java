package com.example.booksapp;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.Date;

public class DBManager {
    DatabaseHelper m_dbHelper;
    private SQLiteDatabase m_database;
    static private DBManager instance=null;
    private MainActivity m_main;

    public void setMain(MainActivity main)
    {
        m_main=main;
    }
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
    public Cursor fetch()
    {

        Cursor cursor=m_database.query(DatabaseHelper.BOOK_TABLE, columns,null,null,null,null,null);

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
        FragmentList fr=(FragmentList)m_main.getSupportFragmentManager().findFragmentByTag("LISTA");
        assert fr != null;
        fr.loadListToView();

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
                                Integer.parseInt(ap), Float.parseFloat(rat), ReadFrom.valueOf(rf),rd);
                        break;
                    }
                    case 3:{
                        book=Book.getReadBook(tit, aut, bt, obs, lg, tr, tb,
                                Integer.parseInt(tp),
                                Float.parseFloat(rat),
                                ReadFrom.valueOf(rf),
                                rd);
                        break;
                    }
                    case 4: {
                        book=Book.getOwnedBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),pub,year,pd);
                        break;
                    }
                    case 5: {
                        book=Book.getOwnedProgressBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),
                                pub,year,pd,Integer.parseInt(tp),Integer.parseInt(ap));
                        break;
                    }
                    case 6:{
                        book=Book.getOwnedProgressReadBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),
                                pub,year,pd,Integer.parseInt(tp),Integer.parseInt(ap),
                                Float.parseFloat(rat),ReadFrom.valueOf(rf),rd);
                        break;
                    }
                    case 7:{
                        book=Book.getOwnedReadBook(tit, aut, bt, obs, lg, tr, tb,CoverType.valueOf(cv),
                                pub,year,pd,Integer.parseInt(tp), Float.parseFloat(rat),
                                ReadFrom.valueOf(rf),rd);
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
        FragmentList fr=(FragmentList)m_main.getSupportFragmentManager().findFragmentByTag("LISTA");
        assert fr != null;
        fr.loadListToView();
    }

    public void delete(long id)
    {
        //stergem cartea cu id ul din book;
        m_database.execSQL("delete from "+ DatabaseHelper.BOOK_TABLE+" where "+DatabaseHelper._ID+" = "+String.valueOf(id));
        FragmentList fr=(FragmentList)m_main.getSupportFragmentManager().findFragmentByTag("LISTA");
        assert fr != null;
        fr.loadListToView();
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

    public ArrayList<BookViewForList> selectWhere(String selection, String[] selectionArgs)
    {
        Cursor cursor=m_database.query(DatabaseHelper.BOOK_TABLE, columns,selection,selectionArgs,null,null,null);



        ArrayList<BookViewForList> list=new ArrayList<>();
        while(cursor.moveToNext())
        {
            long id=cursor.getLong( cursor.getColumnIndexOrThrow(DatabaseHelper._ID));
            String title=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper._Title));
            String author=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper._Author));
            BookViewForList nou=new BookViewForList(id,title,author);
            list.add(nou);
        }

        FragmentList fr=(FragmentList)m_main.getSupportFragmentManager().findFragmentByTag("LISTA");
        assert fr != null;
        fr.loadNewListToView(list);
        return list;
    }

}
