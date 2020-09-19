package com.herya.model;


import javax.persistence.*;

@Entity
@Table(name = "api-namasiswa")
public class DataNama {
    private int no;
    private String nama;
    private String kelas;
    private String no_absen;

    public DataNama(){

    }

    public DataNama(String nama, String kelas, String no_absen) {
        this.nama = nama;
        this.kelas = kelas;
        this.no_absen = no_absen;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


    @Column(name = "nama", nullable = false)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    @Column(name = "kelas", nullable = false)
    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    @Column(name = "no_absen", nullable = false)
    public String getNo_absen() {
        return no_absen;
    }

    public void setNo_absen(String no_absen) {
        this.no_absen = no_absen;
    }
    @Override
    public String toString() {
        return "DataNama{" +
                "no=" + no +
                ", nama='" + nama + '\'' +
                ", kelas='" + kelas + '\'' +
                ", no_absen='" + no_absen + '\'' +
                '}';
    }



}
