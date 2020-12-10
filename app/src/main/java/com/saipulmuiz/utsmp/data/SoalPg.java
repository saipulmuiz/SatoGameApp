package com.saipulmuiz.utsmp.data;

import com.saipulmuiz.utsmp.R;

public class SoalPg {

    public Integer pertanyaan[] = {
            R.drawable.img_harimau,
            R.drawable.img_kuda,
            R.drawable.img_lembu,
            R.drawable.img_monyet,
            R.drawable.img_singa,
    };

    private String pilihanJawaban[][] = {
            {"Harimau", "Lembu", "Moyet"},
            {"Singa", "Harimau", "Kuda"},
            {"Kuda", "Lembu", "Harimau"},
            {"Monyet", "Singa", "Harimau"},
            {"Harimau", "Monyet", "Singa"},
    };

    private String jawabanBenar[] = {
            "Harimau",
            "Kuda",
            "Lembu",
            "Monyet",
            "Singa",
    };

    public Integer getPertanyaan(int x) {
        Integer soal = pertanyaan[x];
        return soal;
    }

    public String getPilihanJawaban1(int x) {
        String jawaban1 = pilihanJawaban[x][0];
        return jawaban1;
    }

    public String getPilihanJawaban2(int x) {
        String jawaban2 = pilihanJawaban[x][1];
        return jawaban2;
    }

    public String getPilihanJawaban3(int x) {
        String jawaban3 = pilihanJawaban[x][2];
        return jawaban3;
    }

    public String getJawabanBenar(int x) {
        String jawaban = jawabanBenar[x];
        return jawaban;
    }
}
