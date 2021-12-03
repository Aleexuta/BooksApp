

package com.example.booksapp;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.booksapp.Books.EditBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MyListAdapter extends BaseAdapter implements Filterable {

    private final Activity context;
    private ArrayList<BookViewForList> bookList;
    private ArrayList<BookViewForList> totalList;
    private ListFilter valueFilter;
    //private IBook m_book;
    public MyListAdapter(Activity context, ArrayList<BookViewForList> list)
    {

        this.context=context;
        this.bookList=list;
        this.totalList=list;
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
