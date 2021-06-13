package com.example.Book.Views;
import com.example.Book.Database.DatabaseHelper;
import com.example.Book.Models.BookDetails;
import com.example.Book.R;

import com.example.Book.RecyclervView.recyclerViewAdapterBook;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import static com.example.Book.Views.BooksActivity.extraAuthor;
import static com.example.Book.Views.BooksActivity.extraDesc;
import static com.example.Book.Views.BooksActivity.extraImg;
import static com.example.Book.Views.BooksActivity.extraTitle;

public class FavouritesActivity extends AppCompatActivity implements recyclerViewAdapterBook.onRecyclerClickListener2{
    private RecyclerView mRecyclerViewFav;
    private RecyclerView.Adapter mRecyclerAdapterFav;
    private RecyclerView.LayoutManager mRecyclerLayoutFav;
    private ArrayList<String> Desc;
    recyclerViewAdapterBook adapter ;
    ArrayList<BookDetails> recyclerItems;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

         recyclerItems =  new ArrayList<>();


        mRecyclerViewFav = findViewById(R.id.recyclerView);
        mRecyclerViewFav.setHasFixedSize(true);
        mRecyclerLayoutFav = new LinearLayoutManager(this);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getData();
        getRecyclerItems();

    }

    @Override
    public void onItemClickRecycler(int position) {
        Intent i = new Intent(FavouritesActivity.this, BookDescriptionActivity.class);
        BookDetails item = recyclerItems.get(position);
        Desc = databaseHelper.getDes();
        i.putExtra(extraImg, item.getRimageView());
        i.putExtra(extraTitle,item.getrBookNameTextView());
        i.putExtra(extraAuthor,item.getrAuthorNameTextView());
        i.putExtra(extraDesc,Desc.get(position));
        startActivity(i);
    }

    private void getRecyclerItems(){


        ArrayList<BookDetails> items = databaseHelper.getItems();

        recyclerItems = items;
        adapter = new recyclerViewAdapterBook(this);
        adapter.setItemsList(recyclerItems);
        mRecyclerAdapterFav = adapter;
        mRecyclerViewFav.setLayoutManager(mRecyclerLayoutFav);
        mRecyclerViewFav.setAdapter(mRecyclerAdapterFav);
        adapter.setOnItemClickListener(FavouritesActivity.this);
    }


}
