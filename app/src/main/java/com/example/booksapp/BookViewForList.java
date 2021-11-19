package com.example.booksapp;

public class BookViewForList {

    private long M_Id;
    private String M_title;
    private String M_author;

    public BookViewForList(long id, String title, String author)
    {
        M_Id=id;
        M_title=title;
        M_author=author;
    }
    public long getM_Id() {
        return M_Id;
    }

    public void setM_Id(long m_Id) {
        M_Id = m_Id;
    }

    public String getM_title() {
        return M_title;
    }

    public void setM_title(String m_title) {
        M_title = m_title;
    }

    public String getM_author() {
        return M_author;
    }

    public void setM_author(String m_author) {
        M_author = m_author;
    }
}
