package com.example.booksapp.Books;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

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

import com.example.booksapp.DBManager;
import com.example.booksapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class EditBook extends Fragment implements  View.OnClickListener{

    public EditBook() {
        // Required empty public constructor
    }

    long m_id;
    public EditBook(long id)
    {
        super();
        m_id=id;
    }
    public void setID(long id)
    {
        m_id=id;
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
    Button m_edit;
    Button m_close;
    Button m_delete;
    EditText m_readdate;
    EditText m_boughtdate;
    EditText m_totalpages;
    EditText m_acutalpage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_book, container, false);

        Spinner spinner = v.findViewById(R.id.spinnergenreEdit);
        List<BookType> genre = new ArrayList<BookType>(Arrays.asList(BookType.values()));
        ArrayAdapter<BookType> adaptergenre = new ArrayAdapter<BookType>(this.getActivity(), android.R.layout.simple_spinner_item, genre);
        adaptergenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptergenre);
        this.m_genre = spinner;

        Spinner spinner2 = v.findViewById(R.id.readedfromspinnerEdit);
        List<ReadFrom> readfrom = new ArrayList<ReadFrom>(Arrays.asList(ReadFrom.values()));
        ArrayAdapter<ReadFrom> adapterread = new ArrayAdapter<ReadFrom>(this.getActivity(), android.R.layout.simple_spinner_item, readfrom);
        adapterread.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterread);
        this.m_readfromspinner = spinner2;

        Spinner spinner3 = v.findViewById(R.id.coverbooktypespinnerEdit);
        List<CoverType> ownedtype = new ArrayList<CoverType>(Arrays.asList(CoverType.values()));
        ArrayAdapter<CoverType> adapterown = new ArrayAdapter<CoverType>(this.getActivity(), android.R.layout.simple_spinner_item, ownedtype);
        adapterown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterown);
        this.m_coverbooktypespinner = spinner3;

        Spinner spinner4 = v.findViewById(R.id.languagespinnerEdit);
        List<Language> language = new ArrayList<>(Arrays.asList(Language.values()));
        ArrayAdapter<Language> adapterlang = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, language);
        adapterlang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapterlang);
        this.m_language = spinner4;


        this.m_ratbar =v.findViewById(R.id.ratingBarEdit);


        this.m_titlu = v.findViewById(R.id.editTitle);
        this.m_autor = v.findViewById(R.id.editauthor);
        this.m_publicatie = v.findViewById(R.id.publisherEdit);
        this.m_year = v.findViewById(R.id.yearEdit);
        this.m_obs = v.findViewById(R.id.ObservatiiEdit);
        this.m_boughtdate=v.findViewById(R.id.boughtdateEdit);
        this.m_readdate=v.findViewById(R.id.readeddateEdit);
        this.m_totalpages=v.findViewById(R.id.tottalpagesEdit);
        this.m_acutalpage=v.findViewById(R.id.actualpagesEdit);


        CheckBox cb = v.findViewById(R.id.owncheckEdit);
        cb.setOnClickListener(this);
        this.m_owned = cb;
        CheckBox cb2 = v.findViewById(R.id.readcheckEdit);
        cb2.setOnClickListener(this);
        this.m_read= cb2;
        CheckBox cb3 = v.findViewById(R.id.tobuycheckEdit);
        cb3.setOnClickListener(this);
        this.m_tobuy = cb3;
        CheckBox cb4 = v.findViewById(R.id.toreadcheckEdit);
        cb4.setOnClickListener(this);
        this.m_toread = cb4;
        CheckBox cb5 = v.findViewById(R.id.progresscheckEdit);
        cb5.setOnClickListener(this);
        this.m_progress = cb5;


        Button addbut=v.findViewById(R.id.okbuttonEdit);
        m_edit=addbut;
        addbut.setOnClickListener(this);

        m_close=v.findViewById(R.id.cancelbuttonEdit);
        m_close.setOnClickListener(this);
        m_delete=v.findViewById(R.id.deletebutton);
        m_delete.setOnClickListener(this);

        m_ratbar.setVisibility(View.GONE);
        m_readfromspinner.setVisibility(View.GONE);
        m_readdate.setVisibility(View.GONE);
        m_acutalpage.setVisibility(View.GONE);
        m_totalpages.setVisibility(View.GONE);
        m_coverbooktypespinner.setVisibility(View.GONE);
        m_publicatie.setVisibility(View.GONE);
        m_year.setVisibility(View.GONE);
        m_boughtdate.setVisibility(View.GONE);

        m_readdate.setOnClickListener(this);
        m_readdate.setInputType(InputType.TYPE_NULL);
        m_boughtdate.setOnClickListener(this);
        m_boughtdate.setInputType(InputType.TYPE_NULL);

        LoadBook();

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void LoadBook()
    {
        DBManager db= DBManager.getInstance();
       IBook book=db.getBook(m_id);

        if(book==null)
            return;
        m_titlu.setText(book.getM_title());
        m_autor.setText(book.getM_author());
        int pos=BookType.valueOf(book.getM_genre().toString()).ordinal();
        m_genre.setSelection(pos);
        pos=Language.valueOf(book.getM_language().toString()).ordinal();
        m_language.setSelection(pos);
        m_obs.setText(book.getM_obs());
        m_tobuy.setChecked(book.isM_toBuy());
        m_toread.setChecked(book.isM_toRead());

        m_read.setChecked(book.isM_Read());
        m_owned.setChecked(book.isM_Owned());
        m_progress.setChecked(book.isM_inProgress());
        android.icu.text.SimpleDateFormat formatter= new android.icu.text.SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        if(m_read.isChecked())
        {
            m_ratbar.setRating(book.getM_rating());
            pos = ReadFrom.valueOf(book.getM_readFrom().toString()).ordinal();
            m_readfromspinner.setSelection(pos);
            m_readdate.setText(book.getM_readDate());
            m_totalpages.setText(String.valueOf(book.getM_totalPages()));


            m_ratbar.setVisibility(View.VISIBLE);
            m_readfromspinner.setVisibility(View.VISIBLE);
            m_readdate.setVisibility(View.VISIBLE);
            m_totalpages.setVisibility(View.VISIBLE);
        }
        if(m_progress.isChecked())
        {
            m_acutalpage.setText(String.valueOf(book.getM_actualPage()));
            m_totalpages.setText(String.valueOf(book.getM_totalPages()));

            m_acutalpage.setVisibility(View.VISIBLE);
            m_totalpages.setVisibility(View.VISIBLE);
        }
        if(m_owned.isChecked()) {
            pos = CoverType.valueOf(book.getM_coverType().toString()).ordinal();
            m_coverbooktypespinner.setSelection(pos);
            m_publicatie.setText(book.getM_publisher());
            m_year.setText(book.getM_yearPublication());
            m_boughtdate.setText(book.getM_purchaseDate());

            m_coverbooktypespinner.setVisibility(View.VISIBLE);
            m_publicatie.setVisibility(View.VISIBLE);
            m_year.setVisibility(View.VISIBLE);
            m_boughtdate.setVisibility(View.VISIBLE);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int readid = m_read.getId();
        int ownid = m_owned.getId();
        int progressid=m_progress.getId();
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
        if(idv==progressid)
        {
            if(((CheckBox)v).isChecked())
            {
                m_totalpages.setVisibility(View.VISIBLE);
                m_acutalpage.setVisibility(View.VISIBLE);
            }
            else
            {
                m_totalpages.setVisibility(View.GONE);
                m_acutalpage.setVisibility(View.GONE);
            }
            return;
        }

        int edit=m_edit.getId();
        if(idv==edit)
        {
            updateBook();
            return;
        }
        int cllid=m_close.getId();
        if(idv==cllid)
        {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            return;
        }
        if(idv==m_delete.getId())
        {
            //delete
            DBManager db = DBManager.getInstance();
            if (db != null)
                db.delete(m_id);
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
                    m_readdate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
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
                    m_boughtdate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },year,month,day);
            picker.show();
        }
    }

    public boolean onBackPressed() { return false; }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    private void updateBook() {
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

        if (owned) {
            CoverType ct = (CoverType) m_coverbooktypespinner.getSelectedItem();

            String pub=m_publicatie.getText().toString();
            String year=m_year.getText().toString();

            if (progress && read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getOwnedProgressReadBook(titlu, autor, gen, obs, lang, toread, tobuy
                        , ct, pub,
                        year, m_boughtdate.getText().toString(), tp,tp, rat, rf, m_readdate.getText().toString());
            } else if (progress) {


                int tp = Integer.parseInt(m_totalpages.getText().toString());
                int ap = Integer.parseInt(m_acutalpage.getText().toString());

                newbook = Book.getOwnedProgressBook(titlu, autor, gen, obs, lang, toread, tobuy,
                        ct,pub, year, m_boughtdate.getText().toString(), tp, ap);

            } else if (read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getOwnedReadBook(titlu, autor, gen, obs, lang, toread, tobuy,
                        ct, pub, year,
                        m_boughtdate.getText().toString(), tp, rat, rf, m_readdate.getText().toString());
            } else {
                newbook = Book.getOwnedBook(titlu, autor, gen, obs, lang, toread, tobuy, ct,
                        pub, year, m_boughtdate.getText().toString());
            }

        } else//nu e cumparata
        {
            if (progress && read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());
                int ap = Integer.parseInt(m_acutalpage.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getProgressReadBook(titlu, autor, gen, obs, lang, toread, tobuy,
                        tp, ap, rat, rf, m_readdate.getText().toString());
            } else if (progress) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());
                int ap = Integer.parseInt(m_acutalpage.getText().toString());

                newbook = Book.getProgressBook(titlu, autor, gen, obs, lang, toread, tobuy, tp, ap);
            } else if (read) {
                int tp = Integer.parseInt(m_totalpages.getText().toString());

                float rat =m_ratbar.getRating();
                ReadFrom rf = (ReadFrom) m_readfromspinner.getSelectedItem();

                newbook = Book.getReadBook(titlu, autor, gen, obs, lang, toread, tobuy, tp, rat, rf, m_readdate.getText().toString());
            } else {
                newbook = Book.getSimpleBook(titlu, autor, gen, obs, lang, toread, tobuy);
            }
        }
        //trimitem inapoi noua carte in baza de date
        DBManager db = DBManager.getInstance();
        if (db != null)
            db.update(m_id,newbook);

        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}