package com.example.booksapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booksapp.Books.NewBook;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton m_exit;

    private Button m_add;
    private Button m_removefilters;
    private Button m_search;
    private Button m_filter;
    private Button m_sort;

    public MenuFragment() {
        // Required empty public constructor
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
        }
        if(v.getId()==m_search.getId())
        {
            FragmentList fr=(FragmentList) requireActivity().getSupportFragmentManager().findFragmentByTag("LISTA");
            assert fr != null;
            fr.SearchFunction();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    public boolean onBackPressed()
    {
        return false;
    }
}