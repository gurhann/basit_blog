package com.gurhan.blog.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurhan.blog.dao.DAOManager;
import com.gurhan.blog.model.Kullanici;
import com.gurhan.blog.util.MD5Hash;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        if (req.getSession(false) == null) {
            // Gelen formdan kullanıcıAdı ve şifre alınır.
            String kullanciAdi = req.getParameter("k_adi");
            String sifre = MD5Hash.MD5(req.getParameter("sifre"));

            Kullanici user;
            try {
                //kullanıcıAdı ve şifre getUser metoduna gönderilir.
                user = DAOManager.dao.getUser(kullanciAdi, sifre);
                /*
                 *Eğer kullanıcı adı ve şifre uyuşmamışsa login.jsp sayfasına
                 *yönlendirilir ve hataMesajında Kullanıcı Adı veya Şifrenin
                 *yanlış olduğu bildirilir.
                 */
                if (user == null) {
                    req.setAttribute("hataMesaji", "Kullanıcı Adı veya Şifre Yanlış");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                } else {
                    /*
                     *Eğer kullanıcı adı ve şifre  doğru ise bu kullanıcıNesnesi
                     *oluşturulan yeni sessiona kaydedilir ve IndexPageServlet'e
                     *yönlendirme yapılır.
                     */
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
        } else {
            req.getRequestDispatcher("/IndexPageServlet").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

}
