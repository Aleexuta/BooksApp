package com.example.booksapp;

import java.util.Date;

public class ReadOwned extends Owned {

    private int m_rating;
    private Read.ReadFrom m_fromWhere;
    private Date m_readdate;
    public ReadOwned(boolean toread, boolean tobuy, String title, String author, BookType genre,
                     String obs,Language lang,  String publisher, String year, CoverType type, int rating,
                     Read.ReadFrom from,Date readdate, Date purchasedate)
    {
        super(toread,tobuy,title,author,genre,obs,lang,publisher,year,type,purchasedate);
        m_rating=rating;
        m_fromWhere= from;
        m_readdate=readdate;
    }
    public void setRating(short rating)
    {
        m_rating=rating;
    }

    public String getInsertSqlString(){
        String sqlquery=getFirstQueryNormalBook()+", "+
                DatabaseHelper._Cover+", "+
                DatabaseHelper._Publisher+", "+
                DatabaseHelper._Year+ ", "+
                DatabaseHelper._PurchaseDate+", "+
                DatabaseHelper._Rating+", "+
                DatabaseHelper._ReadFrom+", "+
                DatabaseHelper._ReadDate+", "+
                DatabaseHelper._TypeOfBook +") ";
        sqlquery+=getFirstValueNormalBook()+", '"+
                this.m_typebook.toString()+"', '"+
                this.m_publisher+"', '"+
                this.m_year_edition+"', '"+
                this.m_purchasedate.toString()+"', "+
                this.m_rating+", '"+
                this.m_fromWhere.toString()+ "', '"+
                this.m_readdate+"',3 );";
        return sqlquery;
    }
}
