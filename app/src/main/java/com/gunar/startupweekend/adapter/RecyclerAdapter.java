package com.gunar.startupweekend.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunar.startupweekend.R;
import com.gunar.startupweekend.model.HomeResponse;

import java.util.HashMap;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {






    private List<HomeResponse> soliList;
    private static ClickListener clickListener;
    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        public TextView nombre;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            nombre = (TextView) view.findViewById(R.id.nombre);
            img = (ImageView) view.findViewById(R.id.link);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public RecyclerAdapter (List<HomeResponse> myDataset) {
        soliList = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeResponse soli = soliList.get(position);

        holder.nombre.setText(soli.getNombre());

//        String base64Image = soli.getLink().split(",")[1];

        byte[] decodedString = Base64.decode(soli.getLink(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.img.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemCount() {
        return soliList.size();
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }


}
