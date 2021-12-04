package com.example.booksapp.Filters;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    private TextView m_covertext;
    private ImageButton m_downcover;
    private ImageButton m_upcover;
    private Spinner m_coverfilter;

    private ImageButton m_right;
    private ImageButton m_left;

    private boolean[] m_boolcheked=new boolean[6];
    public void setWhatIsVisible(boolean read,boolean owned)
    {
        m_boolcheked[3]=read;
        m_boolcheked[4]=owned;
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
        m_uprat.setOnClickListener(this);
        m_readfromfilter=rootview.findViewById(R.id.readfromfilter);
        List<ReadFrom> readfrom = new ArrayList<ReadFrom>(Arrays.asList(ReadFrom.values()));
        ArrayAdapter<ReadFrom> adapterread = new ArrayAdapter<ReadFrom>(this.getActivity(), android.R.layout.simple_spinner_item, readfrom);
        adapterread.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_readfromfilter.setAdapter(adapterread);

        //m_ownedlayout=rootview.findViewById(R.id.ownedcheckedfilter);
        m_covertext=rootview.findViewById(R.id.textView7);
        m_rattext=rootview.findViewById(R.id.textView5);
        m_rftext=rootview.findViewById(R.id.textView6);


        m_downcover=rootview.findViewById(R.id.godowncover);
        m_downcover.setOnClickListener(this);
        m_upcover=rootview.findViewById(R.id.goupcover);
        m_upcover.setOnClickListener(this);
        m_coverfilter=rootview.findViewById(R.id.coverfilter);
        List<CoverType> ownedtype = new ArrayList<CoverType>(Arrays.asList(CoverType.values()));
        ArrayAdapter<CoverType> adapterown = new ArrayAdapter<CoverType>(this.getActivity(), android.R.layout.simple_spinner_item, ownedtype);
        adapterown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_coverfilter.setAdapter(adapterown);


        m_left=rootview.findViewById(R.id.leftarrow1);
        m_left.setOnClickListener(this);
        m_right=rootview.findViewById(R.id.rightarrow1);
       // m_right.setOnClickListener(this);

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
        }
    }
    private void setOwnedVisible(boolean owned)
    {
        if(!owned)
        {
            m_downcover.setVisibility(View.GONE);
            m_upcover.setVisibility(View.GONE);
            m_covertext.setVisibility(View.GONE);
            m_coverfilter.setVisibility(View.GONE);
        }
        else
        {


            if(m_boolcheked[2])
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
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==m_downcover.getId())
        {
            m_boolcheked[2]=true;
            setVisible();
        }
        if(v.getId()==m_upcover.getId())
        {
            m_boolcheked[2]=false;
            setVisible();
        }
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

        if(v.getId()==m_left.getId())
        {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    public void setVisible( ) {

        boolean read=m_boolcheked[3];
        boolean owned=m_boolcheked[4];
        setReadVisible(read);
        setOwnedVisible(owned);
    }

    String selection = "";
    ArrayList<String> selectionArgs = new ArrayList<>();

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
            selection+=DatabaseHelper._Cover+" =? and ";
            selectionArgs.add(m_coverfilter.getSelectedItem().toString());
        }
        Filter.addFilters(selection,selectionArgs);
        selection="";
        selectionArgs.removeAll(selectionArgs);
    }
}