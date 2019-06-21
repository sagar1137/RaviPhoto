package com.example.raviphoto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    Fragment fragment = null;
    static int images[] = {R.drawable.male, R.drawable.wedding, R.drawable.children, R.drawable.wildlife, R.drawable.tabletop};
    static String title[] = {"MODELLING", "WEDDING", "CHILD", "WILDLIFE", "TABLE TOP"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_lay_frag, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView = view.findViewById(R.id.listv);
        MyAdapter adapter = new MyAdapter(getActivity(), title, images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                   Intent intent=new Intent(getContext(),Modelling_Activity.class);
                   startActivity(intent);
                }
                if (position == 1) {
                    Intent intent=new Intent(getContext(),Wedding_Activity.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent=new Intent(getContext(),Child_Activity.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent=new Intent(getContext(),WildLife_Activity.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent=new Intent(getContext(),Table_Activity.class);
                    startActivity(intent);
                }
            }
        });


    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String title[];
        int imgid[];


        public MyAdapter(Context context, String title[], int imgid[]) {
            super(context, R.layout.list_lay, R.id.tv1, title);
            this.context = context;
            this.title = title;
            this.imgid = imgid;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_lay, parent, false);
            ImageView imageView = row.findViewById(R.id.imgv1);
            TextView textView = row.findViewById(R.id.tv1);

            imageView.setImageResource(imgid[position]);
            textView.setText(title[position]);
            return row;
        }
    }
}
