package com.saipulmuiz.utsmp.data;

public class RiwayatSkor {
    private int id;
    private String nama;
    private int skor;

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public RiwayatSkor(String nama, int skor) {
        this.nama = nama;
        this.skor = skor;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getSkor() {
        return skor;
    }
}
