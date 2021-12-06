package com.example.booksapp.Books;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.booksapp.DBManager;
import com.example.booksapp.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewBook extends Fragment implements View.OnClickListener {
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
    CheckBox m_progress;
    Button m_add;
    Button m_close;
    EditText m_readdate;
    EditText m_boughtdate;
    EditText m_totalpages;
    EditText m_acutalpage;

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
        List<BookType> genre = new ArrayList<BookType>(Arrays.asList(BookType.values()));
        ArrayAdapter<BookType> adaptergenre = new ArrayAdapter<BookType>(this.getActivity(), android.R.layout.simple_spinner_item, genre);
        adaptergenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptergenre);
        spinner.setVisibility(View.VISIBLE);
        spinner.setSelection(0);
        this.m_genre = spinner;

        Spinner spinner2 = v.findViewById(R.id.readedfromspinner);
        List<ReadFrom> readfrom = new ArrayList<ReadFrom>(Arrays.asList(ReadFrom.values()));
        ArrayAdapter<ReadFrom> adapterread = new ArrayAdapter<ReadFrom>(this.getActivity(), android.R.layout.simple_spinner_item, readfrom);
        adapterread.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterread);
        spinner2.setVisibility(View.GONE);
        spinner2.setSelection(0);
        this.m_readfromspinner = spinner2;

        Spinner spinner3 = v.findViewById(R.id.coverbooktypespinner);
        List<CoverType> ownedtype = new ArrayList<CoverType>(Arrays.asList(CoverType.values()));
        ArrayAdapter<CoverType> adapterown = new ArrayAdapter<CoverType>(this.getActivity(), android.R.layout.simple_spinner_item, ownedtype);
        adapterown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterown);
        spinner3.setVisibility(View.GONE);
        spinner3.setSelection(0);
        this.m_coverbooktypespinner = spinner3;

        Spinner spinner4 = v.findViewById(R.id.languagespinner);
        List<Language> language = new ArrayList<>(Arrays.asList(Language.values()));
        ArrayAdapter<Language> adapterlang = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, language);
        adapterlang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapterlang);
        spinner4.setVisibility(View.VISIBLE);
        spinner4.setSelection(0);
        this.m_language = spinner4;


        RatingBar rat = v.findViewById(R.id.ratingBar);
        rat.setVisibility(View.GONE);
        this.m_ratbar = rat;

        this.m_titlu = v.findViewById(R.id.newBookTitle);
        this.m_autor = v.findViewById(R.id.newBookauthor);
        this.m_publicatie = v.findViewById(R.id.publisher);
        this.m_year = v.findViewById(R.id.year);
        this.m_obs = v.findViewById(R.id.Observatii);
        this.m_boughtdate = v.findViewById(R.id.boughtdate);
        this.m_readdate = v.findViewById(R.id.readeddate);
        this.m_totalpages = v.findViewById(R.id.tottalpages);
        this.m_acutalpage = v.findViewById(R.id.actualpages);

        m_publicatie.setVisibility(View.GONE);
        m_year.setVisibility(View.GONE);
        m_readdate.setVisibility(View.GONE);
        m_boughtdate.setVisibility(View.GONE);
        m_totalpages.setVisibility(View.GONE);
        m_acutalpage.setVisibility(View.GONE);

        m_readdate.setOnClickListener(this);
        m_readdate.setInputType(InputType.TYPE_NULL);
        m_boughtdate.setOnClickListener(this);
        m_boughtdate.setInputType(InputType.TYPE_NULL);

        CheckBox cb = v.findViewById(R.id.owncheck);
        cb.setOnClickListener(this);
        this.m_owned = cb;
        CheckBox cb2 = v.findViewById(R.id.readcheck);
        cb2.setOnClickListener(this);
        this.m_read = cb2;
        CheckBox cb3 = v.findViewById(R.id.tobuycheck);
        cb3.setOnClickListener(this);
        this.m_tobuy = cb3;
        CheckBox cb4 = v.findViewById(R.id.toreadcheck);
        cb4.setOnClickListener(this);
        this.m_toread = cb4;
        CheckBox cb5 = v.findViewById(R.id.progresscheck);
        cb5.setOnClickListener(this);
        this.m_progress = cb5;


        Button addbut = v.findViewById(R.id.okbutton);
        m_add = addbut;
        addbut.setOnClickListener(this);
        m_close=v.findViewById(R.id.cancelbutton);
        m_close.setOnClickListener(this);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int readid = m_read.getId();
        int ownid = m_owned.getId();
        int progressid = m_progress.getId();
        int idv = v.getId();
        if (idv == readid) {

            if (((CheckBox) v).isChecked()) {
                m_ratbar.setVisibility(View.VISIBLE);
                m_readfromspinner.setVisibility(View.VISIBLE);
                m_readdate.setVisibility(View.VISIBLE);
                m_totalpages.setVisibility(View.VISIBLE);
            } else {
                m_ratbar.setVisibility((View.GONE));
                m_readfromspinner.setVisibility(View.GONE);
                m_readdate.setVisibility(View.GONE);
                m_totalpages.setVisibility(View.GONE);
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
        if (idv == progressid) {
            if (((CheckBox) v).isChecked()) {
                m_totalpages.setVisibility(View.VISIBLE);
                m_acutalpage.setVisibility(View.VISIBLE);
            } else {
                m_totalpages.setVisibility(View.GONE);
                m_acutalpage.setVisibility(View.GONE);
            }
            return;
        }
        int addid = m_add.getId();
        if (idv == addid) {
            getBook();
            return;
        }
        int clid=m_close.getId();
        if(idv==clid)
        {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            return;
        }

        if(idv==m_readdate.getId())
        {
            final Calendar clrd=Calendar.getInstance();
            int day=clrd.get(Calendar.DAY_OF_MONTH);
            int month=clrd.get(Calendar.MONTH);
            int year=clrd.get(Calendar.YEAR);

            DatePickerDialog picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    m_readdate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },year,month,day);
            picker.show();
        }

        if(idv==m_boughtdate.getId())
        {
            final Calendar clrd=Calendar.getInstance();
            int day=clrd.get(Calendar.DAY_OF_MONTH);
            int month=clrd.get(Calendar.MONTH);
            int year=clrd.get(Calendar.YEAR);

            DatePickerDialog picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    m_boughtdate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },year,month,day);
            picker.show();
        }
    }


    public boolean onBackPressed() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    private void getBook() {
        IBook newbook;
        //in functie de ce am biffat creez un anumit tip de carte;

        String titlu = m_titlu.getText().toString();
        String autor = m_autor.getText().toString();
        BookType gen = (BookType) m_genre.getSelectedItem();
        Language lang = (Language) m_language.getSelectedItem();
        String obs = m_obs.getText().toString();
        boolean toread = m_toread.isChecked();
        boolean tobuy = m_tobuy.isChecked();

        boolean owned = m_owned.isChecked();
        boolean read = m_read.isChecked();
        boolean progress = m_progress.isChecked();
        Date readeddate=null;
        if (read) {

            try {
                android.icu.text.SimpleDateFormat formatter= new android.icu.text.SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                readeddate = formatter.parse(m_readdate.getText().toString());

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (owned) {
            CoverType ct = (CoverType) m_coverbooktypespinner.getSelectedItem();
            Date purchdate =null;
            try {
                android.icu.text.SimpleDateFormat formatter= new android.icu.text.SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                purchdate = formatter.parse(m_boughtdate.getText().toString());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            String pub=m_publicatie.getText().toString();
            String year=m_year.getText().toString();

            if (progress && read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                assert purchdate != null;
                assert readeddate != null;
                newbook = Book.getOwnedProgressReadBook(titlu, autor, gen, obs, lang, toread, tobuy
                        , ct, pub,
                       year, purchdate, tp, tp, rat, rf, readeddate);
            } else if (progress) {


                int tp = Integer.parseInt(m_totalpages.getText().toString());
                int ap = Integer.parseInt(m_acutalpage.getText().toString());

                newbook = Book.getOwnedProgressBook(titlu, autor, gen, obs, lang, toread, tobuy,
                        ct,pub, year, purchdate, tp, ap);

            } else if (read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getOwnedReadBook(titlu, autor, gen, obs, lang, toread, tobuy,
                        ct, pub, year,
                        purchdate, tp, rat, rf, readeddate);
            } else {
                newbook = Book.getOwnedBook(titlu, autor, gen, obs, lang, toread, tobuy, ct,
                        pub, year, purchdate);
            }

        } else//nu e cumparata
        {
            if (progress && read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());
                int ap = Integer.parseInt(m_acutalpage.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getProgressReadBook(titlu, autor, gen, obs, lang, toread, tobuy,
                        tp, ap, rat, rf, readeddate);
            } else if (progress) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());
                int ap = Integer.parseInt(m_acutalpage.getText().toString());

                newbook = Book.getProgressBook(titlu, autor, gen, obs, lang, toread, tobuy, tp, ap);
            } else if (read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getReadBook(titlu, autor, gen, obs, lang, toread, tobuy, tp, rat, rf, readeddate);
            } else {
                newbook = Book.getSimpleBook(titlu, autor, gen, obs, lang, toread, tobuy);
            }
        }
        //trimitem inapoi noua carte in baza de date
        DBManager db = DBManager.getInstance();
        if (db != null)
            db.insert(newbook);

        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


}
