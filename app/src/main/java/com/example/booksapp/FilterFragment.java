package com.example.booksapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FilterFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton m_exit;


    public FilterFragment() {
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
        View rootview= inflater.inflate(R.layout.fragment_filter, container, false);
        m_exit=rootview.findViewById(R.id.exitsort);
        m_exit.setOnClickListener(this);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==m_exit.getId())
        {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }


}