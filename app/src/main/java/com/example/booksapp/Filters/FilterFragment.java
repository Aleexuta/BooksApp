package com.example.booksapp.Filters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.booksapp.BookViewForList;
import com.example.booksapp.Books.BookType;
import com.example.booksapp.Books.Language;
import com.example.booksapp.DBManager;
import com.example.booksapp.DatabaseHelper;
import com.example.booksapp.Filters.FilterSecondPage;
import com.example.booksapp.FragmentList;
import com.example.booksapp.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class FilterFragment extends Fragment implements View.OnClickListener {


    private FloatingActionButton m_exit;
    private FloatingActionButton m_filter;

    private Chip m_read;
    private Chip m_owned;
    private Chip m_toread;
    private Chip m_tobuy;
    private Chip m_progress;
    boolean[] m_checkedvalue =new boolean[7];

    private ImageButton m_upgenre;
    private ImageButton m_uplang;
    private ImageButton m_downgenre;
    private ImageButton m_downlang;


    private RadioGroup m_langgroup;
    private RadioButton m_rom;
    private Spinner m_genregroup;

    private ImageButton m_goright;
    private Button m_reset;

    public FilterFragment() {
        // Required empty public constructor

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_filter, container, false);
        m_exit=rootview.findViewById(R.id.exitfilter);
        m_exit.setOnClickListener(this);

        m_filter=rootview.findViewById(R.id.checkfilter);
        m_filter.setOnClickListener(this);


        m_langgroup=rootview.findViewById(R.id.langauagegroup);
        m_langgroup.setVisibility(View.GONE);
        m_rom=rootview.findViewById(R.id.romanianradio);


        m_genregroup=rootview.findViewById(R.id.genrefiltre);
        m_genregroup.setVisibility(View.GONE);
        List<BookType> genre = new ArrayList<>(Arrays.asList(BookType.values()));
        ArrayAdapter<BookType> adaptergenre = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, genre);
        adaptergenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_genregroup.setAdapter(adaptergenre);

        m_read=rootview.findViewById(R.id.readchip);
        m_read.setOnClickListener(this);
        m_owned=rootview.findViewById(R.id.boughtchip);
        m_owned.setOnClickListener(this);
        m_toread=rootview.findViewById(R.id.toreadchip);
        m_toread.setOnClickListener(this);
        m_tobuy=rootview.findViewById(R.id.tobuychip);
        m_tobuy.setOnClickListener(this);
        m_progress=rootview.findViewById(R.id.progresschip);
        m_progress.setOnClickListener(this);

        for (boolean x: m_checkedvalue) {
            x=false;
        }

        m_upgenre=rootview.findViewById(R.id.goupgenre);
        m_upgenre.setOnClickListener(this);
        m_downgenre=rootview.findViewById(R.id.godowngenre);
        m_downgenre.setOnClickListener(this);
        if(!m_checkedvalue[6]) {
            m_upgenre.setVisibility(View.GONE);
            m_downgenre.setVisibility(View.VISIBLE);
            m_genregroup.setVisibility(View.GONE);
        }
        else
        {
            m_upgenre.setVisibility(View.VISIBLE);
            m_downgenre.setVisibility(View.GONE);
            m_genregroup.setVisibility(View.VISIBLE);
        }


        m_uplang=rootview.findViewById(R.id.gouplang);
        m_uplang.setOnClickListener(this);
        m_downlang=rootview.findViewById(R.id.godownlang);
        m_downlang.setOnClickListener(this);
        if(!m_checkedvalue[5]) {
            m_uplang.setVisibility(View.GONE);
            m_downlang.setVisibility(View.VISIBLE);
            m_langgroup.setVisibility(View.GONE);
        }
        else
        {
            m_uplang.setVisibility(View.VISIBLE);
            m_downlang.setVisibility(View.GONE);
            m_langgroup.setVisibility(View.VISIBLE);
        }

        m_goright=rootview.findViewById(R.id.rightarrow1);
        m_goright.setOnClickListener(this);

        m_reset=rootview.findViewById(R.id.resetfiltersbutton);
        m_reset.setOnClickListener(this);
        NeedSecondPage();
        return rootview;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v.getId()==m_exit.getId())
        {
            removePages();
        }
        if(v.getId()==m_filter.getId())
        {
            //filtreaza cu baza de date
            Filter filtre=Filter.getInstance();
            filterData();
            filtre.getSecond().filterData();
            filtre.getThrid().filterData();
            filtre.getFourth().filterData();
            Filter.Filter();
            removePages();
        }
        if(v.getId()==m_downgenre.getId())
        {
            m_genregroup.setVisibility(View.VISIBLE);
            m_downgenre.setVisibility(View.GONE);
            m_upgenre.setVisibility(View.VISIBLE);
            m_checkedvalue[6]=true;
        }
        if(v.getId()==m_upgenre.getId())
        {
            m_genregroup.setVisibility(View.GONE);
            m_downgenre.setVisibility(View.VISIBLE);
            m_upgenre.setVisibility(View.GONE);
            m_checkedvalue[6]=false;
        }
        if(v.getId()==m_downlang.getId())
        {
            m_langgroup.setVisibility(View.VISIBLE);
            m_uplang.setVisibility(View.VISIBLE);
            m_downlang.setVisibility(View.GONE);
            m_checkedvalue[5]=true;
        }
        if(v.getId()==m_uplang.getId())
        {
            m_langgroup.setVisibility(View.GONE);
            m_uplang.setVisibility(View.GONE);
            m_downlang.setVisibility(View.VISIBLE);
            m_checkedvalue[5]=false;
        }
        if(v.getId()==m_progress.getId())
        {
            m_checkedvalue[3]=!m_checkedvalue[3];
            NeedSecondPage();
        }
        if(v.getId()==m_read.getId())
        {
            m_checkedvalue[4]=!m_checkedvalue[4];
            NeedSecondPage();
        }
        if(v.getId()==m_owned.getId())
        {
            m_checkedvalue[2]=!m_checkedvalue[2];
            NeedSecondPage();
        }
        if(v.getId()==m_toread.getId())
        {
            m_checkedvalue[0]=!m_checkedvalue[0];
        }
        if(v.getId()==m_tobuy.getId())
        {
           m_checkedvalue[1]=!m_checkedvalue[1];
        }


        if(v.getId()==m_goright.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            Filter filtre=Filter.getInstance();
            if(m_checkedvalue[4])//read, trimit progress si owned
            {
                transaction.add(R.id.filterframelayout,filtre.getSecond()).addToBackStack("2ndPage").commit();
                filtre.getSecond().setWhatIsVisible(m_checkedvalue[2],m_checkedvalue[3]);
            }
            else if(m_checkedvalue[2])//owned trimit progress si read
            {
                transaction.add(R.id.filterframelayout, filtre.getThrid()).addToBackStack("3rdPage").commit();
                filtre.getThrid().setWhatIsVisible(m_checkedvalue[3],m_checkedvalue[4]);
            }
            else if (m_checkedvalue[3])//progress trimit read si progress
            {
                transaction.add(R.id.filterframelayout, filtre.getFourth()).addToBackStack("4thPage").commit();
                filtre.getFourth().setWhatIsVisible(m_checkedvalue[4],m_checkedvalue[3]);
            }
        }
        if(v.getId()==m_reset.getId())
        {
            FragmentList fr=(FragmentList) requireActivity().getSupportFragmentManager().findFragmentByTag("LISTA");
            assert fr != null;
            fr.loadListToView();
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            Filter.resetFilters();
        }

    }



    String selection = "";
    ArrayList<String> selectionArgs = new ArrayList<>();


    private void filterData()
    {
        selection="";
        selectionArgs.removeAll(selectionArgs);
        if(m_toread.isChecked()) {
            selection+= DatabaseHelper._Toread+" =? and ";
            selectionArgs.add("1");
        }
        if(m_tobuy.isChecked()) {
            selection += DatabaseHelper._Tobuy + " =? and ";
            selectionArgs.add("1");
        }
        if(m_read.isChecked()) {
            selection += DatabaseHelper._Read + " =? and ";
            selectionArgs.add("1");
        }
        if(m_owned.isChecked()) {
            selection += DatabaseHelper._Owned + " =? and ";
            selectionArgs.add("1");
        }
        if(m_progress.isChecked()) {
            selection += DatabaseHelper._Progress + " =? and ";
            selectionArgs.add("1");
        }


        if(m_checkedvalue[5])//daca dorim sa filtram cu limba
        {
            String lang;
            if(m_rom.isChecked())
                lang= Language.Romanian.toString();
            else
                lang=Language.English.toString();
            selection+=DatabaseHelper._Language+" =? and ";
            selectionArgs.add(lang);
        }
        if(m_checkedvalue[6])
        {
            selection+=DatabaseHelper._Genre+" =? and ";
            selectionArgs.add(m_genregroup.getSelectedItem().toString());
        }
        Filter.addFilters(selection,selectionArgs);
        selection="";
        selectionArgs.removeAll(selectionArgs);
    }

    private void NeedSecondPage()
    {
        if(m_checkedvalue[2] || m_checkedvalue[3] || m_checkedvalue[4])
        {
            m_goright.setVisibility(View.VISIBLE);
        }
        else
        {
            m_goright.setVisibility(View.GONE);
        }
    }
    private void removePages()
    {
        FragmentManager manager= requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        Fragment frag=manager.findFragmentByTag("LISTA");
        for (Fragment fragment : manager.getFragments()) {
            if(fragment!=frag)
               transaction.remove(fragment);
        }
        transaction.remove(this).commit();
    }
}