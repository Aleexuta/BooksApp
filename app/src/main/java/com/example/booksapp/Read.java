package com.example.booksapp;

import java.util.Date;

public class Read extends Book{

    public enum ReadFrom{Library,Borrowed,PDF}

    private int m_rating;
    private  ReadFrom m_fromWhere;
    private Date m_readdate;
    public Read(boolean toread, boolean tobuy, String title, String author,
                  BookType genre, String obs,Language lang,  int rating, ReadFrom from,Date readdate)
    {
        super(toread,tobuy,title,author,genre,obs,lang);
        m_rating=rating;
        m_fromWhere=from;
        m_readdate=readdate;
    }
    public void setRating(short rat)
    {
        m_rating=rat;
    }

    public String getInsertSqlString(){
        String sqlquery=getFirstQueryNormalBook()+", "+
                DatabaseHelper._Rating + ", "+
                DatabaseHelper._ReadFrom+", "+
                DatabaseHelper._ReadDate+", "+
                DatabaseHelper._TypeOfBook+")";
        sqlquery+=getFirstValueNormalBook()+", "+
                this.m_rating+", '"+
                this.m_fromWhere.toString()+ "', '"+
                this.m_readdate+"',1);";
        return sqlquery;

    }
}
