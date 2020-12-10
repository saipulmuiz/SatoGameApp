package com.saipulmuiz.utsmp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saipulmuiz.utsmp.R;

public class SkorDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public TextView tvSkor, tvNama;
    public ImageButton btnOk;
    public String s, n;
    MediaPlayer mediaPlayer;

    public SkorDialog(Activity a, String skor, String nama) {
        super(a);
        this.c = a;
        this.s = skor;
        this.n = nama;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_skor);
        btnOk = findViewById(R.id.btn_finish);
        btnOk.setOnClickListener(this);
        tvSkor = findViewById(R.id.item_tvSkor);
        tvNama = findViewById(R.id.tv_name);

        tvNama.setText(n);
        tvSkor.setText(s);

    }

    @Override
    public void onClick(View v) {
        mediaPlayer = MediaPlayer.create(c, R.raw.btn_click);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
        if (v.getId() == R.id.btn_finish) {
            c.finish();
        }
    }
}