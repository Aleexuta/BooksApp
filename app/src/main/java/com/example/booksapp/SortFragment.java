package com.example.booksapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class SortFragment extends Fragment implements View.OnClickListener{
    private ImageButton m_uptitle;
    private ImageButton m_downtitle;
    private RadioGroup m_titlegroup;
    private RadioButton m_asctitle;
    private RadioButton m_desctitle;

    private ImageButton m_upauthor;
    private ImageButton m_downauthor;
    private RadioGroup m_authorgroup;
    private RadioButton m_ascauthor;
    private RadioButton m_descauthor;

    private FloatingActionButton m_exit;
    private FloatingActionButton m_sort;

    private boolean[] m_checkedboolean=new boolean[2];
    public SortFragment()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_sort, container, false);

        for (boolean x: m_checkedboolean) {
            x=false;
        }

        m_exit=rootview.findViewById(R.id.exitsort);
        m_exit.setOnClickListener(this);
        m_sort=rootview.findViewById(R.id.checksort);
        m_sort.setOnClickListener(this);

        m_uptitle=rootview.findViewById(R.id.gouptitle);
        m_uptitle.setOnClickListener(this);
        m_downtitle=rootview.findViewById(R.id.godowntitle);
        m_downtitle.setOnClickListener(this);
        m_titlegroup=rootview.findViewById(R.id.titleradiogroup);
        m_asctitle=rootview.findViewById(R.id.ascTitle);
        m_desctitle=rootview.findViewById(R.id.descTitle);

        m_upauthor=rootview.findViewById(R.id.goupauthor);
        m_upauthor.setOnClickListener(this);
        m_downauthor=rootview.findViewById(R.id.godowauthor);
        m_downauthor.setOnClickListener(this);
        m_authorgroup=rootview.findViewById(R.id.authorradiogroup);
        m_ascauthor=rootview.findViewById(R.id.ascAuthor);
        m_descauthor=rootview.findViewById(R.id.descAuthor);
        setVisible();

        return rootview;
    }
    private void setVisible()
    {
        if(m_checkedboolean[0])
        {
            m_downtitle.setVisibility(View.GONE);
            m_uptitle.setVisibility(View.VISIBLE);
            m_titlegroup.setVisibility(View.VISIBLE);
        }
        else
        {
            m_downtitle.setVisibility(View.VISIBLE);
            m_uptitle.setVisibility(View.GONE);
            m_titlegroup.setVisibility(View.GONE);
        }
        if(m_checkedboolean[1])
        {
            m_downauthor.setVisibility(View.GONE);
            m_upauthor.setVisibility(View.VISIBLE);
            m_authorgroup.setVisibility(View.VISIBLE);
        }
        else
        {
            m_downauthor.setVisibility(View.VISIBLE);
            m_upauthor.setVisibility(View.GONE);
            m_authorgroup.setVisibility(View.GONE);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        if(v.getId()==m_downauthor.getId())
        {
            m_checkedboolean[1]=true;
            setVisible();
        }
        if(v.getId()==m_upauthor.getId())
        {
            m_checkedboolean[1]=false;
            setVisible();
        }
        if(v.getId()==m_downtitle.getId())
        {
            m_checkedboolean[0]=true;
            setVisible();
        }
        if(v.getId()==m_uptitle.getId())
        {
            m_checkedboolean[0]=false;
            setVisible();
        }
        if(v.getId()==m_exit.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.remove(this).commit();
        }
        if(v.getId()==m_sort.getId())
        {
            FragmentList fr=(FragmentList) requireActivity().getSupportFragmentManager().findFragmentByTag("LISTA");
            assert fr != null;
            if(m_checkedboolean[0] && m_checkedboolean[1])
            {
                if(m_asctitle.isChecked() && m_ascauthor.isChecked())
                    fr.SortList("BothAscAsc");
                if(m_asctitle.isChecked() && m_descauthor.isChecked())
                    fr.SortList("BothAscDesc");
                if(m_desctitle.isChecked()&&m_ascauthor.isChecked())
                    fr.SortList("BothDescAsc");
                if(m_desctitle.isChecked() && m_descauthor.isChecked())
                    fr.SortList("BothDescDesc");
            }
            else if(m_checkedboolean[0])
            {
                if(m_asctitle.isChecked())
                    fr.SortList("TitleAsc");
                if(m_desctitle.isChecked())
                    fr.SortList("TitleDesc");
            }
            else if(m_checkedboolean[1])
            {
                if(m_ascauthor.isChecked())
                    fr.SortList("AuthorAsc");
                if(m_descauthor.isChecked())
                    fr.SortList("AuthorDesc");
            }
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.remove(this).commit();
        }
    }
}