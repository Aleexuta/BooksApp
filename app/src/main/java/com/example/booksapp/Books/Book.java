package com.example.booksapp.Books;

import com.example.booksapp.DatabaseHelper;

import java.util.Date;

public class Book implements IBook {

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

    protected boolean m_inProgress=false;
    int m_totalPages=0;
    int m_actualPage=0;

    protected boolean m_Read=false;
    int m_rating=0;
    ReadFrom m_readFrom=null;
    Date m_readDate =null;

    protected boolean m_Owned=false;
    CoverType m_coverType=null;
    String m_publisher=null;
    String m_yearPublication=null;
    Date m_purchaseDate=null;


    private void setSimpleBook(boolean toread, boolean tobuy, String title, String author, BookType genre, String obs, Language lang)
    {
        m_toRead=toread;
        m_toBuy=tobuy;
        m_title=title;
        m_author=author;
        m_genre=genre;
        m_obs=obs;
        m_language=lang;
    }

    //cumparata si citita
    public Book(String title, String author, BookType genre, String obs, Language lang,
                boolean toread, boolean tobuy, boolean inprogress, boolean read, boolean owned,
                CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                int NrTotalPages, int nrActualPage,int rating, ReadFrom readFrom, Date readDate)
    {
        setSimpleBook(tobuy,toread,title,author,genre,obs,lang);
        m_inProgress=inprogress;
        m_Read=read;
        m_Owned=owned;
        m_coverType=coverType;
        m_publisher=publisher;
        m_yearPublication=yearOfPublication;
        m_purchaseDate=dateOfPurchase;
        m_totalPages=NrTotalPages;
        m_actualPage=nrActualPage;
        m_rating=rating;
        m_readFrom=readFrom;
        m_readDate=readDate;
    }



    static public IBook getSimpleBook(String title, String author, BookType genre, String obs, Language lang,
                                      boolean toread, boolean tobuy)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,null,null,null,
                null,0,0,0,null,null);
    }
    static public IBook getProgressBook(String title, String author, BookType genre, String obs, Language lang,
                                        boolean toread, boolean tobuy,
                                        int nrPagesTotal, int nrActualPage)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,true,false,false,
                null,null,null,null,nrPagesTotal,nrActualPage,0,null,null);
    }

    static public IBook getProgressReadBook(String title, String author, BookType genre, String obs, Language lang,
                                            boolean toread, boolean tobuy,
                                            int NrTotalPages,int nrActualPage, int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,null,null,null,
                null,NrTotalPages,nrActualPage,rating,readFrom,readDate);
    }
    static public IBook getReadBook(String title, String author, BookType genre, String obs, Language lang,
                                    boolean toread, boolean tobuy,
                                    int NrTotalPages,int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,null,null,null,
                null,NrTotalPages,0,rating,readFrom,readDate);
    }

    static public IBook getOwnedBook(String title, String author, BookType genre, String obs, Language lang,
                                     boolean toread, boolean tobuy,
                                     CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,0,0,0,null,null);
    }

    static public IBook getOwnedProgressBook(String title, String author, BookType genre, String obs, Language lang,
                                             boolean toread, boolean tobuy,
                                             CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                                             int nrPagesTotal, int nrActualPage)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,nrPagesTotal,nrActualPage,0,null,null);
    }

    static public IBook getOwnedProgressReadBook(String title, String author, BookType genre, String obs, Language lang,
                                                 boolean toread, boolean tobuy,
                                                 CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                                                 int NrTotalPages, int nrActualPage,int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,NrTotalPages,nrActualPage,rating,readFrom,readDate);
    }

    static public IBook getOwnedReadBook(String title, String author, BookType genre, String obs, Language lang,
                                         boolean toread, boolean tobuy,
                                         CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                                         int NrTotalPages, int nrActualPage,int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,NrTotalPages,nrActualPage,rating,readFrom,readDate);
    }

    public void setReadStatus(boolean status){
        m_toRead=status;
    }
    public void setBuyStatus(boolean status){
        m_toBuy=status;
    }
    protected String getNameBook()
    {
        String sqlquery="Insert into "+ DatabaseHelper.BOOK_TABLE+" ( "+
            DatabaseHelper._Title+", "+ DatabaseHelper._Author+", "+
            DatabaseHelper._Toread+", "+DatabaseHelper._Tobuy+", "+
            DatabaseHelper._Read+", "+DatabaseHelper._Owned+", "+
            DatabaseHelper._Progress+", "+ DatabaseHelper._Genre+", "+
            DatabaseHelper._Language+", "+DatabaseHelper._Obs+", "+
            DatabaseHelper._Rating+", "+DatabaseHelper._ReadFrom+", "+
            DatabaseHelper._ReadDate+", "+DatabaseHelper._Cover+", "+
            DatabaseHelper._Publisher+", "+DatabaseHelper._Year+","+
            DatabaseHelper._PurchaseDate+", "+DatabaseHelper._TotalPages+", "+
            DatabaseHelper._ActualPage+") ";
        return sqlquery;
    }
    protected String getValueBook()
    {
        int tr=m_toRead ? 1 : 0;
        int tb=m_toBuy ? 1 : 0;
        int r=m_Read ? 1 : 0;
        int o=m_Owned ? 1 : 0;
        int p=m_inProgress ? 1 : 0;
        String sqlquery=" values (' "+m_title+"', '"+m_author+"', '"+
                "', "+tr+", "+tb+", "+r+", "+o+", "+p+", '"+
                m_genre+"', '"+m_language+"', '"+m_obs+"', "+
                m_rating+", '"+m_readFrom+"', "+m_readDate+", '"+
                m_coverType+"', "+m_publisher+"', '"+m_yearPublication+"', "+
                m_purchaseDate+", "+m_totalPages+", "+m_actualPage+");";

        return sqlquery;
    }
    public String getInsertSqlString(){
        String sqlquery=getNameBook()+
                getValueBook();
        return sqlquery;
    }
}
