package com.example.raviphoto;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splash_Screen extends AppCompatActivity {
   // FirebaseDatabase firebaseDatabase;
   // DatabaseReference reference;
    ImageView imageView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_splash__screen);

     //   firebaseDatabase= FirebaseDatabase.getInstance();
     //   reference=firebaseDatabase.getReference("Data");
        //changing status bar
        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
        //up to this status bar
        imageView=findViewById(R.id.imageView2);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash_Screen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
