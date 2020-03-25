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

public class Bottom_Sheet extends BottomSheetDialogFragment {
    
    String item[]={"item1","item2","item3","item4"};
    TextView title,areacount,citycount;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet,container,false);
        title=view.findViewById(R.id.areaname);
        areacount=view.findViewById(R.id.areacount);
        citycount=view.findViewById(R.id.citycount);
        title.setText(this.getArguments().getString("title"));
        areacount.setText(this.getArguments().getString("numberofcasesinarea"));
        citycount.setText(this.getArguments().getString("date"));
            recyclerView=view.findViewById(R.id.bsrecycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setAdapter(new BSAdapter(this.getContext(),item));

        return view;
    }
}
