package com.example.booksapp.Filters;

import com.example.booksapp.DB.DBManager;
import com.example.booksapp.SortFragment;

import java.util.ArrayList;

public class Filter {



    static String selection = "";
    static ArrayList<String> selectionArgs = new ArrayList<>();
    static public boolean[] activePages=new boolean[3];
    static Filter instance=null;

    private FilterFragment first;
    private FilterSecondPage second;
    private FilterThirdPage third;
    private FilterFourthPage fourth;
    private SortFragment sort;

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

    public static void resetFilters()
    {
        instance=new Filter();
    }
    Filter()
    {
        first=new FilterFragment();
        second=new FilterSecondPage();
        third=new FilterThirdPage();
        fourth=new FilterFourthPage();
        sort=new SortFragment();
    }
    static public Filter getInstance()
    {
        if(instance==null)
            instance=new Filter();
        return instance;
    }
    public FilterFragment getFirst() { return  first; }
    public FilterSecondPage getSecond()
    {
        return second;
    }
    public FilterThirdPage getThrid()
    {
        return third;
    }
    public FilterFourthPage getFourth()
    {
        return fourth;
    }
    public SortFragment getSort(){return sort; }
}
