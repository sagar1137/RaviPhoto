package com.example.raviphoto;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home_Frag extends Fragment {
    ListView listView;
    static int images[] = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground,R.drawable.children,R.drawable.male,R.drawable.wedding};
    static String title[] = {"MODEELING", "WEDDING","CHILD","WILDLIFE","TABLE TOP"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_lay_frag, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

       listView=view.findViewById(R.id.listv);
        MyAdapter adapter=new MyAdapter(getActivity(),title,images);
    listView.setAdapter(adapter);


    }

    class MyAdapter extends ArrayAdapter <String>{
        Context context;
        String title[];
        int imgid[];


        public MyAdapter(Context context, String title[], int imgid[]) {
            super(context, R.layout.list_lay,R.id.tv1,title);
            this.context = context;
            this.title=title;
            this.imgid=imgid;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.list_lay,parent,false);
            ImageView imageView=row.findViewById(R.id.imgv1);
            TextView textView=row.findViewById(R.id.tv1);

            imageView.setImageResource(imgid[position]);
            textView.setText(title[position]);
            return row;
        }
    }
}