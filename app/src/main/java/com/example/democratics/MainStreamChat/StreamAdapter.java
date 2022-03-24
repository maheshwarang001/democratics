package com.example.democratics.MainStreamChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.democratics.R;

import java.util.ArrayList;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelInputStream> modelInputStreams;

    public StreamAdapter(Context context, ArrayList<ModelInputStream> modelInputStreams) {
        this.context = context;
        this.modelInputStreams = modelInputStreams;
    }


    @NonNull
    @Override
    public StreamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.post_custom,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamAdapter.ViewHolder holder, int position) {

        position = holder.getAdapterPosition();
        holder.title.setText(modelInputStreams.get(position).getTitle());
        holder.context.setText(modelInputStreams.get(position).getContext());
        holder.date.setText(modelInputStreams.get(position).getDate());

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.like.setColorFilter(v.getContext().getResources().getColor(R.color.butnblue));
                holder.dislike.setColorFilter(v.getContext().getResources().getColor(R.color.black));
            }
        });


        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.dislike.setColorFilter(v.getContext().getResources().getColor(R.color.butnblue));
                holder.like.setColorFilter(v.getContext().getResources().getColor(R.color.black));
            }
        });



        // null image url not resolved

        if(modelInputStreams.get(position).getImageURL() == null){
            holder.image.setVisibility(View.GONE);
        }
        Glide.with(context).
                load(modelInputStreams.get(position).
                        getImageURL()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return modelInputStreams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView context;
        ImageView image;
        TextView date;
        ImageView like;
        ImageView dislike;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_post);
            context = itemView.findViewById(R.id.context_upload);
            image = itemView.findViewById(R.id.image_post);
            date = itemView.findViewById(R.id.date_post);
            like = itemView.findViewById(R.id.positive);
            dislike = itemView.findViewById(R.id.negative);
        }
    }
}
