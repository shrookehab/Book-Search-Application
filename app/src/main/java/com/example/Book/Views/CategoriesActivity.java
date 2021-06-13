package com.example.Book.Views;
import com.example.Book.RecyclervView.recyclerViewAdapterCateg;
import com.example.Book.Models.categoryDetails;
import com.example.Book.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements recyclerViewAdapterCateg.onRecyclerClickListener {

    public static final String extraCateg = "Category";
    private RecyclerView mRecyclerViewCat;
    private RecyclerView.Adapter mRecyclerAdapterCat;
    private RecyclerView.LayoutManager mRecyclerLayoutCat;
    ArrayList<categoryDetails> recyclerItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Button favButton = findViewById(R.id.buttonFavourites);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoriesActivity.this, FavouritesActivity.class);
                startActivity(i);
            }
        });

        recyclerItems = new ArrayList<>();
        recyclerItems.add(new categoryDetails(R.drawable.life, "Life"));
        recyclerItems.add(new categoryDetails(R.drawable.love, "Love"));
        recyclerItems.add(new categoryDetails(R.drawable.time, "Time"));
        recyclerItems.add(new categoryDetails(R.drawable.sport, "Sport"));

        mRecyclerViewCat = findViewById(R.id.categrecyclerView);
        mRecyclerViewCat.setHasFixedSize(true);
        mRecyclerLayoutCat = new LinearLayoutManager(this);
        recyclerViewAdapterCateg adapter = new recyclerViewAdapterCateg(this);
        adapter.setItemsList(recyclerItems);
        mRecyclerAdapterCat = adapter;
        mRecyclerViewCat.setLayoutManager(mRecyclerLayoutCat);
        mRecyclerViewCat.setAdapter(mRecyclerAdapterCat);
        adapter.setOnItemClickListener2(CategoriesActivity.this);

    }

    @Override
    public void onItemClickRecycler(int position) {
        Intent i = new Intent(CategoriesActivity.this, BooksActivity.class);
        categoryDetails item = recyclerItems.get(position);
        i.putExtra(extraCateg, item.getCategText());
        startActivity(i);
    }
}
