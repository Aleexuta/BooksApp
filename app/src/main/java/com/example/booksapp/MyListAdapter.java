

package com.example.booksapp;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.SortedList;

import com.example.booksapp.Books.EditBook;
import com.example.booksapp.DB.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MyListAdapter extends BaseAdapter implements Filterable{

    private final Activity context;
    private ArrayList<BookViewForList> bookList;
    private ArrayList<BookViewForList> totalList;
    private ArrayList<BookViewForList> sortedlist;
    private ListFilter valueFilter;
    //private IBook m_book;
    public MyListAdapter(Activity context, ArrayList<BookViewForList> list)
    {

        this.context=context;
        this.bookList=list;
        this.totalList=list;
    }
    public void SortByTitle()
    {
        Collections.sort(bookList, new Comparator<BookViewForList>() {
            @Override
            public int compare(BookViewForList o1, BookViewForList o2) {
                return o1.getM_title().compareToIgnoreCase(o2.getM_title());
            }});
    }
    public void ReverseByTitle()
    {
        SortByTitle();
        Collections.reverse(bookList);
    }
    public void ReverseByAuthor()
    {
        SortByAuthor();
        Collections.reverse(bookList);
    }
    public void SortByAuthor()
    {
        Collections.sort(bookList, new Comparator<BookViewForList>() {
            @Override
            public int compare(BookViewForList o1, BookViewForList o2) {
                return o1.getM_author().compareToIgnoreCase(o2.getM_author());
            }});
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SortByTitleAscAuthorAsc()
    {
        Comparator<BookViewForList> compar=Comparator.comparing(BookViewForList::getM_title)
                .thenComparing(BookViewForList::getM_author);
        bookList.sort(compar);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SortByTitleAscAuthorDesc()
    {
        Comparator<BookViewForList> compar=Comparator.comparing(BookViewForList::getM_title)
                .thenComparing(Comparator.comparing(BookViewForList::getM_author).reversed());
        bookList.sort(compar);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SortByTitleDescAuthorAsc()
    {
        Comparator<BookViewForList> compar=Comparator.comparing(BookViewForList::getM_title).reversed()
                .thenComparing(BookViewForList::getM_author);
        bookList.sort(compar);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SortByTitleDescAuthorDesc()
    {
        Comparator<BookViewForList> compar=Comparator.comparing(BookViewForList::getM_title).reversed()
                .thenComparing(Comparator.comparing(BookViewForList::getM_author).reversed());
        bookList.sort(compar);
    }
    @Override
    public Filter getFilter() {
        if(valueFilter==null){
            valueFilter=new ListFilter();
        }
        return valueFilter;
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

        TextView title = rowview.findViewById(R.id.newBookTitle);
        title.setText(currentBook.getM_title());

        TextView autor = rowview.findViewById(R.id.listAuthor);
        autor.setText(currentBook.getM_author());

        FloatingActionButton deletebut=rowview.findViewById(R.id.deleteFloatButt);
        deletebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager db=DBManager.getInstance();
                db.delete(currentBook.getM_Id());

            }
        });
        FloatingActionButton editbut=rowview.findViewById(R.id.editfloatbutt);
        editbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main=(MainActivity)context;
                FragmentManager manager=main.getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                EditBook eb=new EditBook();
                eb.setID(currentBook.getM_Id());
                transaction.add(R.id.fragmentLayout,eb).addToBackStack("1").commit();
            }
        });

        return rowview;
    }
    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result=new FilterResults();
            if(constraint!=null && constraint.length()>0)
            {
                ArrayList<BookViewForList> filtered=new ArrayList<>();
                for(int i=0;i<totalList.size();i++)
                {
                    if(totalList.get(i).getM_title().toUpperCase().
                            contains(constraint.toString().toUpperCase()) ||
                            totalList.get(i).getM_author().toUpperCase().
                            contains(constraint.toString().toUpperCase()) )
                    {
                        BookViewForList noua=new BookViewForList(
                                totalList.get(i).getM_Id(),
                                totalList.get(i).getM_title(),
                                totalList.get(i).getM_author());
                        filtered.add(noua);
                    }
                    result.count=filtered.size();
                    result.values=filtered;
                }
            }
            else
            {
                result.count=totalList.size();
                result.values=totalList;
            }
            return result;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bookList=(ArrayList<BookViewForList>) results.values;
            notifyDataSetChanged();
        }
    }
}
