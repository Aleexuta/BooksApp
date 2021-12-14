package com.example.booksapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booksapp.Books.NewBook;
import com.example.booksapp.Filters.Filter;
import com.example.booksapp.Filters.FilterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class MenuFragment extends Fragment implements View.OnClickListener {

    Intent m_chosefileintent;


    private FloatingActionButton m_exit;

    private Button m_add;
    private Button m_removefilters;
    private Button m_search;
    private Button m_filter;
    private Button m_sort;
    private Button m_statistics;

    FilterFragment filterFragment;
    SortFragment sortFragment;

    private Button m_load;
    private Button m_save;
    public MenuFragment() {
        // Required empty public constructor
    }

    public MenuFragment(FilterFragment fr,SortFragment sr)
    {
        filterFragment=fr;
        sortFragment=sr;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_menu, container, false);

        m_exit=rootview.findViewById(R.id.exitmenu);

        m_exit.setOnClickListener(this);

        m_add=rootview.findViewById(R.id.addbookmenu);
        m_add.setOnClickListener(this);

        m_removefilters=rootview.findViewById(R.id.removefiltersmenu);
        m_removefilters.setOnClickListener(this);

        m_search=rootview.findViewById(R.id.searchbuttonmenu);
        m_search.setOnClickListener(this);

        m_filter=rootview.findViewById(R.id.filtrebuttonmenu);
        m_filter.setOnClickListener(this);

        m_sort=rootview.findViewById(R.id.sortbuttonmenu);
        m_sort.setOnClickListener(this);

        m_load=rootview.findViewById(R.id.loadList);
        m_load.setOnClickListener(this);

        m_save=rootview.findViewById(R.id.saveList);
        m_save.setOnClickListener(this);

        m_statistics=rootview.findViewById(R.id.statistics);
        m_statistics.setOnClickListener(this);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==m_exit.getId())
        {
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
        if(v.getId()==m_add.getId())
        {
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout,new NewBook()).addToBackStack("ADD").commit();
        }
        if(v.getId()==m_removefilters.getId())
        {
            FragmentList fr=(FragmentList) requireActivity().getSupportFragmentManager().findFragmentByTag("LISTA");
            assert fr != null;
            fr.loadListToView();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            Filter.resetFilters();
        }
        if(v.getId()==m_search.getId())
        {
            FragmentList fr=(FragmentList) requireActivity().getSupportFragmentManager().findFragmentByTag("LISTA");
            assert fr != null;
            fr.SearchFunction();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
        if(v.getId()==m_filter.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, filterFragment).addToBackStack("FILTER").commit();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
        if(v.getId()==m_sort.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, sortFragment).addToBackStack("SORT").commit();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
        if(v.getId()==m_load.getId())
        {
            String [] mimeTypes={ "application/vnd.ms-excel",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
            m_chosefileintent=new Intent(Intent.ACTION_GET_CONTENT);
            m_chosefileintent.setType("text/csv");
            //m_chosefileintent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            m_chosefileintent.addCategory(Intent.CATEGORY_OPENABLE);
            requireActivity().startActivityForResult(m_chosefileintent,10);


        }
        if(v.getId()==m_save.getId())
        {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/csv");
            intent.putExtra(Intent.EXTRA_TITLE,"backupBooks.xlsx");
            requireActivity().startActivityForResult(intent,20);
        }
        if(v.getId()==m_statistics.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=   manager.beginTransaction();
            transaction.add(R.id.fragmentLayout, new StatisticsFragment()).addToBackStack("STATISTICS").commit();
        }
    }




    public boolean onBackPressed()
    {
        return false;
    }
}