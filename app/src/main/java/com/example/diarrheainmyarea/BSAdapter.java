package com.example.diarrheainmyarea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BSAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String str1[];
    public BSAdapter(Context context,String str1[]){
        this.context=context;
        this.str1=str1;

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
        ((bsdata)holder).tv1.setText(str1[position]);
    }

    @Override
    public int getItemCount() {
        return str1.length;
    }

    public class bsdata extends RecyclerView.ViewHolder {
        TextView tv1;
        public bsdata(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
        }
    }
}
