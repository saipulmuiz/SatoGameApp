package com.saipulmuiz.utsmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.saipulmuiz.utsmp.adapter.DatabaseAdapter;
import com.saipulmuiz.utsmp.adapter.RiwayatSkorAdapter;
import com.saipulmuiz.utsmp.data.RiwayatSkor;
import com.saipulmuiz.utsmp.data.SoalPg;
import com.saipulmuiz.utsmp.dialog.ExitDialog;
import com.saipulmuiz.utsmp.dialog.SkorDialog;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    TextView tvSkor, tvNomorSoal;
    RadioGroup rgPilihanJawaban;
    RadioButton rbPilihanJawaban1, rbPilihanJawaban2, rbPilihanJawaban3;
    ImageView imgSoal;
    ImageButton btnSubmit, btnSound;
    NestedScrollView nestedScrollView;
    MediaPlayer mediaPlayer, soundSalah, soundBenar, soundWinner;
    int skor = 0;
    int arr;
    int x;
    String jawaban;
    SoalPg soalPG = new SoalPg();
    String nama;
    private DatabaseAdapter databaseAdapter;
    private RiwayatSkorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setupAnimation();
        initComponents();
        nama = getIntent().getStringExtra("NAMA");
        databaseAdapter = new DatabaseAdapter(this);

        tvSkor.setText("" + skor);
        tvNomorSoal.setText("Soal No. 1");
        setKonten();

        btnSubmit.setOnClickListener(view -> {
            mediaPlayer.start();
            cekJawaban();
        });
    }

    private void initComponents() {
        tvSkor = findViewById(R.id.tvSkor);
        imgSoal = findViewById(R.id.img_soal);
        rgPilihanJawaban = findViewById(R.id.rgPilihanJawaban);
        rbPilihanJawaban1 = findViewById(R.id.rbPilihanJawaban1);
        rbPilihanJawaban2 = findViewById(R.id.rbPilihanJawaban2);
        rbPilihanJawaban3 = findViewById(R.id.rbPilihanJawaban3);
        btnSubmit = findViewById(R.id.btn_next);
        btnSound = findViewById(R.id.btn_sound);
        tvNomorSoal = findViewById(R.id.tv_nomorSoal);
        nestedScrollView = findViewById(R.id.nestedScrollGame);
        mediaPlayer = MediaPlayer.create(this, R.raw.btn_click);
        mediaPlayer.setLooping(false);
        soundBenar = MediaPlayer.create(this, R.raw.jawaban_true);
        soundBenar.setLooping(false);
        soundSalah = MediaPlayer.create(this, R.raw.salah);
        soundSalah.setLooping(false);
        soundWinner = MediaPlayer.create(this, R.raw.winner);
        soundWinner.setLooping(false);
    }

    private void setupAnimation() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void setKonten() {
        nestedScrollView.smoothScrollTo(0, 0);
        rgPilihanJawaban.clearCheck();
        arr = soalPG.pertanyaan.length;
        if (x >= arr) {
            String jumlahSkor = String.valueOf(skor);
            SkorDialog skorDialog = new SkorDialog(this, jumlahSkor, nama);
            skorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            skorDialog.show();
            simpanSkor();
            skorDialog.setOnKeyListener((arg0, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return true;
            });
            Window window = skorDialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            soundWinner.start();
        } else {
            imgSoal.setImageResource(soalPG.getPertanyaan(x));
            rbPilihanJawaban1.setText(soalPG.getPilihanJawaban1(x));
            rbPilihanJawaban2.setText(soalPG.getPilihanJawaban2(x));
            rbPilihanJawaban3.setText(soalPG.getPilihanJawaban3(x));
            jawaban = soalPG.getJawabanBenar(x);

        }
        tvNomorSoal.setText("Soal No. " + (x + 1));
        x++;
    }

    public void cekJawaban() {
        if (rbPilihanJawaban1.isChecked()) {
            if (rbPilihanJawaban1.getText().toString().equals(jawaban)) {
                skor = skor + 20;
                tvSkor.setText("" + skor);
                jawabanBenar();
                setKonten();
            } else {
                tvSkor.setText("" + skor);
                jawabanSalah();
                setKonten();
            }
        } else if (rbPilihanJawaban2.isChecked()) {
            if (rbPilihanJawaban2.getText().toString().equals(jawaban)) {
                skor = skor + 20;
                tvSkor.setText("" + skor);
                jawabanBenar();
                setKonten();
            } else {
                tvSkor.setText("" + skor);
                jawabanSalah();
                setKonten();
            }
        } else if (rbPilihanJawaban3.isChecked()) {
            if (rbPilihanJawaban3.getText().toString().equals(jawaban)) {
                skor = skor + 20;
                tvSkor.setText("" + skor);
                jawabanBenar();
                setKonten();
            } else {
                tvSkor.setText("" + skor);
                jawabanSalah();
                setKonten();
            }
        } else {
			nestedScrollView.smoothScrollTo(0, nestedScrollView.getHeight());
            Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }
    }

    private void jawabanBenar() {
        soundBenar.start();
        Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
    }

    private void jawabanSalah() {
        soundSalah.start();
        Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
    }

    public void simpanSkor() {
        databaseAdapter.insertData(new RiwayatSkor(nama, skor));
        List<RiwayatSkor> riwayatSkorList = databaseAdapter.getAllSkor();
        adapter = new RiwayatSkorAdapter(riwayatSkorList);
        adapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog(this);
        exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        exitDialog.show();
        Window window = exitDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
}