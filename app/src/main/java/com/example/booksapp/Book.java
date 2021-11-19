package com.example.booksapp;

import java.lang.invoke.LambdaConversionException;

public class Book implements IBook{

    public enum Language{Romanian,English}

    public String getM_title() {
        return m_title;
    }



    public String getM_author() {
        return m_author;
    }


    protected String m_title;
    protected String m_author;
    protected String m_obs;
    protected BookType m_genre;
    protected boolean m_toRead=false;
    protected boolean m_toBuy=false;
    protected Language m_language;




    public Book(boolean toread, boolean tobuy, String title, String author, BookType genre, String obs, Language lang)
    {
        super();
        m_toRead=toread;
        m_toBuy=tobuy;
        m_title=title;
        m_author=author;
        m_genre=genre;
        m_obs=obs;
        m_language=lang;
    }
    public void setReadStatus(boolean status){
        m_toRead=status;
    }
    public void setBuyStatus(boolean status){
        m_toBuy=status;
    }
    protected String getFirstQueryNormalBook()
    {
        String sqlquery="Insert into "+DatabaseHelper.BOOK_TABLE+" ( "+
            DatabaseHelper._Title+", "+
            DatabaseHelper._Author+", "+DatabaseHelper._Genre+", "+
            DatabaseHelper._Toread+", "+DatabaseHelper._Tobuy+", "+
            DatabaseHelper._Language+", "+DatabaseHelper._Obs;
        return sqlquery;
    }
    protected String getFirstValueNormalBook()
    {
        int tr=m_toRead ? 1 : 0;
        int tb=m_toBuy ? 1 : 0;
        String sqlquery=" values (' "+this.m_title+"', '"+
                this.m_author+"', '"+
                this.m_genre.toString() +"', "+
                String.valueOf(tr)  + ", "+
                String.valueOf(tb)+", '"+
                this.m_language.toString()+"', '"+
                this.m_obs+"' ";
        return sqlquery;
    }
    public String getInsertSqlString(){
        String sqlquery=getFirstQueryNormalBook()+", "+
                DatabaseHelper._TypeOfBook+ ") " +
                getFirstValueNormalBook()+",0);";
        return sqlquery;
    }
}
