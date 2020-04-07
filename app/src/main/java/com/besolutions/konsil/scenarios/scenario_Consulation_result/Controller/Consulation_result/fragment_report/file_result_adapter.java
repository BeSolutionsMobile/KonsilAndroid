package com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_result.Controller.Consulation_result.fragment_report.model.file_list;

import java.util.ArrayList;

public class file_result_adapter extends RecyclerView.Adapter<file_result_adapter.consulation_result_holder> {
    Context context;
    ArrayList<file_list>mylist;

    public file_result_adapter(Context context, ArrayList<file_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public consulation_result_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.file_item,viewGroup,false);
        consulation_result_holder consulation_result_holder=new consulation_result_holder(view);
        return  consulation_result_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull consulation_result_holder viewHolder, final int i) {
     viewHolder.name.setText(mylist.get(i).getName());

     viewHolder.file_download.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(mylist.get(i).getUrl()));
             context.startActivity(browse);
         }
     });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class consulation_result_holder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout file_download;
        public consulation_result_holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.title);
            file_download = itemView.findViewById(R.id.file_download);
        }
    }
}
