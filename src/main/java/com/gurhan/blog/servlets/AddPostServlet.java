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
import com.gurhan.blog.model.Kullanici;

@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String blogBaslik = req.getParameter("blogBaslik");
        String blogIcerik = req.getParameter("blogIcerik");
        int yazarID = ((Kullanici) req.getSession(false).getAttribute("user")).getKullaniciID();
        try {
            DAOManager.dao.addBlogPost(blogBaslik, blogIcerik, yazarID);
            resp.sendRedirect("IndexPageServlet");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
