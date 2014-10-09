    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.blog.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gurhan.blog.dao.DAOManager;
import com.gurhan.blog.model.Blog;

@WebServlet("/IndexPageServlet")
public class IndexPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if (req.getSession(false) != null) {
            ArrayList<Blog> allPost;
            HashMap<Integer, String> authorNames = new HashMap<Integer, String>();
            try {
                allPost = DAOManager.dao.getAllPost();
                for (Blog post : allPost) {
                    authorNames.put(post.getYazarID(), DAOManager.dao.getAuthorName(post.getYazarID()));
                }
                req.setAttribute("authorNames", authorNames);
                req.setAttribute("allPost", allPost);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect("login.jsp");
        }

    }

}
