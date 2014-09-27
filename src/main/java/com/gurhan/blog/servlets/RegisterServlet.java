package com.blog.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gurhan.blog.dao.DAOManager;
import com.gurhan.blog.util.MD5Hash;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String kullaniciAdi = req.getParameter("k_adi");
		String sifre = MD5Hash.MD5(req.getParameter("sifre1"));
		
		try {
			DAOManager.dao.addUser(kullaniciAdi, sifre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("login.jsp");
		
	}

}
