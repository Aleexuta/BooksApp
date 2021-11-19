

package com.example.booksapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    private final Activity context;
    private ArrayList<BookViewForList> bookList;
    //private IBook m_book;
    public MyListAdapter(Activity context, ArrayList<BookViewForList> list)
    {

        this.context=context;
        this.bookList=list;
    }
    @Override
    public int getCount()
    {
        return bookList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View rowview, ViewGroup parent) {
        //LayoutInflater inflater = context.getLayoutInflater();

        rowview = LayoutInflater.from(context).inflate(R.layout.mylist, parent, false);

        BookViewForList currentBook = bookList.get(position);

        TextView title = rowview.findViewById(R.id.listTitle);
        title.setText(currentBook.getM_title());

        TextView autor = rowview.findViewById(R.id.listAuthor);
        autor.setText(currentBook.getM_author());


        return rowview;
    }

}
