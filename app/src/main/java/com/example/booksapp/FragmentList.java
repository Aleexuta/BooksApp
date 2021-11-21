package com.example.booksapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
public class FragmentList extends Fragment implements  View.OnClickListener{
    private MainActivity main;
    private ListView listview;
    private MyListAdapter myListAdapter;

    private DBManager dbManager;


    private Button m_addbutton;
    private Button m_refreshbutton;
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

        Button addbookbutton=(Button) rootview.findViewById(R.id.openNewBookFragButton);
        addbookbutton.setOnClickListener(this);
        m_addbutton=addbookbutton;

        m_refreshbutton=(Button) rootview.findViewById(R.id.refreshbutton);
        m_refreshbutton.setOnClickListener(this);

        return rootview;
    }

    private void loadListToView()
    {
        ArrayList<BookViewForList> booklist= dbManager.loadAllData(); //iei lista din  baza de date cu o functie;
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
        if(view.getId()==m_refreshbutton.getId())
        {
            loadListToView();
        }
    }

    public boolean onBackPressed()
    {
        return false;
    }
}