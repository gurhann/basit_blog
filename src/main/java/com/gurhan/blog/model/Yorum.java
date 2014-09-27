package com.gurhan.blog.model;

import java.util.Date;

public class Yorum {
	private int yorumID;
	private int blogID;
	private int YazarID;
	private String icerik;
	private Date tarih;
	
	public int getYorumID() {
		return yorumID;
	}
	public void setYorumID(int yorumID) {
		this.yorumID = yorumID;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}
	public int getYazarID() {
		return YazarID;
	}
	public void setYazarID(int yazarID) {
		YazarID = yazarID;
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
}
