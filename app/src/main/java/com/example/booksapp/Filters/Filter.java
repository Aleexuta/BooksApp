package com.example.booksapp.Filters;

import android.annotation.SuppressLint;

import com.example.booksapp.BookViewForList;
import com.example.booksapp.Books.Language;
import com.example.booksapp.DBManager;
import com.example.booksapp.DatabaseHelper;
import com.example.booksapp.FragmentList;

import java.util.ArrayList;

public class Filter {



    static String selection = "";
    static ArrayList<String> selectionArgs = new ArrayList<>();
    static public boolean[] activePages=new boolean[3];
    static void Filter()
    {
        if(selection.length()>4) {

            selection=selection.substring(0, selection.length() - 4);
            DBManager db = DBManager.getInstance();
            db.selectWhere(selection, selectionArgs.toArray(new String[0]));
            selection="";
            selectionArgs.removeAll(selectionArgs);
        }
        else
        {
            DBManager db = DBManager.getInstance();
            db.loadAllData();

        }

    }
    static void addFilters(String sel,ArrayList<String> selargs)
    {
        selection+=sel;
        selectionArgs.addAll(selargs);
    }

    static void resetActivePages()
    {
        for (boolean x:
             activePages) {
            x=false;
        }
    }
}
