package com.example.booksapp.Books;

import android.content.ContentValues;

import com.example.booksapp.DatabaseHelper;

import java.util.Date;

public class Book implements IBook {

    public String getM_title() {
        return m_title;
    }

    public String getM_author() {
        return m_author;
    }


    public String getM_obs() {
        return m_obs;
    }

    public BookType getM_genre() {
        return m_genre;
    }

    public boolean isM_toRead() {
        return m_toRead;
    }

    public boolean isM_toBuy() {
        return m_toBuy;
    }

    public Language getM_language() {
        return m_language;
    }

    public boolean isM_inProgress() {
        return m_inProgress;
    }

    public int getM_totalPages() {
        return m_totalPages;
    }

    public int getM_actualPage() {
        return m_actualPage;
    }

    public boolean isM_Read() {
        return m_Read;
    }

    public int getM_rating() {
        return m_rating;
    }

    public ReadFrom getM_readFrom() {
        return m_readFrom;
    }

    public Date getM_readDate() {
        return m_readDate;
    }

    public boolean isM_Owned() {
        return m_Owned;
    }

    public CoverType getM_coverType() {
        return m_coverType;
    }

    public String getM_publisher() {
        return m_publisher;
    }

    public String getM_yearPublication() {
        return m_yearPublication;
    }

    public Date getM_purchaseDate() {
        return m_purchaseDate;
    }

    protected String m_title;
    protected String m_author;
    protected String m_obs;
    protected BookType m_genre;
    protected boolean m_toRead=false;
    protected boolean m_toBuy=false;
    protected Language m_language;

    protected boolean m_inProgress=false;
    protected int m_totalPages=0;
    protected int m_actualPage=0;

    protected boolean m_Read=false;
    protected int m_rating=0;
    protected ReadFrom m_readFrom=ReadFrom.EMPTY;
    protected Date m_readDate =null;

    protected boolean m_Owned=false;
    protected CoverType m_coverType=CoverType.EMPTY;
    protected String m_publisher="";
    protected String m_yearPublication="";
    protected Date m_purchaseDate=null;

    protected int m_typeBook; //0-normala
    //1-progress
    //2-progress+read
    //3-read
    //4-owned
    //5-owned progress
    //6-owned progress+read
    //7-owned read

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

