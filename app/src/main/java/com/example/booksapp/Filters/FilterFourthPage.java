package com.example.booksapp.Filters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.booksapp.DB.DatabaseHelper;
import com.example.booksapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFourthPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterFourthPage extends Fragment implements View.OnClickListener {

    private ImageButton m_downnrpages;
    private ImageButton m_upnrpages;
    private TextView m_textnrpages;
    private TextView m_from;
    private TextView m_to;
    private EditText m_minnrpages;
    private EditText m_maxnrpages;

    private ImageButton m_downbookmark;
    private ImageButton m_upbookmark;
    private TextView m_textbookmark;
    private EditText m_bookmark;
    private RadioGroup m_bookmarkgroup;
    private RadioButton m_readmin;
    private RadioButton m_readmax;
    private RadioButton m_leftmin;
    private RadioButton m_leftmax;

    private ImageButton m_left;
    private ImageButton m_right;

    private boolean[] m_boolcheked=new boolean[6];//0-nrpages
    //1-bookmark
    //2-progress
    //3-read

    public void setWhatIsVisible(boolean read,boolean progress)
    {
        m_boolcheked[2]=progress;
        m_boolcheked[3]=read;
    }
    public FilterFourthPage() {
        // Required empty public constructor
        for (boolean x:m_boolcheked) {
            x=false;
        }
    }

    public static FilterFourthPage newInstance(String param1, String param2) {
        FilterFourthPage fragment = new FilterFourthPage();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setProgressVisible(boolean progress)
    {
        if(!progress)
        {
            m_downbookmark.setVisibility(View.GONE);
            m_upbookmark.setVisibility(View.GONE);
            m_textbookmark.setVisibility(View.GONE);
            m_bookmarkgroup.setVisibility(View.GONE);
            m_bookmark.setVisibility(View.GONE);
        }
        else
        {
            if(m_boolcheked[1])
            {
                m_downbookmark.setVisibility(View.GONE);
                m_upbookmark.setVisibility(View.VISIBLE);
                m_bookmarkgroup.setVisibility(View.VISIBLE);
                m_bookmark.setVisibility(View.VISIBLE);
            }
            else
            {
                m_downbookmark.setVisibility(View.VISIBLE);
                m_upbookmark.setVisibility(View.GONE);
                m_bookmarkgroup.setVisibility(View.GONE);
                m_bookmark.setVisibility(View.GONE);
            }
        }
    }
    private void setReadVisible(boolean read)
    {
        if(!read)
        {
            m_downnrpages.setVisibility(View.GONE);
            m_upnrpages.setVisibility(View.GONE);
            m_from.setVisibility(View.GONE);
            m_to.setVisibility(View.GONE);
            m_minnrpages.setVisibility(View.GONE);
            m_maxnrpages.setVisibility(View.GONE);
            m_textnrpages.setVisibility(View.GONE);
        }
        else
        {
            if(m_boolcheked[0])
            {
                m_downnrpages.setVisibility(View.GONE);
                m_upnrpages.setVisibility(View.VISIBLE);
                m_from.setVisibility(View.VISIBLE);
                m_to.setVisibility(View.VISIBLE);
                m_minnrpages.setVisibility(View.VISIBLE);
                m_maxnrpages.setVisibility(View.VISIBLE);
            }
            else
            {
                m_downnrpages.setVisibility(View.VISIBLE);
                m_upnrpages.setVisibility(View.GONE);
                m_from.setVisibility(View.GONE);
                m_to.setVisibility(View.GONE);
                m_minnrpages.setVisibility(View.GONE);
                m_maxnrpages.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_filter_fourth_page, container, false);

        m_downnrpages=rootView.findViewById(R.id.godowntotalpages);
        m_downnrpages.setOnClickListener(this);
        m_upnrpages=rootView.findViewById(R.id.gouptotalpages);
        m_upnrpages.setOnClickListener(this);
        m_textnrpages=rootView.findViewById(R.id.textView15);
        m_from=rootView.findViewById(R.id.textView17);
        m_to=rootView.findViewById(R.id.textView16);
        m_minnrpages=rootView.findViewById(R.id.minpages);
        m_maxnrpages=rootView.findViewById(R.id.maxpages);

        m_downbookmark=rootView.findViewById(R.id.godownbookmark);
        m_downbookmark.setOnClickListener(this);
        m_upbookmark=rootView.findViewById(R.id.goupbookmark);
        m_upbookmark.setOnClickListener(this);
        m_textbookmark=rootView.findViewById(R.id.textView18);
        m_bookmark=rootView.findViewById(R.id.bookmarkfilter);
        m_bookmarkgroup=rootView.findViewById(R.id.bookmarkgroup);
        m_readmin=rootView.findViewById(R.id.prmin);
        m_readmax=rootView.findViewById(R.id.prmax);
        m_leftmin=rootView.findViewById(R.id.plmin);
        m_leftmax=rootView.findViewById(R.id.plmax);


        m_left=rootView.findViewById(R.id.leftarrow3);
        m_left.setOnClickListener(this);
        setVisible();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==m_downnrpages.getId())
        {
            m_boolcheked[0]=true;
            setVisible();
        }
        if(v.getId()==m_upnrpages.getId())
        {
            m_boolcheked[0]=false;
            setVisible();
        }
        if(v.getId()==m_downbookmark.getId())
        {
            m_boolcheked[1]=true;
            setVisible();
        }
        if(v.getId()==m_upbookmark.getId())
        {
            m_boolcheked[1]=false;
            setVisible();
        }
        if(v.getId()==m_left.getId())
        {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    private void setVisible()
    {
        setProgressVisible(m_boolcheked[2]);
        setReadVisible(m_boolcheked[3]);
        //m_right.setVisibility(View.GONE);
    }

    String selection = "";
    ArrayList<String> selectionArgs = new ArrayList<>();

    public void filterData()
    {
        if(m_boolcheked[0])
        {
            selection+= DatabaseHelper._TotalPages+" >=? and <=? and ";
            selectionArgs.add(m_minnrpages.getText().toString());
            selectionArgs.add(m_maxnrpages.getText().toString());
        }
        if(m_boolcheked[1])
        {

            if(m_readmin.isChecked())
            {
                selection+=DatabaseHelper._ActualPage+" >=? and ";
            }
            else if(m_readmax.isChecked())
            {
                selection+=DatabaseHelper._ActualPage+" <=? and ";
            }
            else if(m_leftmin.isChecked())
            {
                selection+=DatabaseHelper._TotalPages+" >=? + "+DatabaseHelper._ActualPage+" and ";
            }
            else if(m_leftmax.isChecked())
            {
                selection+=DatabaseHelper._TotalPages+" <=? + "+DatabaseHelper._ActualPage+" and ";
            }
            selectionArgs.add(m_bookmark.getText().toString());
        }
        Filter.addFilters(selection,selectionArgs);
        selection="";
        selectionArgs.removeAll(selectionArgs);
    }
}