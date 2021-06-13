package com.example.Book.RecyclervView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Book.Models.BookDetails;
import com.example.Book.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recyclerViewAdapterBook extends RecyclerView.Adapter<recyclerViewAdapterBook.recyclerViewHolder> {

    Context context;

    public recyclerViewAdapterBook(Context context) {
        this.context = context;
    }

    private ArrayList<BookDetails> itemsList = new ArrayList<>();
    private onRecyclerClickListener2 clickItem;

    public interface onRecyclerClickListener2 {

        void onItemClickRecycler(int position);
    }
    public void setOnItemClickListener(onRecyclerClickListener2 listener){
        clickItem = listener;

    }

    @NonNull
    @Override
    public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new recyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.books,parent,false));
    }

    //items will set in each list in recyclerview
    @Override
    public void onBindViewHolder(@NonNull final recyclerViewHolder holder, final int position) {
        holder.likeButton.setImageResource(itemsList.get(position).getrLikeButton());
        holder.faceButton.setImageResource(itemsList.get(position).getRfaceButton());
        holder.instaButton.setImageResource(itemsList.get(position).getRinstaButton());
        holder.tweetButton.setImageResource(itemsList.get(position).getRtweetButton());
        holder.shareButton.setImageResource(itemsList.get(position).getRshareButton());
        Picasso.with(this.context).load(itemsList.get(position).getRimageView()).fit().centerInside().into(holder.imgView);
        holder.BookNameTextView.setText(itemsList.get(position).getrBookNameTextView());
        holder.AuthorNameTextView.setText(itemsList.get(position).getrAuthorNameTextView());


        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    holder.likeButton.setImageResource(R.drawable.img1);
                    Toast.makeText(context, "Added To FavouritesActivity ❤", Toast.LENGTH_SHORT).show();
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void setItemsList(ArrayList<BookDetails> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();// to refresh the whole list
    }


    public class recyclerViewHolder extends RecyclerView.ViewHolder {

        ImageButton likeButton;
        ImageButton shareButton;
        ImageView imgView;
        ImageButton faceButton;
        ImageButton instaButton;
        ImageButton tweetButton;
        TextView BookNameTextView;
        TextView AuthorNameTextView;


        public recyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            likeButton = itemView.findViewById(R.id.likeImageButton);
            faceButton = itemView.findViewById(R.id.facebookImageButton);
            instaButton = itemView.findViewById(R.id.instaImageButton);
            tweetButton = itemView.findViewById(R.id.twitterImageButton);
            shareButton = itemView.findViewById(R.id.shareImageButton);
            imgView = itemView.findViewById(R.id.imageView);
            BookNameTextView = itemView.findViewById(R.id.BookNameTextView);
            AuthorNameTextView = itemView.findViewById(R.id.AuthorNameTextView);

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
