package com.saipulmuiz.utsmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saipulmuiz.utsmp.adapter.DatabaseAdapter;
import com.saipulmuiz.utsmp.adapter.RiwayatSkorAdapter;
import com.saipulmuiz.utsmp.data.RiwayatSkor;

import java.util.ArrayList;
import java.util.List;

public class RiwayatSkorActivity extends AppCompatActivity {
    ImageButton btnBack;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RiwayatSkorAdapter adapter;
    private DatabaseAdapter databaseAdapter;
    private TextView txt_resultadapter;
    private List<RiwayatSkor> riwayatSkorList = new ArrayList<>();
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_skor);
        setupAnimation();
        btnBack = findViewById(R.id.btn_back);
        mediaPlayer = MediaPlayer.create(this, R.raw.btn_click);
        mediaPlayer.setLooping(false);
        btnBack.setOnClickListener(view -> {
            mediaPlayer.start();
            finish();
        });
        txt_resultadapter = findViewById(R.id.txt_resultadapter);
        initRecyclerView();
        cekDataRecyclerView();
    }

    private void setupAnimation() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rv_riwayat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        databaseAdapter = new DatabaseAdapter(RiwayatSkorActivity.this);
        riwayatSkorList = databaseAdapter.getAllSkor();
        adapter = new RiwayatSkorAdapter(riwayatSkorList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void cekDataRecyclerView() {
        if (adapter.getItemCount() == 0) {
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}