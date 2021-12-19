package com.example.booksapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.booksapp.Books.NewBook;
import com.example.booksapp.DB.DBManager;
import com.example.booksapp.Filters.Filter;
import com.example.booksapp.Filters.FilterFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentList extends Fragment implements  View.OnClickListener{

    private MainActivity main;
    private ListView listview;
    private MyListAdapter myListAdapter;
    ArrayList<BookViewForList> booklist;
    private DBManager dbManager;


    private FloatingActionButton m_addbutton;
    private FloatingActionButton m_sortbutton;
    private FloatingActionButton m_menubutton;
    private FloatingActionButton m_filtrebutton;
    private FloatingActionButton m_searchbutton;
    private ExtendedFloatingActionButton m_morebutton;
    private ExtendedFloatingActionButton m_lessbutton;

    private SearchView m_searchbox;
    private TextView m_searchtext;
    private TextView m_filtertext;
    private TextView m_sorttext;

    private TextView m_emptylist;
    private boolean m_allFabs=false;
    private boolean m_showssearch=false;

    FilterFragment m_filtrefragment;
    SortFragment m_sortfragment;

    public FragmentList() {
        // Required empty public constructor
    }


    public FragmentList(MainActivity m)
    {
        super();
        main=m;
    }
    public static FragmentList newInstance(MainActivity m) {
        FragmentList fragment = new FragmentList(m);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_list, container, false);

        dbManager=DBManager.getInstance();
        assert dbManager != null;
        if(dbManager.open(main)==null)
            getExitTransition();

       // Cursor cursor=dbManager.fetch();
        m_emptylist=rootview.findViewById(R.id.emptylisttext);

        listview=(ListView) rootview.findViewById(R.id.listlayout);
        loadListToView();
        listview.setPadding(0,16,0,0);
        listview.smoothScrollToPosition(0);


        FloatingActionButton addbookbutton=(FloatingActionButton) rootview.findViewById(R.id.newbook);
        addbookbutton.setOnClickListener(this);
        m_addbutton=addbookbutton;


        m_menubutton=rootview.findViewById(R.id.menu);
        m_menubutton.setOnClickListener(this);

        m_morebutton=rootview.findViewById(R.id.expands);
        m_morebutton.setOnClickListener(this);
        m_morebutton.shrink();

        m_lessbutton=rootview.findViewById(R.id.closeall);
        m_lessbutton.setOnClickListener(this);
        m_lessbutton.hide();
        m_lessbutton.shrink();

        m_sortbutton=rootview.findViewById(R.id.sort);
        m_sortbutton.setOnClickListener(this);
        m_sortbutton.hide();

        m_filtrebutton=rootview.findViewById(R.id.filtre);
        m_filtrebutton.setOnClickListener(this);
        m_filtrebutton.hide();

        m_searchbutton=rootview.findViewById(R.id.search);
        m_searchbutton.setOnClickListener(this);
        m_searchbutton.hide();


        m_filtertext=rootview.findViewById(R.id.filtertext);
        m_filtertext.setVisibility(View.GONE);
        m_sorttext=rootview.findViewById(R.id.sorttext);
        m_sorttext.setVisibility(View.GONE);
        m_searchtext=rootview.findViewById(R.id.searchtext);
        m_searchtext.setVisibility(View.GONE);



        m_searchbox=rootview.findViewById(R.id.searchbox);
        m_searchbox.setVisibility(View.GONE);
        m_searchbox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myListAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myListAdapter.getFilter().filter(newText);
                return true;
            }
        });


        return rootview;
    }
    public void loadNewListToView(ArrayList<BookViewForList> arr)
    {
        booklist=arr;
        myListAdapter=new MyListAdapter(getActivity(),booklist);
        listview.setAdapter(myListAdapter);
    }
    public void loadListToView()
    {
        booklist= dbManager.loadAllData(); //iei lista din  baza de date cu o functie;
        if(booklist.size()>0)
            m_emptylist.setVisibility(View.GONE);
        else
            m_emptylist.setVisibility(View.VISIBLE);
        myListAdapter=new MyListAdapter(getActivity(),booklist);
        listview.setAdapter(myListAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SortList(String field)
    {
        if(field.equals("TitleAsc"))
        {
            myListAdapter.SortByTitle();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("AuthorAsc"))
        {
            myListAdapter.SortByAuthor();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("BothAscAsc"))
        {
            myListAdapter.SortByTitleAscAuthorAsc();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("TitleDesc"))
        {
            myListAdapter.ReverseByTitle();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("AuthorDesc"))
        {
            myListAdapter.ReverseByAuthor();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("BothAscDesc"))
        {
            myListAdapter.SortByTitleAscAuthorDesc();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("BothDescDesc"))
        {
            myListAdapter.SortByTitleDescAuthorDesc();
            listview.setAdapter(myListAdapter);
        }
        if(field.equals("BothDescAsc"))
        {
            myListAdapter.SortByTitleDescAuthorAsc();
            listview.setAdapter(myListAdapter);
        }
    }


    @Override
    public void onClick(View view) {
        //manager.executePendingTransactions();
        //transaction.replace(R.id.fragmentLayout,new NewBookFragment()).commit();
        m_filtrefragment= Filter.getInstance().getFirst();
        m_sortfragment=Filter.getInstance().getSort();
        if(view.getId()==m_addbutton.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, new NewBook()).addToBackStack("ADD").commit();
        }
        if(view.getId()==m_menubutton.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, new MenuFragment(m_filtrefragment,m_sortfragment)).addToBackStack("MENU").commit();
        }
        if(view.getId()==m_morebutton.getId())
        {
            if(!m_allFabs)
            {
                m_sortbutton.show();
                m_filtrebutton.show();
                m_searchbutton.show();

                m_morebutton.extend();
                m_lessbutton.show();

                m_sorttext.setVisibility(View.VISIBLE);
                m_filtertext.setVisibility(View.VISIBLE);
                m_searchtext.setVisibility(View.VISIBLE);
                m_allFabs=true;
            }
        }
        if(view.getId()==m_lessbutton.getId())
        {
            if(m_allFabs)
            {
                m_sortbutton.hide();
                m_filtrebutton.hide();
                m_searchbutton.hide();

                m_lessbutton.shrink();
                m_morebutton.shrink();
                m_lessbutton.hide();


                listview.setPadding(0,16,0,0);
                listview.smoothScrollToPosition(0);

                m_searchbox.setVisibility(View.GONE);
                m_sorttext.setVisibility(View.GONE);
                m_filtertext.setVisibility(View.GONE);
                m_searchtext.setVisibility(View.GONE);
                m_allFabs = false;
            }
        }
        if(view.getId()==m_filtrebutton.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, m_filtrefragment).addToBackStack("FILTER").commit();
        }
        if(view.getId()==m_sortbutton.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, m_sortfragment).addToBackStack("SORT").commit();
        }
        if(view.getId()==m_searchbutton.getId())
        {
            SearchFunction();
        }

    }

    public void SearchFunction()
    {
        if(!m_showssearch) {
            m_searchbox.setVisibility(View.VISIBLE);
            listview.setPadding(0, 120, 0, 0);
            listview.smoothScrollToPosition(0);
            m_showssearch=true;
        }
        else
        {
            m_searchbox.setVisibility(View.GONE);
            listview.setPadding(0,16,0,0);
            listview.smoothScrollToPosition(0);
            m_showssearch=false;
        }
    }

    public boolean onBackPressed()
    {
        return false;
    }
}