package com.example.booksapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewBook extends  Fragment implements View.OnClickListener {
    public NewBook() {
        // Required empty public constructor
    }

    //salvez idurile de la acestea pt a le apela mai tarziu;
    EditText m_autor;
    EditText m_titlu;
    Spinner m_genre;
    Spinner m_language;
    RatingBar m_ratbar;
    Spinner m_coverbooktypespinner;
    Spinner m_readfromspinner;
    EditText m_publicatie;
    EditText m_year;
    EditText m_obs;
    CheckBox m_toread;
    CheckBox m_tobuy;
    CheckBox m_read;
    CheckBox m_owned;
    Button m_add;
    Button m_close;
    EditText m_readdate;
    EditText m_boughtdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_book, container, false);

        Spinner spinner = v.findViewById(R.id.spinnergenre);
        List<BookType> genre = new ArrayList<BookType>();
        for (BookType b : BookType.values()) {
            genre.add(b);
        }
        ArrayAdapter<BookType> adaptergenre = new ArrayAdapter<BookType>(this.getActivity(), android.R.layout.simple_spinner_item, genre);
        adaptergenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptergenre);
        spinner.setVisibility(View.VISIBLE);
        spinner.setSelection(0);
        this.m_genre = spinner;

        Spinner spinner2 = v.findViewById(R.id.readedfromspinner);
        List<Read.ReadFrom> readfrom = new ArrayList<Read.ReadFrom>();
        for (Read.ReadFrom b : Read.ReadFrom.values()) {
            readfrom.add(b);
        }
        ArrayAdapter<Read.ReadFrom> adapterread = new ArrayAdapter<Read.ReadFrom>(this.getActivity(), android.R.layout.simple_spinner_item, readfrom);
        adapterread.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterread);
        spinner2.setVisibility(View.GONE);
        spinner2.setSelection(0);
        this.m_readfromspinner = spinner2;

        Spinner spinner3 = v.findViewById(R.id.coverbooktypespinner);
        List<Owned.CoverType> ownedtype = new ArrayList<Owned.CoverType>();
        for (Owned.CoverType b : Owned.CoverType.values()) {
            ownedtype.add(b);
        }
        ArrayAdapter<Owned.CoverType> adapterown = new ArrayAdapter<Owned.CoverType>(this.getActivity(), android.R.layout.simple_spinner_item, ownedtype);
        adapterown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterown);
        spinner3.setVisibility(View.GONE);
        spinner3.setSelection(0);
        this.m_coverbooktypespinner = spinner3;

        Spinner spinner4 = v.findViewById(R.id.languagespinner);
        List<Book.Language> language = new ArrayList<>();
        for (Book.Language b : Book.Language.values()) {
            language.add(b);
        }
        ArrayAdapter<Book.Language> adapterlang = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, language);
        adapterlang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapterlang);
        spinner4.setVisibility(View.VISIBLE);
        spinner4.setSelection(0);
        this.m_language = spinner4;


        RatingBar rat =v.findViewById(R.id.ratingBar);
        rat.setVisibility(View.GONE);
        this.m_ratbar = rat;

        this.m_titlu = v.findViewById(R.id.listTitle);
        this.m_autor = v.findViewById(R.id.newbookauthor);
        this.m_publicatie = v.findViewById(R.id.publisher);
        this.m_year = v.findViewById(R.id.year);
        this.m_obs = v.findViewById(R.id.Observatii);
        this.m_boughtdate=v.findViewById(R.id.boughtdate);
        this.m_readdate=v.findViewById(R.id.readeddate);

        m_publicatie.setVisibility(View.GONE);
        m_year.setVisibility(View.GONE);
        m_readdate.setVisibility(View.GONE);
        m_boughtdate.setVisibility(View.GONE);

        CheckBox cb = v.findViewById(R.id.owncheck);
        cb.setOnClickListener(this);
        this.m_owned = cb;
        CheckBox cb2 = v.findViewById(R.id.readcheck);
        cb2.setOnClickListener(this);
        this.m_read= cb2;
        CheckBox cb3 = v.findViewById(R.id.tobuycheck);
        cb3.setOnClickListener(this);
        this.m_tobuy = cb3;
        CheckBox cb4 = v.findViewById(R.id.toreadcheck);
        cb4.setOnClickListener(this);
        this.m_toread = cb4;



        Button addbut=v.findViewById(R.id.okbutton);
        m_add=addbut;
        addbut.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        int readid = m_read.getId();
        int ownid = m_owned.getId();
        int idv = v.getId();
        if (idv == readid) {

            if (((CheckBox) v).isChecked()) {
                m_ratbar.setVisibility(View.VISIBLE);
                m_readfromspinner.setVisibility(View.VISIBLE);
                m_readdate.setVisibility(View.VISIBLE);
            } else {
                m_ratbar.setVisibility((View.GONE));
                m_readfromspinner.setVisibility(View.GONE);
                m_readdate.setVisibility(View.GONE);
            }
            return;
        }
        if (idv == ownid) {
            if (((CheckBox) v).isChecked()) {
                m_coverbooktypespinner.setVisibility(View.VISIBLE);
                m_publicatie.setVisibility(View.VISIBLE);
                m_year.setVisibility(View.VISIBLE);
                m_boughtdate.setVisibility(View.VISIBLE);
            } else {
                m_coverbooktypespinner.setVisibility(View.GONE);
                m_publicatie.setVisibility(View.GONE);
                m_year.setVisibility(View.GONE);
                m_boughtdate.setVisibility(View.GONE);
            }
            return;
        }
        int addid=m_add.getId();
        if (idv == addid) {
            IBook newbook;
            //in functie de ce am biffat creez un anumit tip de carte;

            String titlu = m_titlu.getText().toString();
            String autor = m_autor.getText().toString();
            BookType gen = (BookType) m_genre.getSelectedItem();
            Book.Language lang = (Book.Language) m_language.getSelectedItem();
            String obs = m_obs.getText().toString();
            boolean toread = m_toread.isChecked();
            boolean tobuy = m_tobuy.isChecked();
            if (m_owned.isChecked()) {
                Owned.CoverType ot = (Owned.CoverType) m_coverbooktypespinner.getSelectedItem();
                String pub = m_publicatie.toString();
                String year = m_year.toString();
                //ia data de cumparare
                Date db=new Date();
                if (m_read.isChecked()) {
                    int rat = m_ratbar.getNumStars();
                    //ia data de citire
                    Date dr=new Date();
                    Read.ReadFrom readFrom=(Read.ReadFrom) m_readfromspinner.getSelectedItem();

                    newbook = new ReadOwned(toread, tobuy, titlu, autor, gen, obs, lang,pub, year, ot, rat,readFrom,dr,db);
                } else {
                    newbook = new Owned(toread, tobuy, titlu, autor, gen, obs,lang, pub, year, ot,db);
                }
            } else if (m_read.isChecked()) {
                int rat = m_ratbar.getNumStars();
                //ia data de citire
                Date dr=new Date();
                Read.
ReadFrom rf = (Read.
ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = new Read(toread, tobuy, titlu, autor, gen, obs,lang, rat, rf,dr);
            } else//niciuna nu e bifata
            {
                newbook = new Book(toread, tobuy, titlu, autor, gen, obs,lang);
            }
            //trimitem inapoi noua carte in baza de date
            DBManager db= DBManager.getInstance();
            if(db!=null)
                db.insert(newbook);

            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

        }


    }


    public boolean onBackPressed() { return false; }

}

