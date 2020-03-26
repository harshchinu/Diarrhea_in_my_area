package com.example.diarrheainmyarea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BSAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> name = new ArrayList<>();
    List<String> name1 = new ArrayList<>();
    public BSAdapter(Context context, List<String> name,List<String> name1){
        this.context=context;
        this.name=name;
        this.name1=name1;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.bsrecycleritems,parent,false);
        bsdata data=new bsdata(view);
        return data;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((bsdata)holder).tv1.setText(name.get(position)+":");
        ((bsdata)holder).tv2.setText(name1.get(position));
    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    public class bsdata extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        public bsdata(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);

        }
    }
}
