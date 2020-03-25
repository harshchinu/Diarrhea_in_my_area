package com.example.diarrheainmyarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_Sheet extends BottomSheetDialogFragment {
    
    TextView title,areacount,citycount;
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
        return view;
    }
}
