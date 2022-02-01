package com.example.democratics.MyMinisters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.democratics.MyMinisters.ApiDet.Mps;
import com.example.democratics.R;

import java.util.ArrayList;


public class MinAdapter extends RecyclerView.Adapter<MinAdapter.ViewHolder> {

    Context context;
    ArrayList<Mps> arrayListMps;

    public MinAdapter(Context context, ArrayList<Mps> arrayListmps) {
        this.context = context;
        this.arrayListMps = arrayListmps;
    }


    @NonNull
    @Override
    public MinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).
                inflate(R.layout.member_of_parliament_view, null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MinAdapter.ViewHolder holder, int position) {

        position = holder.getAdapterPosition();

        holder.mState.setText(arrayListMps.get(position).getState());
        holder.mName.setText(arrayListMps.get(position).getName());
        holder.mParty.setText(arrayListMps.get(position).getParty());
        holder.mCase.setText(""+(arrayListMps.get(position).getCriminalCases()));
        holder.mConst.setText(arrayListMps.get(position).getConstituency());
        holder.mEdu.setText(arrayListMps.get(position).getEducation());
       holder.mAssets.setText(""+arrayListMps.get(position).getAssets());


    }

    @Override
    public int getItemCount() {
        return arrayListMps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mState, mName, mParty, mCase, mEdu, mAssets, mConst;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mState = itemView.findViewById(R.id.state_view);
            mName = itemView.findViewById(R.id.name_view);
            mParty = itemView.findViewById(R.id.party_view);
            mCase = itemView.findViewById(R.id.case_view);
            mEdu = itemView.findViewById(R.id.education_view);
            mAssets = itemView.findViewById(R.id.assets_view);
            mConst = itemView.findViewById(R.id.constituency_view);


        }
    }
}
