package com.besolutions.konsil.Consulation_result.fragment_report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.besolutions.konsil.Consulation_result.fragment_message.consulation_list;
import com.besolutions.konsil.R;

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
    public void onBindViewHolder(@NonNull consulation_result_holder viewHolder, int i) {
     viewHolder.name.setText(mylist.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class consulation_result_holder extends RecyclerView.ViewHolder {
        TextView name;
        public consulation_result_holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.title);
        }
    }
}
