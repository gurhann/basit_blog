package com.gurhan.blog.model;

import java.util.ArrayList;
import java.util.Date;

public class Kullanici {

    private int kullaniciID;
    private String kullaniciAdi;
    private String sifre;
    private Date kayitTarihi;
    private ArrayList<Blog> bloglar;

    public int getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public Date getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(Date kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public ArrayList<Blog> getBloglar() {
        return bloglar;
    }

    public void setBloglar(ArrayList<Blog> bloglar) {
        this.bloglar = bloglar;
    }

}
