package com.example.Book.RecyclervView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Book.Models.categoryDetails;
import com.example.Book.R;

import java.util.ArrayList;


public class recyclerViewAdapterCateg extends RecyclerView.Adapter<recyclerViewAdapterCateg.recyclerViewHolder> {
    private ArrayList<categoryDetails> itemsList = new ArrayList<>();
    private onRecyclerClickListener clickItem;
    Context context;
    public interface onRecyclerClickListener {

        void onItemClickRecycler(int position);
    }
    public void setOnItemClickListener2(onRecyclerClickListener listener){
        clickItem = listener;

    }


    public recyclerViewAdapterCateg(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new recyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categoriesitems,parent,false));
    }

    //items will set in each list in recyclerview
    @Override
    public void onBindViewHolder(@NonNull final recyclerViewHolder holder, final int position) {
        holder.categImgView.setImageResource(itemsList.get(position).getCategImgView());
        holder.categText.setText(itemsList.get(position).getCategText());


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void setItemsList(ArrayList<categoryDetails> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();// to refresh the whole list
    }

    public class recyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView categImgView;
        TextView categText;
        onRecyclerClickListener clickedItem;
        public recyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categImgView = itemView.findViewById(R.id.categimageView);
            categText = itemView.findViewById(R.id.categtextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItem != null){
                        int position = getAdapterPosition();
                        if ( position != RecyclerView.NO_POSITION){
                            clickItem.onItemClickRecycler(position);
                        }
                    }
                }
            });
        }



    }

}
