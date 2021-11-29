package com.example.booksapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.booksapp.Books.EditBook;
import com.example.booksapp.Books.NewBook;
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

        listview=(ListView) rootview.findViewById(R.id.listlayout);
        loadListToView();



        FloatingActionButton addbookbutton=(FloatingActionButton) rootview.findViewById(R.id.newbook);
        addbookbutton.setOnClickListener(this);
        m_addbutton=addbookbutton;


        m_menubutton=rootview.findViewById(R.id.menu);
        m_menubutton.setOnClickListener(this);

        m_sortbutton=rootview.findViewById(R.id.sortFiltre);
        m_sortbutton.setOnClickListener(this);
        return rootview;
    }

    public void loadListToView()
    {
        booklist= dbManager.loadAllData(); //iei lista din  baza de date cu o functie;
        myListAdapter=new MyListAdapter(getActivity(),booklist);
        listview.setAdapter(myListAdapter);
    }
    @Override
    public void onClick(View view) {
        //manager.executePendingTransactions();
        //transaction.replace(R.id.fragmentLayout,new NewBookFragment()).commit();
        if(view.getId()==m_addbutton.getId())
        {
            FragmentManager manager=getActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, new NewBook()).addToBackStack("").commit();
        }
        if(view.getId()==m_menubutton.getId())
        {
            FragmentManager manager=getActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, new MenuFragment()).addToBackStack("MENU").commit();
        }
        if(view.getId()==m_sortbutton.getId())
        {
            FragmentManager manager=getActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, new SortFilterFragment()).addToBackStack("SORT").commit();
        }
    }

    public boolean onBackPressed()
    {
        return false;
    }
}