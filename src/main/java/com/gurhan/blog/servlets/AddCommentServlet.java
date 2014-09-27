/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.blog.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gurhan.blog.dao.DAOManager;
import com.gurhan.blog.model.Yorum;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Yorum comment = new Yorum();
		comment.setBlogID(Integer.parseInt(req.getParameter("blogID")));
		comment.setYazarID(Integer.parseInt(req.getParameter("yazarID")));
		comment.setIcerik(req.getParameter("yorum"));
		
		try {
			DAOManager.dao.addComment(comment);
			resp.sendRedirect("PostPageServlet?post_id="+comment.getBlogID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

