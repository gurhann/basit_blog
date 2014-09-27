package com.blog.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gurhan.blog.dao.DAOManager;
import com.gurhan.blog.model.Blog;

@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int userID = Integer.parseInt(req.getParameter("user_id"));
		try {
			ArrayList<Blog> allPosts = DAOManager.dao.postsByUserID(userID);
			req.setAttribute("posts", allPosts);
			req.setAttribute("authorName", DAOManager.dao.getAuthorName(userID));
			req.getRequestDispatcher("/blog/profil.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

