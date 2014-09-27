package com.gurhan.blog.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurhan.blog.dao.DAO;
import com.gurhan.blog.dao.DAOManager;
import com.gurhan.blog.model.Blog;
import com.gurhan.blog.model.Kullanici;
import com.gurhan.blog.util.MD5Hash;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(req.getSession(false) == null){

			String kullanciAdi = req.getParameter("k_adi");
			String sifre = MD5Hash.MD5(req.getParameter("sifre"));
			
			Kullanici user;
		try {
			user = DAOManager.dao.getUser(kullanciAdi, sifre);
			if(user == null) {
				req.setAttribute("hataMesaji", "Kullanıcı Adı veya Şifre Yanlış");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				HttpSession session = req.getSession(false);
				if (session == null) {
					session = req.getSession();
					session.setAttribute("user", user);	
					req.getRequestDispatcher("/IndexPageServlet").forward(req, resp);
				}
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}else {
			req.getRequestDispatcher("/IndexPageServlet").forward(req, resp);
		}
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
