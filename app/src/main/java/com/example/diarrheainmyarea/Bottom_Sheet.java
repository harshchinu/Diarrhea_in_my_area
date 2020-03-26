package com.example.diarrheainmyarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bottom_Sheet extends BottomSheetDialogFragment {


    TextView title,areacount,citycount;
    RecyclerView recyclerView;
    HashMap<String,String> dieases;
    List<String> ab,ac;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet,container,false);
        title=view.findViewById(R.id.areaname);
        areacount=view.findViewById(R.id.areacount);
        citycount=view.findViewById(R.id.citycount);
        title.setText("Area Name :" +this.getArguments().getString("title"));
        areacount.setText("Area Count :"+this.getArguments().getString("numberofcasesinarea"));
        citycount.setText("City Count :"+this.getArguments().getString("totalcount"));

        ab=(ArrayList<String>)getArguments().getSerializable("map1");
        ac =(ArrayList<String>)getArguments().getSerializable("map2");


            recyclerView=view.findViewById(R.id.bsrecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setAdapter(new BSAdapter(this.getContext(),ab,ac));

        return view;
    }
}
