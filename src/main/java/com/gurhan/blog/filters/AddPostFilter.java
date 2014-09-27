package com.gurhan.blog.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/AddPostServlet"})
public class AddPostFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)servletRequest;
		HttpServletResponse resp = (HttpServletResponse)servletResponse;
		String hataMesaji = "";
		if (req.getParameter("blogBaslik").length() > 100 ) {
			hataMesaji = "Başlık en fazla 100 karakter uzunluğunda olmalı";
		}
		if (req.getParameter("blogIcerik").length() > 400) {
			hataMesaji = "İcerik en fazla 400 karakter uzunluğunda olmalı";
		}
		if (!hataMesaji.equals("")) {
			req.setAttribute("mesaj", hataMesaji);
			req.getRequestDispatcher("/blog/add_post.jsp").forward(req, resp);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
