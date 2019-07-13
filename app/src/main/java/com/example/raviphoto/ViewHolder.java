package com.example.raviphoto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView=itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclickListener.onItemClick(v,getAdapterPosition());
            }
        });

    }
    //set detail to recycler view row
    public void setImage(Context c,String image)
    {
        ImageView imageView=mView.findViewById(R.id.imgv);
        Picasso.get().load(image).into(imageView);
    }

    private ViewHolder.ClickListener mclickListener;
//inetface to send call backs
    public interface ClickListener
    {
        void onItemClick(View view,int position);

    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mclickListener=clickListener;
    }
}
