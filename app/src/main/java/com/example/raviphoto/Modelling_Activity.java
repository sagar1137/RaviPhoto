package com.example.raviphoto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class Modelling_Activity extends AppCompatActivity {
    protected ProgressDialog progressBar;

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelling_);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        //set layout as linear layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase

       firebaseDatabase=FirebaseDatabase.getInstance();
       reference=firebaseDatabase.getReference("Data");

        AlertDialog alertDialog;
        progressBar = new ProgressDialog(Modelling_Activity.this);
        progressBar.setTitle("Loading");
        progressBar.setMessage("Please wait,while we Load new Images For you :)");
        progressBar.show();
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressBar.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);



    }

    //load data into recycler view  onstart

    @Override
    protected void onStart() {
        super.onStart();



        FirebaseRecyclerAdapter<Model,ViewHolder> modelViewHolderFirebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class,R.layout.row,ViewHolder.class,reference) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                       viewHolder.setImage(getApplicationContext(),model.getImage());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
                    {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                               String mImage=getItem(position).getImage();

                               //pass this data to new activity
                                Intent intent=new Intent(view.getContext(),PostDetailActivity.class);
                                intent.putExtra("image",mImage);
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                            }

                        });

                        return viewHolder;
                    }
                };

        //set adapter to recylcerview
        recyclerView.setAdapter(modelViewHolderFirebaseRecyclerAdapter);

    }
}
