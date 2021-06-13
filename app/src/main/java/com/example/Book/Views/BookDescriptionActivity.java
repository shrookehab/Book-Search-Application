package com.example.Book.Views;

import com.example.Book.Database.DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Book.R;
import com.squareup.picasso.Picasso;

import static com.example.Book.Views.BooksActivity.extraAuthor;
import static com.example.Book.Views.BooksActivity.extraDesc;
import static com.example.Book.Views.BooksActivity.extraImg;
import static com.example.Book.Views.BooksActivity.extraTitle;

public class BookDescriptionActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);

        intent = getIntent();
         ImageView imgBook = findViewById(R.id.imageViewBookPhoto);
         TextView bookName = findViewById(R.id.textViewBookName);
         TextView Descrip = findViewById(R.id.textVieweDscription);
         TextView author = findViewById(R.id.textViewAuthorName);
         final ImageButton addToFav =  findViewById(R.id.ImageButtonAddToFav);

        Picasso.with(this).load(intent.getStringExtra(extraImg)).fit().centerInside().into(imgBook);
        bookName.setText(intent.getStringExtra(extraTitle));
        Descrip.setText(intent.getStringExtra(extraDesc));
        author.setText(intent.getStringExtra(extraAuthor));
        databaseHelper = new DatabaseHelper(this);


        boolean checkIfDataExists = databaseHelper.checkIfExists(intent.getStringExtra(extraTitle));
        if (checkIfDataExists){
            addToFav.setImageResource(R.drawable.img1);
            addToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Already in Favourites", Toast.LENGTH_SHORT).show();
                }
            });

        }

        else if(! checkIfDataExists) {
            addToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addToFav.setImageResource(R.drawable.img1);
                    addData(intent.getStringExtra(extraTitle), intent.getStringExtra(extraImg), intent.getStringExtra(extraAuthor), intent.getStringExtra(extraDesc));
                }
            });
        }

    }

    private void addData (String title, String img, String author, String description){

            boolean insertedData = databaseHelper.addItems(title, img, author, description);

            if (insertedData) {
                Toast.makeText(this, "Added To Favourites ❤", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(this, "Failed to Inserte", Toast.LENGTH_SHORT).show();
            }

    }
}
