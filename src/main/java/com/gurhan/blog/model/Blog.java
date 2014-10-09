package com.gurhan.blog.model;

import java.util.ArrayList;
import java.util.Date;

public class Blog {

    private int blogID;
    private int yazarID;
    private String baslik;
    private String icerik;
    private Date tarih;
    private ArrayList<Yorum> yorumlar;

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getYazarID() {
        return yazarID;
    }

    public void setYazarID(int yazarID) {
        this.yazarID = yazarID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public ArrayList<Yorum> getYorumlar() {
        return yorumlar;
    }

    public void setYorumlar(ArrayList<Yorum> yorumlar) {
        this.yorumlar = yorumlar;
    }

}
