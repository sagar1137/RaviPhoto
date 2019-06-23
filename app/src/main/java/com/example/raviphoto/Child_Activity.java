package com.example.raviphoto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Child_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog progressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        //set layout as linear layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebase

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Child_data");


        progressBar=new ProgressDialog(Child_Activity.this);
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
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
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
                            }

                        });

                        return viewHolder;
                    }
                };

        //set adapter to recylcerview
        recyclerView.setAdapter(modelViewHolderFirebaseRecyclerAdapter);

    }
}
