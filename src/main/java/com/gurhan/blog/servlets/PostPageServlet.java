package com.blog.servlets;

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
import com.gurhan.blog.model.Yorum;

@WebServlet("/PostPageServlet")
public class PostPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Blog post;
        ArrayList<Yorum> comments;
        HashMap<Integer, String> authorNames = new HashMap<Integer, String>();
        try {
            post = DAOManager.dao.getPostByID(Integer.parseInt(req.getParameter("post_id")));
            comments = DAOManager.dao.getCommentsByBlogId(Integer.parseInt(req.getParameter("post_id")));
            for (Yorum comment : comments) {
                authorNames.put(comment.getYazarID(), DAOManager.dao.getAuthorName(comment.getYazarID()));
            }
            req.setAttribute("comments", comments);
            req.setAttribute("authorNames", authorNames);
            req.setAttribute("post", post);
            req.getRequestDispatcher("/blog/post_page.jsp").forward(req, resp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

}
