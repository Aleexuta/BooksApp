package com.example.booksapp.Filters;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.booksapp.Books.CoverType;
import com.example.booksapp.Books.ReadFrom;
import com.example.booksapp.DatabaseHelper;
import com.example.booksapp.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FilterSecondPage extends Fragment implements View.OnClickListener {


    private TextView m_rattext;
    private ImageButton m_downrat;
    private ImageButton m_uprat;
    private RatingBar m_rat;
    private RadioGroup m_radgroup;
    private RadioButton m_minrat;
    private RadioButton m_maxrat;
    private RadioButton m_fisexrat;

    private TextView m_rftext;
    private ImageButton m_downrf;
    private ImageButton m_uprf;
    private Spinner m_readfromfilter;

    private TextView m_rdtext;
    private ImageButton m_downrd;
    private ImageButton m_uprd;
    private EditText m_rdmin;
    private EditText m_rdmax;
    private TextView m_lin;
    private CheckBox m_mincb;
    private CheckBox m_maxcb;

    private ImageButton m_right;
    private ImageButton m_left;

    private boolean[] m_boolcheked=new boolean[7];
    public void setWhatIsVisible(boolean owned,boolean progress)
    {
        m_boolcheked[3]=true;
        m_boolcheked[4]=owned;
        m_boolcheked[5]=progress;
    }
    public FilterSecondPage() {
        // Required empty public constructor

        for (boolean x:m_boolcheked) {
            x=false;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_filter_second_page, container, false);


        //m_readlayout=rootview.findViewById(R.id.readcheckedfilter);

        m_downrat=rootview.findViewById(R.id.godownrat);
        m_downrat.setOnClickListener(this);
        m_uprat=rootview.findViewById(R.id.gouprat);
        m_uprat.setOnClickListener(this);

        m_rat=rootview.findViewById(R.id.ratingfiltre);
        m_radgroup=rootview.findViewById(R.id.ratradiogroup);
        m_maxrat=rootview.findViewById(R.id.maxratingbut);
        m_minrat=rootview.findViewById(R.id.minratingbut);
        m_fisexrat=rootview.findViewById(R.id.fixedratingbut);

        m_downrf=rootview.findViewById(R.id.godownreadfrom);
        m_downrf.setOnClickListener(this);
        m_uprf=rootview.findViewById(R.id.goupreadfrom);
        m_uprf.setOnClickListener(this);
        m_readfromfilter=rootview.findViewById(R.id.readfromfilter);
        List<ReadFrom> readfrom = new ArrayList<ReadFrom>(Arrays.asList(ReadFrom.values()));
        ArrayAdapter<ReadFrom> adapterread = new ArrayAdapter<ReadFrom>(this.getActivity(), android.R.layout.simple_spinner_item, readfrom);
        adapterread.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_readfromfilter.setAdapter(adapterread);

        m_rdmin=rootview.findViewById(R.id.readdatefilterstg);
        m_rdmin.setInputType(InputType.TYPE_NULL);
        m_rdmin.setOnClickListener(this);
        m_rdmax=rootview.findViewById(R.id.readdatefilterdr);
        m_rdmax.setInputType(InputType.TYPE_NULL);
        m_rdmax.setOnClickListener(this);
        m_downrd=rootview.findViewById(R.id.godownreaddate);
        m_downrd.setOnClickListener(this);
        m_uprd=rootview.findViewById(R.id.goupreaddate);
        m_uprd.setOnClickListener(this);
        m_mincb=rootview.findViewById(R.id.minsupressed);
        m_mincb.setOnClickListener(this);
        m_maxcb=rootview.findViewById(R.id.maxsupressed);
        m_maxcb.setOnClickListener(this);

        m_rattext=rootview.findViewById(R.id.textView5);
        m_rftext=rootview.findViewById(R.id.textView6);
        m_rdtext=rootview.findViewById(R.id.textView7);
        m_lin=rootview.findViewById(R.id.textView8);




        m_left=rootview.findViewById(R.id.leftarrow2);
        m_left.setOnClickListener(this);
        m_right=rootview.findViewById(R.id.rightarrow2);
        m_right.setOnClickListener(this);

        setVisible();


        return rootview;
    }

    private void setReadVisible(boolean visible)
    {
        if(!visible)
        {
            m_downrat.setVisibility(View.GONE);
            m_uprat.setVisibility(View.GONE);
            m_rattext.setVisibility(View.GONE);
            m_downrf.setVisibility(View.GONE);
            m_uprf.setVisibility(View.GONE);
            m_rftext.setVisibility(View.GONE);
            m_rat.setVisibility(View.GONE);
            m_radgroup.setVisibility(View.GONE);
            m_readfromfilter.setVisibility(View.GONE);
            m_rdmin.setVisibility(View.GONE);
            m_rdmax.setVisibility(View.GONE);
            m_lin.setVisibility(View.GONE);
            m_downrd.setVisibility(View.GONE);
            m_uprd.setVisibility(View.GONE);
            m_mincb.setVisibility(View.GONE);
            m_maxcb.setVisibility(View.GONE);
        }
        else
        {
            if(m_boolcheked[0])
            {
                m_rat.setVisibility(View.VISIBLE);
                m_radgroup.setVisibility(View.VISIBLE);
                m_downrat.setVisibility(View.GONE);
                m_uprat.setVisibility(View.VISIBLE);
            }
            else {
                m_rat.setVisibility(View.GONE);
                m_radgroup.setVisibility(View.GONE);
                m_downrat.setVisibility(View.VISIBLE);
                m_uprat.setVisibility(View.GONE);
            }

            if(m_boolcheked[1])
            {
                m_readfromfilter.setVisibility(View.VISIBLE);
                m_downrf.setVisibility(View.GONE);
                m_uprf.setVisibility(View.VISIBLE);
            }
            else
            {
                m_readfromfilter.setVisibility(View.GONE);
                m_downrf.setVisibility(View.VISIBLE);
                m_uprf.setVisibility(View.GONE);
            }

            if(m_boolcheked[2])
            {
                m_rdmin.setVisibility(View.VISIBLE);
                m_rdmax.setVisibility(View.VISIBLE);
                m_lin.setVisibility(View.VISIBLE);
                m_downrd.setVisibility(View.GONE);
                m_uprd.setVisibility(View.VISIBLE);
                m_mincb.setVisibility(View.VISIBLE);
                m_maxcb.setVisibility(View.VISIBLE);

                m_rdmin.setEnabled(m_mincb.isChecked());
                m_rdmax.setEnabled(m_maxcb.isChecked());
            }
            else
            {
                m_rdmin.setVisibility(View.GONE);
                m_rdmax.setVisibility(View.GONE);
                m_lin.setVisibility(View.GONE);
                m_downrd.setVisibility(View.VISIBLE);
                m_uprd.setVisibility(View.GONE);
                m_mincb.setVisibility(View.GONE);
                m_maxcb.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==m_downrat.getId())
        {
            m_boolcheked[0]=true;
            setVisible();
        }
        if(v.getId()==m_uprat.getId())
        {
            m_boolcheked[0]=false;
            setVisible();
        }
        if(v.getId()==m_downrf.getId())
        {
            m_boolcheked[1]=true;
            setVisible();
        }
        if(v.getId()==m_uprf.getId())
        {
            m_boolcheked[1]=false;
            setVisible();
        }
        if(v.getId()==m_uprd.getId())
        {
            m_boolcheked[2]=false;
            setVisible();
        }
        if(v.getId()==m_downrd.getId())
        {
            m_boolcheked[2]=true;
            setVisible();
        }
        if(v.getId()==m_rdmin.getId() )
        {
            final Calendar clrd=Calendar.getInstance();
            int day=clrd.get(Calendar.DAY_OF_MONTH);
            int month=clrd.get(Calendar.MONTH);
            int year=clrd.get(Calendar.YEAR);

            DatePickerDialog picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    m_rdmin.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },year,month,day);
            picker.show();
        }
        if(v.getId()==m_rdmax.getId())
        {
            final Calendar clrd=Calendar.getInstance();
            int day=clrd.get(Calendar.DAY_OF_MONTH);
            int month=clrd.get(Calendar.MONTH);
            int year=clrd.get(Calendar.YEAR);

            DatePickerDialog picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    m_rdmax.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },year,month,day);
            picker.show();
        }
        if(m_mincb.getId()==v.getId())
        {
            if(m_mincb.isChecked())
                m_rdmin.setEnabled(true);
            else
                m_rdmin.setEnabled(false);
        }
        if(m_maxcb.getId()==v.getId())
        {
            if(m_maxcb.isChecked())
                m_rdmax.setEnabled(true);
            else
                m_rdmax.setEnabled(false);
        }
        if(v.getId()==m_left.getId())
        {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
        if(v.getId()==m_right.getId())
        {
            FragmentManager manager= requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction=manager.beginTransaction();
            Filter filter=Filter.getInstance();
            if(m_boolcheked[4])//owned trimit read si progress
            {
                transaction.add(R.id.filterframelayout, filter.getThrid()).addToBackStack("3thPage").commit();
                filter.getThrid().setWhatIsVisible(m_boolcheked[5],m_boolcheked[3]);

            } else
            if(m_boolcheked[5] || m_boolcheked[3])//progress trimit progress si read
            {
                transaction.add(R.id.filterframelayout, filter.getFourth()).addToBackStack("4thPage").commit();
                filter.getFourth().setWhatIsVisible(m_boolcheked[3],m_boolcheked[5]);
            }
        }
    }

    public void setVisible( ) {

        boolean read=m_boolcheked[3];
        setReadVisible(read);

        m_right.setVisibility(View.VISIBLE);

    }

    String selection = "";
    ArrayList<String> selectionArgs = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterData()
    {
        if(m_boolcheked[0])
        {
            if(m_minrat.isChecked())
                selection+= DatabaseHelper._Rating+" >=? and ";
            if(m_maxrat.isChecked())
                selection+= DatabaseHelper._Rating+" <=? and ";
            if(m_fisexrat.isChecked())
                selection+= DatabaseHelper._Rating+" =? and ";
            selectionArgs.add(String.valueOf(m_rat.getRating()));
        }
        if(m_boolcheked[1])
        {
            selection+=DatabaseHelper._ReadFrom+" =? and ";
            selectionArgs.add(m_readfromfilter.getSelectedItem().toString());
        }
        if(m_boolcheked[2])
        {
            if(m_mincb.isChecked() && m_maxcb.isChecked())
            {
                selection+=DatabaseHelper._ReadDate+" >=? and "+
                        DatabaseHelper._ReadDate+" <=? and ";

                selectionArgs.add(m_rdmin.getText().toString());
                selectionArgs.add(m_rdmax.getText().toString());
            }
            else if(m_mincb.isChecked())
            {
                selection+=DatabaseHelper._ReadDate+" >=? and ";
                selectionArgs.add(m_rdmin.getText().toString());
            }
            else if(m_maxcb.isChecked())
            {
                selection+=DatabaseHelper._ReadDate+" <=? and ";
                selectionArgs.add(m_rdmax.getText().toString());
            }
        }
        Filter.addFilters(selection,selectionArgs);
        selection="";
        selectionArgs.removeAll(selectionArgs);
    }
}