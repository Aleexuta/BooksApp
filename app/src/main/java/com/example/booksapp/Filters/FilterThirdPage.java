package com.example.booksapp.Filters;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.booksapp.Books.CoverType;
import com.example.booksapp.DatabaseHelper;
import com.example.booksapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class FilterThirdPage extends Fragment implements  View.OnClickListener{


    private TextView m_covertext;
    private ImageButton m_downcover;
    private ImageButton m_upcover;
    private Spinner m_coverfilter;

    private TextView m_publishertext;
    private ImageButton m_downpublisher;
    private ImageButton m_uppublisher;
    private EditText m_publisher;

    private TextView m_yeartext;
    private ImageButton m_downyear;
    private ImageButton m_upyear;
    private TextView m_from;
    private TextView m_to;
    private EditText m_minyear;
    private EditText m_maxyear;

    private TextView m_readdatetext;
    private ImageButton m_downreaddate;
    private ImageButton m_upreaddate;
    private EditText m_mindate;
    private EditText m_maxdate;
    private TextView m_lin;
    private CheckBox m_mincb;
    private CheckBox m_maxcb;


    private ImageButton m_left;
    private ImageButton m_right;

    private boolean[] m_boolcheked=new boolean[6];

    public void setWhatIsVisible(boolean progress)
    {
        m_boolcheked[4]=true;//owned
        m_boolcheked[5]=progress;
    }
    public FilterThirdPage() {
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
        View rootview= inflater.inflate(R.layout.fragment_filter_third_page, container, false);

        m_covertext=rootview.findViewById(R.id.textView7);
        m_downcover=rootview.findViewById(R.id.godowncover);
        m_downcover.setOnClickListener(this);
        m_upcover=rootview.findViewById(R.id.goupcover);
        m_upcover.setOnClickListener(this);
        m_coverfilter=rootview.findViewById(R.id.coverfilter);
        List<CoverType> ownedtype = new ArrayList<CoverType>(Arrays.asList(CoverType.values()));
        ArrayAdapter<CoverType> adapterown = new ArrayAdapter<CoverType>(this.getActivity(), android.R.layout.simple_spinner_item, ownedtype);
        adapterown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_coverfilter.setAdapter(adapterown);

        m_publishertext=rootview.findViewById(R.id.textView9);
        m_downpublisher=rootview.findViewById(R.id.godownpublisher);
        m_downpublisher.setOnClickListener(this);
        m_uppublisher=rootview.findViewById(R.id.gouppublisher);
        m_uppublisher.setOnClickListener(this);
        m_publisher=rootview.findViewById(R.id.publisherfilter);

        m_yeartext=rootview.findViewById(R.id.textView12);
        m_downyear=rootview.findViewById(R.id.godownyear);
        m_downyear.setOnClickListener(this);
        m_upyear=rootview.findViewById(R.id.goupyear);
        m_upyear.setOnClickListener(this);
        m_from=rootview.findViewById(R.id.textView14);
        m_to=rootview.findViewById(R.id.textView13);
        m_minyear=rootview.findViewById(R.id.yearmin);
        m_maxyear=rootview.findViewById(R.id.yearmax);


        m_readdatetext=rootview.findViewById(R.id.textView10);
        m_downreaddate=rootview.findViewById(R.id.godownboughtdate);
        m_downreaddate.setOnClickListener(this);
        m_upreaddate=rootview.findViewById(R.id.goupboughtdate);
        m_upreaddate.setOnClickListener(this);
        m_mindate=rootview.findViewById(R.id.boughtdatefilterstg);
        m_mindate.setOnClickListener(this);
        m_mindate.setInputType(InputType.TYPE_NULL);
        m_maxdate=rootview.findViewById(R.id.boughtdatefilterdr);
        m_maxdate.setOnClickListener(this);
        m_maxdate.setInputType(InputType.TYPE_NULL);
        m_lin=rootview.findViewById(R.id.textView11);
        m_mincb=rootview.findViewById(R.id.minsupressed2);
        m_mincb.setOnClickListener(this);
        m_maxcb=rootview.findViewById(R.id.maxsupressed2);
        m_maxcb.setOnClickListener(this);

        m_left=rootview.findViewById(R.id.left3);
        m_left.setOnClickListener(this);
        m_right=rootview.findViewById(R.id.right3);
        m_right.setOnClickListener(this);
        setVisible();
        return rootview;
    }

    private void setOwnedVisible(boolean owned)
    {
        if(!owned)
        {
            m_downcover.setVisibility(View.GONE);
            m_upcover.setVisibility(View.GONE);
            m_covertext.setVisibility(View.GONE);
            m_coverfilter.setVisibility(View.GONE);
            m_publisher.setVisibility(View.GONE);
            m_downpublisher.setVisibility(View.GONE);
            m_uppublisher.setVisibility(View.GONE);
            m_from.setVisibility(View.GONE);
            m_to.setVisibility(View.GONE);
            m_minyear.setVisibility(View.GONE);
            m_maxyear.setVisibility(View.GONE);
            m_downyear.setVisibility(View.GONE);
            m_upyear.setVisibility(View.GONE);
            m_mindate.setVisibility(View.GONE);
            m_maxdate.setVisibility(View.GONE);
            m_lin.setVisibility(View.GONE);
            m_mincb.setVisibility(View.GONE);
            m_maxcb.setVisibility(View.GONE);
            m_downreaddate.setVisibility(View.GONE);
            m_upreaddate.setVisibility(View.GONE);
        }
        else
        {
            if(m_boolcheked[0])
            {
                m_coverfilter.setVisibility(View.VISIBLE);
                m_downcover.setVisibility(View.GONE);
                m_upcover.setVisibility(View.VISIBLE);
            }
            else
            {
                m_coverfilter.setVisibility(View.GONE);
                m_downcover.setVisibility(View.VISIBLE);
                m_upcover.setVisibility(View.GONE);
            }
            if(m_boolcheked[1])
            {
                m_publisher.setVisibility(View.VISIBLE);
                m_downpublisher.setVisibility(View.GONE);
                m_uppublisher.setVisibility(View.VISIBLE);
            }
            else
            {
                m_publisher.setVisibility(View.GONE);
                m_downpublisher.setVisibility(View.VISIBLE);
                m_uppublisher.setVisibility(View.GONE);
            }
            if(m_boolcheked[2])
            {
                m_from.setVisibility(View.VISIBLE);
                m_to.setVisibility(View.VISIBLE);
                m_minyear.setVisibility(View.VISIBLE);
                m_maxyear.setVisibility(View.VISIBLE);
                m_downyear.setVisibility(View.GONE);
                m_upyear.setVisibility(View.VISIBLE);
            }
            else
            {
                m_from.setVisibility(View.GONE);
                m_to.setVisibility(View.GONE);
                m_minyear.setVisibility(View.GONE);
                m_maxyear.setVisibility(View.GONE);
                m_downyear.setVisibility(View.VISIBLE);
                m_upyear.setVisibility(View.GONE);
            }
            if(m_boolcheked[3])
            {
                m_mindate.setVisibility(View.VISIBLE);
                m_maxdate.setVisibility(View.VISIBLE);
                m_lin.setVisibility(View.VISIBLE);
                m_mincb.setVisibility(View.VISIBLE);
                m_maxcb.setVisibility(View.VISIBLE);
                m_downreaddate.setVisibility(View.GONE);
                m_upreaddate.setVisibility(View.VISIBLE);

                m_mindate.setEnabled(m_mincb.isChecked());
                m_maxdate.setEnabled(m_maxcb.isChecked());
            }
            else
            {
                m_mindate.setVisibility(View.GONE);
                m_maxdate.setVisibility(View.GONE);
                m_lin.setVisibility(View.GONE);
                m_mincb.setVisibility(View.GONE);
                m_maxcb.setVisibility(View.GONE);
                m_downreaddate.setVisibility(View.VISIBLE);
                m_upreaddate.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==m_downcover.getId())
        {
            m_boolcheked[0]=true;
            setVisible();
        }
        if(v.getId()==m_upcover.getId())
        {
            m_boolcheked[0]=false;
            setVisible();
        }
        if(v.getId()==m_downpublisher.getId())
        {
            m_boolcheked[1]=true;
            setVisible();
        }
        if(v.getId()==m_uppublisher.getId())
        {
            m_boolcheked[1]=false;
            setVisible();
        }
        if(v.getId()==m_downyear.getId())
        {
            m_boolcheked[2]=true;
            setVisible();
        }
        if(v.getId()==m_upyear.getId())
        {
            m_boolcheked[2]=false;
            setVisible();
        }
        if(v.getId()==m_downreaddate.getId())
        {
            m_boolcheked[3]=true;
            setVisible();
        }
        if(v.getId()==m_upreaddate.getId())
        {
            m_boolcheked[3]=false;
            setVisible();
        }


        if(m_mindate.getId()==v.getId())
        {
            final Calendar clrd=Calendar.getInstance();
            int day=clrd.get(Calendar.DAY_OF_MONTH);
            int month=clrd.get(Calendar.MONTH);
            int year=clrd.get(Calendar.YEAR);

            DatePickerDialog picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    m_mindate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },year,month,day);
            picker.show();
        }
        if(m_maxdate.getId()==v.getId())
        {
            final Calendar clrd=Calendar.getInstance();
            int day=clrd.get(Calendar.DAY_OF_MONTH);
            int month=clrd.get(Calendar.MONTH);
            int year=clrd.get(Calendar.YEAR);

            DatePickerDialog picker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    m_maxdate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },year,month,day);
            picker.show();
        }

        if(v.getId()==m_mincb.getId())
        {
            m_mindate.setEnabled(m_mincb.isChecked());
        }
        if(v.getId()==m_maxcb.getId())
        {
            m_maxdate.setEnabled(m_maxcb.isChecked());
        }
        if(v.getId()==m_left.getId())
        {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    private void setVisible()
    {
        setOwnedVisible(m_boolcheked[4]);

        //daca e si in progress at afisam sagetica la dreapta
        if(m_boolcheked[5])
            m_right.setVisibility(View.VISIBLE);
        else
            m_right.setVisibility(View.GONE);

    }

    String selection = "";
    ArrayList<String> selectionArgs = new ArrayList<>();


    public void filterData()
    {
        if(m_boolcheked[0])
        {
            selection+= DatabaseHelper._Cover+" =? and ";
            selectionArgs.add(m_coverfilter.getSelectedItem().toString());
        }
        if(m_boolcheked[1])
        {
            selection+=DatabaseHelper._Publisher+" =? and ";
            selectionArgs.add(m_publisher.toString());
        }
        if(m_boolcheked[2])
        {
            selection+=DatabaseHelper._Year+" >=? and <=? and ";
            selectionArgs.add(m_minyear.toString());
            selectionArgs.add(m_maxyear.toString());
        }
        if(m_boolcheked[3])
        {
            if(m_mincb.isChecked() && m_maxcb.isChecked())
            {
                selection+=DatabaseHelper._ReadDate+" >=? and <=? and ";
                selectionArgs.add(m_mindate.toString());
                selectionArgs.add(m_maxdate.toString());
            }
            if(m_mincb.isChecked())
            {
                selection+=DatabaseHelper._ReadDate+" >=? and ";
                selectionArgs.add(m_mindate.toString());
            }
            if(m_maxcb.isChecked())
            {
                selection+=DatabaseHelper._ReadDate+" <=? and ";
                selectionArgs.add(m_maxdate.toString());
            }
        }
    }
}