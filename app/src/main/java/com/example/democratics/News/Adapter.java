package com.example.democratics.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.democratics.ApiDetails.ModelClass;
import com.example.democratics.R;

import java.util.ArrayList;

public class Adapter extends
        RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelArray;

    public Adapter(Context context, ArrayList<ModelClass> modelArray) {
        this.context = context;
        this.modelArray = modelArray;
    }




    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.latest_recycler_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        position = holder.getAdapterPosition();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(modelArray.get(holder.getAdapterPosition()).getUrl()));
                context.startActivity(intent);
            }
        });

        holder.mAuthor.setText(modelArray.get(position).getAuthor());
        holder.mTitle.setText(modelArray.get(position).getTitle());

        Glide.with(context).
                load(modelArray.get(position).
                        getUrlToImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return modelArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthor, mTitle;
        CardView cardView;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.news_author);
            mTitle = itemView.findViewById(R.id.news_title);
            cardView = itemView.findViewById(R.id.background_image);
            image = itemView.findViewById(R.id.news_image);

        }
    }
}
