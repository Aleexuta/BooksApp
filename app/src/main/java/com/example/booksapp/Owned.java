package com.example.booksapp;

import java.util.Date;

public class Owned extends Book{

    public enum CoverType{Hardcover,Paperback,Ebook,PDF}
    protected String m_publisher;
    protected String m_year_edition;
    protected CoverType m_typebook;
    protected Date m_purchasedate;
    public Owned(boolean toread, boolean tobuy, String title, String author,
                 BookType genre, String obs,Language lang, String publisher, String year, CoverType type,
                 Date datepurchase)
    {
        super(toread,tobuy,title,author,genre,obs,lang);
        m_publisher=publisher;
        m_year_edition=year;
        m_typebook=type;
        m_purchasedate=datepurchase;
    }

    public String getInsertSqlString(){
        String sqlquery=getFirstQueryNormalBook()+", "+
                DatabaseHelper._Cover+", "+
                DatabaseHelper._Publisher+", "+
                DatabaseHelper._Year+ ", "+
                DatabaseHelper._PurchaseDate+", "+
                DatabaseHelper._TypeOfBook+") ";
        sqlquery+=getFirstValueNormalBook()+", '"+
                this.m_typebook.toString()+"', '"+
                this.m_publisher+"', '"+
                this.m_year_edition+"', "+
                this.m_purchasedate.toString()+", 3);";
        return sqlquery;
    }

}
