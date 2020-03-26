package com.example.diarrheainmyarea;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link statisticalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class statisticalFragment extends Fragment {

    private DatabaseReference dbDates,mDatabase;
    private ColumnChartView chart;
    private ColumnChartData data;
    private Spinner diseaseSpinner;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public statisticalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment statisticalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static statisticalFragment newInstance(String param1, String param2) {
        statisticalFragment fragment = new statisticalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_statistical, container, false);
        dbDates = FirebaseDatabase.getInstance().getReference().child("Date");
        chart = (ColumnChartView) rootView.findViewById(R.id.columnChartCorona);
        diseaseSpinner = rootView.findViewById(R.id.graphDiseaseSpinner);
        diseaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                generateData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerFill();



        return rootView;


    }

    private void spinnerFill(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Diseases");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    String name = dataSnapshot1.getValue(String.class);
                    titleList.add(name);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                diseaseSpinner.setAdapter(arrayAdapter);
                generateData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void generateData(){

        final String stDisease = diseaseSpinner.getSelectedItem().toString();

        final List<String> keys = new ArrayList<>();

        final List<AxisValue> valuesOfX = new ArrayList<AxisValue>();
        dbDates.addListenerForSingleValueEvent(new ValueEventListener() {
            int i =0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Column> columns = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    List<SubcolumnValue> values= new ArrayList<SubcolumnValue>();
                    AxisValue temp = new AxisValue(i++);
                    temp.setLabel(ds.getKey());
                    valuesOfX.add(temp);
                    if(ds.child(stDisease).exists()){
                        values.add(new SubcolumnValue(ds.child(stDisease).getValue(Integer.class),ChartUtils.pickColor()));
                    }

                    else {
                        values.add(new SubcolumnValue(0,ChartUtils.pickColor()));
                    }
                    Column column = new Column(values);
                    column.setHasLabels(true);
                    columns.add(column);
//                   Log.e("THE DATA",ds.child("Corona").getValue().toString());
                }
                setValues(columns,valuesOfX);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//
//        values.add(new SubcolumnValue(20, ChartUtils.pickColor()));
//        values.add(new SubcolumnValue(50, ChartUtils.pickColor()));
//        values.add(new SubcolumnValue(20, ChartUtils.pickColor()));

    }

    private void setValues(List<Column> columns,List<AxisValue> valuesOfX ){

        data = new ColumnChartData(columns);

        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Dates");
        axisY.setName("Number of cases");
        axisX.setValues(valuesOfX);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        chart.setColumnChartData(data);
    }

}
