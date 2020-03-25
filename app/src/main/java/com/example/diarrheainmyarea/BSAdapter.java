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
    String str1[],str2[],str3[];
    public BSAdapter(Context context,String str1[],String str2[],String str3[]){
        this.context=context;
        this.str1=str1;
        this.str2=str2;
        this.str3=str3;
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
        ((bsdata)holder).title.setText(str1[position]);
        ((bsdata)holder).areacount.setText(str2[position]);
        ((bsdata)holder).citycount.setText(str3[position]);
    }

    @Override
    public int getItemCount() {
        return str1.length;
    }

    public class bsdata extends RecyclerView.ViewHolder {
        TextView title,areacount,citycount;
        public bsdata(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.areaname);
            areacount=itemView.findViewById(R.id.areacount);
            citycount=itemView.findViewById(R.id.citycount);
        }
    }
}