    public Book(String title, String author, BookType genre, String obs, Language lang,
                boolean toread, boolean tobuy, boolean inprogress, boolean read, boolean owned,
                CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                int NrTotalPages, int nrActualPage,int rating, ReadFrom readFrom, Date readDate,
                int tip)
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
        m_typeBook=tip;
    }



    static public IBook getSimpleBook(String title, String author, BookType genre, String obs, Language lang,
                                      boolean toread, boolean tobuy)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,CoverType.EMPTY,"null","null",
                null,0,0,0,ReadFrom.EMPTY,null,0);
    }
    static public IBook getProgressBook(String title, String author, BookType genre, String obs, Language lang,
                                        boolean toread, boolean tobuy,
                                        int nrPagesTotal, int nrActualPage)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,true,false,false,
                CoverType.EMPTY,"null","null",null,nrPagesTotal,nrActualPage,
                0,ReadFrom.EMPTY,null,1);
    }

    static public IBook getProgressReadBook(String title, String author, BookType genre, String obs, Language lang,
                                            boolean toread, boolean tobuy,
                                            int NrTotalPages,int nrActualPage, int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,CoverType.EMPTY,"null","null",
                null,NrTotalPages,nrActualPage,rating,readFrom,readDate,2);
    }
    static public IBook getReadBook(String title, String author, BookType genre, String obs, Language lang,
                                    boolean toread, boolean tobuy,
                                    int NrTotalPages,int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,CoverType.EMPTY,"null","null",
                null,NrTotalPages,0,rating,readFrom,readDate,3);
    }

    static public IBook getOwnedBook(String title, String author, BookType genre, String obs, Language lang,
                                     boolean toread, boolean tobuy,
                                     CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,0,0,0,ReadFrom.EMPTY,null,4);
    }

    static public IBook getOwnedProgressBook(String title, String author, BookType genre, String obs, Language lang,
                                             boolean toread, boolean tobuy,
                                             CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                                             int nrPagesTotal, int nrActualPage)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,nrPagesTotal,nrActualPage,0,ReadFrom.EMPTY,null,5);
    }

    static public IBook getOwnedProgressReadBook(String title, String author, BookType genre, String obs, Language lang,
                                                 boolean toread, boolean tobuy,
                                                 CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                                                 int NrTotalPages, int nrActualPage,int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,NrTotalPages,nrActualPage,rating,readFrom,readDate,6);
    }

    static public IBook getOwnedReadBook(String title, String author, BookType genre, String obs, Language lang,
                                         boolean toread, boolean tobuy,
                                         CoverType coverType, String publisher, String yearOfPublication, Date dateOfPurchase,
                                         int NrTotalPages, int nrActualPage,int rating, ReadFrom readFrom, Date readDate)
    {
        return new Book(title,author,genre,obs,lang,toread,tobuy,
                false,false,false,coverType,publisher,yearOfPublication,
                dateOfPurchase,NrTotalPages,nrActualPage,rating,readFrom,readDate,7);
    }

    public void setReadStatus(boolean status){
        m_toRead=status;
    }
    public void setBuyStatus(boolean status){
        m_toBuy=status;
    }
    protected String getNameBook()
    {
        String sqlquery=" ("+
            DatabaseHelper._Title+", "+ DatabaseHelper._Author+", "+
            DatabaseHelper._Toread+", "+DatabaseHelper._Tobuy+", "+
            DatabaseHelper._Read+", "+DatabaseHelper._Owned+", "+
            DatabaseHelper._Progress+", "+ DatabaseHelper._Genre+", "+
            DatabaseHelper._Language+", "+DatabaseHelper._Obs;
        //0-normala
        //1-progress
        //2-progress+read
        //3-read
        //4-owned
        //5-owned progress
        //6-owned progress+read
        //7-owned read
        if(m_typeBook!=0)
            sqlquery+=", ";
        if(m_typeBook==1)
            sqlquery+=DatabaseHelper._TotalPages+", "+
                    DatabaseHelper._ActualPage;
        if(m_typeBook==2)
            sqlquery+=sqlquery+=DatabaseHelper._TotalPages+", "+
                    DatabaseHelper._ActualPage+", "+
                    DatabaseHelper._Rating+", "+DatabaseHelper._ReadFrom+", "+
                    DatabaseHelper._ReadDate;
        if(m_typeBook==3)
            sqlquery+=DatabaseHelper._Rating+", "+DatabaseHelper._ReadFrom+", "+
                    DatabaseHelper._ReadDate+", "+DatabaseHelper._TotalPages;
        if(m_typeBook==4)
            sqlquery+=DatabaseHelper._Cover+", "+
                    DatabaseHelper._Publisher+", "+DatabaseHelper._Year+","+
                    DatabaseHelper._PurchaseDate;
        if(m_typeBook==5)
            sqlquery+=DatabaseHelper._Cover+", "+
                    DatabaseHelper._Publisher+", "+DatabaseHelper._Year+","+
                    DatabaseHelper._PurchaseDate+", "+DatabaseHelper._TotalPages+", "+
                    DatabaseHelper._ActualPage;
        if(m_typeBook==6)
            sqlquery+=DatabaseHelper._Cover+", "+
                    DatabaseHelper._Publisher+", "+DatabaseHelper._Year+","+
                    DatabaseHelper._PurchaseDate+", "+DatabaseHelper._TotalPages+", "+
                    DatabaseHelper._ActualPage+", "+
                    DatabaseHelper._Rating+", "+DatabaseHelper._ReadFrom+", "+
                    DatabaseHelper._ReadDate;
        if(m_typeBook==7)
            sqlquery+=DatabaseHelper._Cover+", "+
                    DatabaseHelper._Publisher+", "+DatabaseHelper._Year+","+
                    DatabaseHelper._PurchaseDate+", "+DatabaseHelper._Rating+", "+
                    DatabaseHelper._ReadFrom+", "+
                    DatabaseHelper._ReadDate+", "+DatabaseHelper._TotalPages;
        sqlquery+=") ";
        return sqlquery;
    }
    protected String getValueBook()
    {
        int tr=m_toRead ? 1 : 0;
        int tb=m_toBuy ? 1 : 0;
        int r=m_Read ? 1 : 0;
        int o=m_Owned ? 1 : 0;
        int p=m_inProgress ? 1 : 0;
        String sqlquery="('"+m_title+"', '"+m_author+"',"+
                +tr+", "+tb+", "+r+", "+o+", "+p+", '"+
                m_genre.toString()+"', '"+m_language.toString()+"', '"+m_obs+"'";

        if(m_typeBook==1)
            sqlquery+=", " +m_totalPages+", "+m_actualPage;
        if(m_typeBook==2)
            sqlquery+=", "+m_totalPages+", "+m_actualPage+", "+
                    m_rating+", '"+m_readFrom.toString()+"', '"+m_readDate+"'";
        if(m_typeBook==3)
            sqlquery+=", "+m_totalPages+", "+
                    m_rating+", '"+m_readFrom.toString()+"', '"+m_readDate+"'";
        if(m_typeBook==4)
            sqlquery+=", '"+m_coverType.toString()+"', '"+m_publisher+"', '"+m_yearPublication+"', '"+
                    m_purchaseDate+"'";
        if(m_typeBook==5)
            sqlquery+=", '"+m_coverType.toString()+"', '"+m_publisher+"', '"+m_yearPublication+"', '"+
                    m_purchaseDate+"', "+m_totalPages+", "+m_actualPage;
        if(m_typeBook==6)
            sqlquery+=", '"+m_coverType.toString()+"', '"+m_publisher+"', '"+m_yearPublication+"', '"+
                    m_purchaseDate+"', "+m_totalPages+", "+m_actualPage+", "+m_rating+", '"+
                    m_readFrom.toString()+"', '"+m_readDate+"'";
        if(m_typeBook==7)
            sqlquery+=",'"+m_coverType.toString()+"', '"+m_publisher+"', '"+m_yearPublication+"', '"+
                    m_purchaseDate+"', "+m_rating+", '"+m_readFrom.toString()+"', '"+m_readDate+"',"+
                    m_totalPages;
        sqlquery+=");";
        return sqlquery;
    }
    protected String getUpdateString()
    {
        int tr=m_toRead ? 1 : 0;
        int tb=m_toBuy ? 1 : 0;
        int r=m_Read ? 1 : 0;
        int o=m_Owned ? 1 : 0;
        int p=m_inProgress ? 1 : 0;
        String sqlquery="set "+DatabaseHelper._Title+"=' "+m_title+"'," +
                DatabaseHelper._Author+"='"+m_author+"',"+
                DatabaseHelper._Toread+"="+tr+", "+
                DatabaseHelper._Tobuy+"="+tb+", "+
                DatabaseHelper._Read+"="+r+", "+
                DatabaseHelper._Owned+"="+o+", " +
                DatabaseHelper._Progress+"="+p+", " +
                DatabaseHelper._Genre+"='"+m_genre.toString()+"', "+
                DatabaseHelper._Language+"='"+m_language.toString()+"', "+
                DatabaseHelper._Obs+"='"+m_obs+"', "+
                DatabaseHelper._Rating+"="+m_rating+", "+
                DatabaseHelper._ReadFrom+"='"+m_readFrom.toString()+"', "+
                DatabaseHelper._ReadDate+"="+m_readDate+", "+
                DatabaseHelper._Cover+"='"+m_coverType.toString()+"', "+
                DatabaseHelper._Publisher+"='"+m_publisher+"', "+
                DatabaseHelper._Year+"='"+m_yearPublication+"', "+
                DatabaseHelper._PurchaseDate+"="+m_purchaseDate+", "+
                DatabaseHelper._TotalPages+"="+m_totalPages+", "+
                DatabaseHelper._ActualPage+"="+m_actualPage+"";

        return sqlquery;
    }
    public String getInsertSqlString(){
        String sqlquery="INSERT INTO "+ DatabaseHelper.BOOK_TABLE+getNameBook()+" VALUES "+
                getValueBook();
        return sqlquery;
    }

    public ContentValues getValues()
    {
        int tr=m_toRead ? 1 : 0;
        int tb=m_toBuy ? 1 : 0;
        int r=m_Read ? 1 : 0;
        int o=m_Owned ? 1 : 0;
        int p=m_inProgress ? 1 : 0;
        ContentValues cv=new ContentValues();
        cv.put(DatabaseHelper._Author,m_author);
        cv.put(DatabaseHelper._Title,m_title);
        cv.put(DatabaseHelper._Tobuy,tb);
        cv.put(DatabaseHelper._Read,r);
        cv.put(DatabaseHelper._Owned,o);
        cv.put(DatabaseHelper._Progress,p);
        cv.put(DatabaseHelper._Genre,m_genre.toString());
        cv.put(DatabaseHelper._Language,m_language.toString());
        cv.put(DatabaseHelper._Obs,m_obs);
        cv.put(DatabaseHelper._Rating,m_rating);
        cv.put(DatabaseHelper._ReadFrom,m_readFrom.toString());
        cv.put(DatabaseHelper._ReadDate,m_readDate.toString());
        cv.put(DatabaseHelper._Cover,m_coverType.toString());
        cv.put(DatabaseHelper._Publisher,m_publisher);
        cv.put(DatabaseHelper._Year,m_yearPublication);
        cv.put(DatabaseHelper._PurchaseDate,m_purchaseDate.toString());
        cv.put(DatabaseHelper._TotalPages,m_totalPages);
        cv.put(DatabaseHelper._ActualPage,m_actualPage);
        return cv;
    }
    public String getUpdateSqlString()
    {
        String query="Update "+DatabaseHelper.BOOK_TABLE+getUpdateString();
        return query;
    }
}
