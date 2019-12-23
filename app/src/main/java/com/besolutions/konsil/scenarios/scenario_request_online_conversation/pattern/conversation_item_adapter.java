package com.besolutions.konsil.scenarios.scenario_request_online_conversation.pattern;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_request_online_conversation.model.conversation_reserv_list;

import java.util.ArrayList;

public class conversation_item_adapter extends RecyclerView.Adapter<conversation_item_adapter.conversation_holder> {
 Context context;
 ArrayList<conversation_reserv_list> mylist;

    public conversation_item_adapter(Context context, ArrayList<conversation_reserv_list> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public conversation_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.converstaion_item,viewGroup,false);
        conversation_holder conversation_holder=new conversation_holder(view);
        return conversation_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final conversation_holder viewHolder, final int position) {

       viewHolder.radio_txt.setText(mylist.get(position).getResev_txt());
       viewHolder.radio_txt.setChecked(mylist.get(position).getStatus());

       viewHolder.radio_txt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               for (int index=0;index<mylist.size();index++)
               {

                   if(index!=position)
                   {
                       Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                       mylist.get(index).setStatus(false);
                       viewHolder.radio_txt.setChecked(mylist.get(index).getStatus());
                   }else
                       {
                           Toast.makeText(context, "ccczz"+position, Toast.LENGTH_SHORT).show();

                           mylist.get(index).setStatus(true);
                           viewHolder.radio_txt.setChecked(mylist.get(index).getStatus());
                       }
               }


               /*mylist.get(i).setStatus(true);
               for (int index=0;index<mylist.size();index++)
               {

                   if(index!=i)
                   {
                       mylist.get(index).setStatus(false);
                       viewHolder.radio_txt.setChecked(false);
                   }
                   viewHolder.radio_txt.setChecked(true);

               }*/


           }
       });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class  conversation_holder extends RecyclerView.ViewHolder {
        RadioButton radio_txt;
        public conversation_holder(@NonNull View itemView) {
            super(itemView);
            radio_txt=(RadioButton)itemView.findViewById(R.id.radio_item);
        }
    }
}
