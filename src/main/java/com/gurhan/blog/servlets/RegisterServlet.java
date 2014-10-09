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
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //Formdan gelen bilgiler alınıyor.
        String kullaniciAdi = req.getParameter("k_adi");
        String sifre1 = MD5Hash.MD5(req.getParameter("sifre1"));
        String sifre2 = MD5Hash.MD5(req.getParameter("sifre2"));
        try {
            //İlk olarak bu kullanıcı adının olup olmadığına bakılıyor
            if (!DAOManager.dao.isExistUser(kullaniciAdi)) {
                //Sonra şifrelerin aynı olup olmadığı kontrol ediliyor.
                if (!sifre1.equals(sifre2)) {
                    req.setAttribute("mesaj", "Şifreler Uyuşmuyor");
                    req.getRequestDispatcher("/register.jsp").forward(req, resp);

                } else {
                    //Sorun yoksa yeni kullanıcı eklenip giriş yapması için
                    //login.jsp sayfasına gönderiliyor.
                    DAOManager.dao.addUser(kullaniciAdi, sifre2);
                    resp.sendRedirect("login.jsp");
                }
            } else {
                req.setAttribute("mesaj", "Bu kullanıcı Adı Kullanılmakta");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
