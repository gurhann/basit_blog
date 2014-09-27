package com.gurhan.blog.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gurhan.blog.dao.DAOManager;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/RegisterServlet"})
public class RegisterFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Register filter calisti");
		HttpServletRequest req = (HttpServletRequest)servletRequest;
		HttpServletResponse resp = (HttpServletResponse)servletResponse;
		String kullaniciAdi = req.getParameter("k_adi");
		try {
			if (DAOManager.dao.isExistUser(kullaniciAdi)) {
				req.setAttribute("mesaj", "Bu kullanıcı Adı Kullanılmakta");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!req.getParameter("sifre1").equals(req.getParameter("sifre2"))) {
			req.setAttribute("mesaj", "Şifreler Uyuşmuyor");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
			
		}
		chain.doFilter(servletRequest, servletResponse);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
				
	}
	
}
