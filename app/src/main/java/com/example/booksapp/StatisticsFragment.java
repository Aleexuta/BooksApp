package com.example.booksapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.booksapp.Books.BookType;
import com.example.booksapp.DB.DBManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticsFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton m_exit;
    private TextView m_det;
    private Spinner m_spinner;

    private boolean m_read=true;
    private boolean m_owned=false;
    private boolean m_monthchaged=false;

    private AnyChartView m_yearchart;
    private AnyChartView m_monthchart;

    private  List<DataEntry> datamonth=null;
    private  List<DataEntry> datayear=null;
    private Set month;
    private Set year;
    public StatisticsFragment() {
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
        View rootview= inflater.inflate(R.layout.fragment_statistics, container, false);
        m_exit=rootview.findViewById(R.id.exitstat);
        m_exit.setOnClickListener(this);
        m_det=rootview.findViewById(R.id.detailstext);
        m_spinner=rootview.findViewById(R.id.readowned);
        m_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setDataYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> list = new ArrayList<String>();
        list.add("Read books");
        list.add("Bought books");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spinner.setAdapter(adapter);


        m_yearchart = rootview.findViewById(R.id.yearchart);
        m_yearchart.setProgressBar(rootview.findViewById(R.id.yearprogressBar));
        APIlib.getInstance().setActiveAnyChartView(m_yearchart);
        Cartesian cartesian = AnyChart.column();
        year=Set.instantiate();
        year.data(datayear);
        Mapping map=year.mapAs("{ x: 'x', value: 'value' }");
        Column column = cartesian.column(map);
        column.tooltip().format("{%Value} Books");
        cartesian.xAxis(0).title("Year");
        cartesian.yAxis(0).title("Number of books");
        cartesian.background().fill("#FFE194");
        column.color("#8AE8D7");
        m_yearchart.setChart(cartesian);
        setDataYear();

        column.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(requireContext(), event.getData().get("x"), Toast.LENGTH_SHORT).show();
                APIlib.getInstance().setActiveAnyChartView(m_monthchart);
                if(m_read){
                    datamonth=DBManager.getInstance().getReadBooksPerMonth(event.getData().get("x"));
                    month.data(datamonth);
                    m_det.setText("Read books for year: "+event.getData().get("x"));
                }
                else if(m_owned)
                {
                    datamonth=DBManager.getInstance().geOwnedBooksPerMonth(event.getData().get("x"));
                    month.data(datamonth);
                    m_det.setText("Bought books for year: "+event.getData().get("x"));
                }
            }
        });

        m_monthchart=rootview.findViewById(R.id.monthchart);
        m_monthchart.setProgressBar(rootview.findViewById(R.id.monthprogressBar));
        APIlib.getInstance().setActiveAnyChartView(m_monthchart);

        Cartesian cartesian2=AnyChart.column();
        month=Set.instantiate();
        month.data(datamonth);
        Mapping map2=month.mapAs("{ x: 'x', value: 'value' }");
        Column column2=cartesian2.column(map2);
        column2.tooltip().format("{%Value} Books");
        cartesian2.xAxis(0).title("Months");
        cartesian2.yAxis(0).title("Number of books");
        cartesian2.background().fill("#FFE194");
        column2.color("#8AE8D7");
        m_monthchart.setChart(cartesian2);
        m_monthchart.refreshDrawableState();






        return rootview;
    }

    private void setDataYear()
    {
        APIlib.getInstance().setActiveAnyChartView(m_yearchart);
        if(m_spinner.getSelectedItem().toString().equals("Read books"))
        {
            datayear=DBManager.getInstance().getReadBooksPerYear();
            year.data(datayear);
            m_read=true;
            m_owned=false;
        }
        else if(m_spinner.getSelectedItem().toString().equals("Bought books"))
        {
            datayear=DBManager.getInstance().getOwnedBooksPerYear();
            year.data(datayear);
            m_read=false;
            m_owned=true;
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==m_exit.getId())
        {
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }
}
