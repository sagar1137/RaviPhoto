package com.example.raviphoto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        imageView=findViewById(R.id.pimgv);

        // get data from intent
        String image=getIntent().getStringExtra("image");

        //set data to view
        Picasso.get().load(image).into(imageView);
    }
}
