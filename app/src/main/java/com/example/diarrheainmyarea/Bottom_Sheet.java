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
    
    TextView title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bottom_sheet,container,false);
        title=view.findViewById(R.id.tv1);
        title.setText(this.getArguments().getString("title"));
        return view;
    }
}
