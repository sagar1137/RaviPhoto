package com.example.raviphoto;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WildLife_Activity extends AppCompatActivity {
    RecyclerView recyclerView;

    ProgressDialog progressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wild_life_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        //set layout as linear layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Wild_data");
    }

    //load data into recycler view  onstart

    @Override
    protected void onStart() {
        super.onStart();

        progressBar=new ProgressDialog(WildLife_Activity.this);
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

        FirebaseRecyclerAdapter<Model,ViewHolder> modelViewHolderFirebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(Model.class,R.layout.row,ViewHolder.class,reference) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setImage(getApplicationContext(),model.getImage());
                    }
                };

        //set adapter to recylcerview
        recyclerView.setAdapter(modelViewHolderFirebaseRecyclerAdapter);

    }
}
