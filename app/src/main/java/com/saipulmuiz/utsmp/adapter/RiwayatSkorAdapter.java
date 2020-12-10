package com.saipulmuiz.utsmp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saipulmuiz.utsmp.R;
import com.saipulmuiz.utsmp.data.RiwayatSkor;

import java.util.ArrayList;
import java.util.List;

public class RiwayatSkorAdapter extends RecyclerView.Adapter<RiwayatSkorAdapter.RiwayatSkorViewHolder> {
    private List<RiwayatSkor> riwayatSkorList = new ArrayList<>();

    public RiwayatSkorAdapter(List<RiwayatSkor> riwayatSkorList) {
        this.riwayatSkorList = riwayatSkorList;
    }

    @Override
    public RiwayatSkorAdapter.RiwayatSkorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_skor, parent, false);
        return new RiwayatSkorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RiwayatSkorAdapter.RiwayatSkorViewHolder holder, int position) {
        holder.resultNama.setText(riwayatSkorList.get(position).getNama());
        holder.resultSkor.setText(String.valueOf(riwayatSkorList.get(position).getSkor()));
    }

    @Override
    public int getItemCount() {
        return riwayatSkorList.size();
    }

    public static class RiwayatSkorViewHolder extends RecyclerView.ViewHolder {

        TextView resultNama;
        TextView resultSkor;

        public RiwayatSkorViewHolder(View itemView) {
            super(itemView);

            resultNama = itemView.findViewById(R.id.item_nama);
            resultSkor = itemView.findViewById(R.id.item_skor);
        }
    }
}
