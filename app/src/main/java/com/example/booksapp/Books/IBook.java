package com.example.booksapp.Books;

import android.content.ContentValues;

import java.util.Date;

public interface IBook {

    abstract public String getM_title();
    abstract public String getM_author();
    abstract public String getInsertSqlString();
    abstract public String getUpdateSqlString();
    abstract public ContentValues getValues();

    abstract public String getM_obs();
    abstract public BookType getM_genre();
    abstract public boolean isM_toRead();
    abstract public boolean isM_toBuy();
    abstract public Language getM_language();
    abstract public boolean isM_inProgress();
    abstract public int getM_totalPages();
    abstract public int getM_actualPage();
    abstract public boolean isM_Read();
    abstract public int getM_rating();
    abstract public ReadFrom getM_readFrom();
    abstract public Date getM_readDate();
    abstract public boolean isM_Owned();
    abstract public CoverType getM_coverType();
    abstract public String getM_publisher();
    abstract public String getM_yearPublication();
    abstract public Date getM_purchaseDate();
}
