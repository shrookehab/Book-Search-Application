package com.example.Book.Views;
import com.example.Book.Models.BookDetails;
import com.example.Book.RecyclervView.recyclerViewAdapterBook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Book.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.Book.Views.CategoriesActivity.extraCateg;

public class BooksActivity extends AppCompatActivity implements recyclerViewAdapterBook.onRecyclerClickListener2{
    public static final String extraImg = "image";
    public static final String extraTitle = "title";
    public static final String extraAuthor = "author";
    public static final String extraDesc = "Description";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mRecyclerLayout;
    private ArrayList<String> Desc =  new ArrayList<>();

    recyclerViewAdapterBook adapter;
    ArrayList<BookDetails> itemsOfRecycler;
    //Volley Api
    private RequestQueue rQueue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        TextView categTitle = findViewById(R.id.textView);
        Button favouriteButton = findViewById(R.id.buttonFavourites2);
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(BooksActivity.this, FavouritesActivity.class);
                startActivity(in);
            }
        });




        //Volley Api
        Intent i = getIntent();
        String Catgo = i.getStringExtra(extraCateg);
        categTitle.setText(Catgo);

        if (Catgo.equals("Sport")){
            url ="https://www.googleapis.com/books/v1/volumes?q=Sport&printType=books";
        }
        else if (Catgo.equals("Love")){
            url ="https://www.googleapis.com/books/v1/volumes?q=Love&printType=books";
        }
        else if (Catgo.equals("Life")){
            url ="https://www.googleapis.com/books/v1/volumes?q=Life&printType=books";
        }
        else if (Catgo.equals("Time")){
            url ="https://www.googleapis.com/books/v1/volumes?q=Time&printType=books";
        }


        rQueue = Volley.newRequestQueue(this);
        recyclerItems(url);



        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerLayout = new LinearLayoutManager(this);
        itemsOfRecycler =  new ArrayList<>();

    }

    private void recyclerItems(String URL){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {



                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject items = jsonArray.getJSONObject(i);
                        JSONObject jsonobject3 = items.getJSONObject("volumeInfo");
                        String title = "",description = "",img = "",authorName = "";
                        if (jsonobject3.has("title")){
                            title = jsonobject3.getString("title");

                        }
                        if (jsonobject3.has("authors")) {
                            JSONArray jsonArray3 = jsonobject3.getJSONArray("authors");
                            authorName = jsonArray3.getString(0);
                            for (int k = 1; k < jsonArray3.length(); k++) {

                                authorName.concat(" , " + jsonArray3.getString(k));
                            }
                        }


                        if (jsonobject3.has("imageLinks")) {

                            JSONObject jsonobject4 = jsonobject3.getJSONObject("imageLinks");
                            //bookInfo.setImag(jsonobject4.getString("thumbnail"));
                            if (jsonobject4.has("smallThumbnail")) {
                                img = jsonobject4.getString("smallThumbnail");
                                img.concat(".jpg");
                            }
                        }
                        if (jsonobject3.has("description")) {
                            description = jsonobject3.getString("description");
                            Desc.add(description);
                        }
                        else if (! jsonobject3.has("description")){
                            Desc.add("No Available Description Right Now");
                        }


                        itemsOfRecycler.add(new BookDetails(R.drawable.img,R.drawable.facebook,R.drawable.instagram,R.drawable.twitter,R.drawable.img2, img,title,authorName));
                    }
                    adapter = new recyclerViewAdapterBook(BooksActivity.this);
                    adapter.setItemsList(itemsOfRecycler);
                    mRecyclerAdapter = adapter;
                    mRecyclerView.setLayoutManager(mRecyclerLayout);
                    mRecyclerView.setAdapter(mRecyclerAdapter);
                    adapter.setOnItemClickListener(BooksActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rQueue.add(request);


    }

    @Override
    public void onItemClickRecycler(int position) {
        Intent i = new Intent(BooksActivity.this, BookDescriptionActivity.class);
        BookDetails item = itemsOfRecycler.get(position);
        i.putExtra(extraImg, item.getRimageView());
        i.putExtra(extraTitle,item.getrBookNameTextView());
        i.putExtra(extraAuthor,item.getrAuthorNameTextView());
        i.putExtra(extraDesc,Desc.get(position));
        startActivity(i);

    }
}
