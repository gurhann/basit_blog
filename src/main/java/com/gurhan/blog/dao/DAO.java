package com.gurhan.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gurhan.blog.model.Blog;
import com.gurhan.blog.model.Kullanici;
import com.gurhan.blog.model.Yorum;

public class DAO {
	private Connection connection;
	private PreparedStatement psmt;
	private ResultSet rs;
	public DAO(String dbKullaniciAdi, String dbSifre, String dbPath) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(dbPath, dbKullaniciAdi, dbSifre);
	}
	public Kullanici getUser(String kullanciAdi, String sifre) throws SQLException {
		psmt = null;
		rs = null;
		Kullanici kullanici = new Kullanici();
		String sql = "SELECT * FROM kullanici WHERE kullaniciAdi = ? and sifre = ?";
		try{
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, kullanciAdi);
			psmt.setString(2, sifre);
			rs = psmt.executeQuery();
			if (rs.next()) {
				kullanici.setKullaniciID(rs.getInt("id"));
				kullanici.setKullaniciAdi(rs.getString("kullaniciAdi"));
				kullanici.setKayitTarihi(rs.getDate("kayitTarihi"));
				
				return kullanici;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			rs.close();
			psmt.close();
		}
	}
	public boolean  isExistUser(String kullaniciAdi) throws SQLException {
		psmt = null;
		rs = null;
		String sql = "SELECT * FROM kullanici WHERE kullaniciAdi = ?";
		
		try{
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, kullaniciAdi);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch (SQLException ex) {
			return false;
		}finally {
			psmt.close();
			rs.close();
		}
	}
	
	public void addUser (String kullaniciAdi, String sifre) throws SQLException {
		psmt = null;
		rs = null;
		String sql = "INSERT INTO kullanici(kullaniciAdi, sifre) values(?,?)";
		try {
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, kullaniciAdi);
			psmt.setString(2, sifre);
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			psmt.close();
			rs.close();
		}
	}
	
	public void addBlogPost(String blogBaslik, String blogIcerik, int yazarID) throws SQLException {
		psmt = null;
		String sql = "INSERT INTO blog_yazilari(yazar_id, baslik, icerik) VALUES(?,?,?)";
		try {
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, yazarID);
			psmt.setString(2, blogBaslik);
			psmt.setString(3, blogIcerik);
			
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			psmt.close();
		}
	}
	
	public ArrayList<Blog> getAllPost() throws SQLException {
		ArrayList<Blog> blogYazilari = new ArrayList<Blog>();
		
		psmt = null;
		rs = null;
		String sql = "SELECT * FROM blog_yazilari";
		try {
			psmt = connection.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Blog blog = new Blog();
				System.out.println(rs.getString("baslik"));
				blog.setBlogID(rs.getInt("id"));
				blog.setBaslik(rs.getString("baslik"));
				blog.setIcerik(rs.getString("icerik"));
				blog.setYazarID(rs.getInt("yazar_id"));
				blog.setTarih(rs.getDate("tarih"));
				blogYazilari.add(blog);
			}
			return blogYazilari;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			rs.close();
			psmt.close();
		}
	}
	public Blog getPostByID(int id) throws SQLException {
		psmt = null;
		rs = null;
		Blog blog = new Blog();
		String sql = "SELECT * FROM blog_yazilari WHERE id = ?";
		try {
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				blog.setBlogID(rs.getInt("id"));
				blog.setBaslik(rs.getString("baslik"));
				blog.setIcerik(rs.getString("icerik"));
				blog.setYazarID(rs.getInt("yazar_id"));
				blog.setTarih(rs.getDate("tarih"));
				
				return blog;
			}
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			psmt.close();
			rs.close();
		}
		
	}
	public String getAuthorName(int yazarID) {
		psmt = null;
		rs = null;
		String sql = "SELECT kullaniciAdi from kullanici WHERE id = ?";
		
		try {
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, yazarID);
			rs = psmt.executeQuery();
			return rs.next() ? rs.getString("kullaniciAdi") : null;
 		} catch (SQLException e) {
 			e.printStackTrace();
 			return null;
		}
	}
	public void addComment(Yorum comment) throws SQLException {
		psmt = null;
		String sql = "INSERT INTO yorum (blog_id, yazar_id, icerik) values(?,?,?)";
		
		try {
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, comment.getBlogID());
			psmt.setInt(2, comment.getYazarID());
			psmt.setString(3, comment.getIcerik());
			
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			psmt.close();
		}
		
	}
	public ArrayList<Yorum> getCommentsByBlogId(int id) throws SQLException {
		psmt = null;
		rs = null;
		ArrayList<Yorum> yorumlar = new ArrayList<Yorum>();
		String sql = "SELECT * FROM yorum WHERE blog_id = ?";
		
		try{
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, id);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				Yorum yorum = new Yorum();
				yorum.setIcerik(rs.getString("icerik"));
				yorum.setBlogID(id);
				yorum.setYazarID(rs.getInt("yazar_id"));
				yorum.setYorumID(rs.getInt("id"));
				yorum.setTarih(rs.getDate("tarih"));
				
				yorumlar.add(yorum);
			}
			return yorumlar;
		} catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			psmt.close();
			rs.close();
		}
	}
	
	public ArrayList<Blog> postsByUserID(int userID) throws SQLException {
		psmt = null;
		rs = null;
		ArrayList<Blog> posts = new ArrayList<Blog>();
		String sql = "SELECT * FROM blog_yazilari WHERE yazar_id = ?";
		
		try {
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, userID);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setBlogID(rs.getInt("id"));
				blog.setBaslik(rs.getString("baslik"));
				blog.setIcerik(rs.getString("icerik"));
				blog.setYazarID(rs.getInt("yazar_id"));
				blog.setTarih(rs.getDate("tarih"));
				
				posts.add(blog);
			}
			return posts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			psmt.close();
			rs.close();
		}
	}
}
