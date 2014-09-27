package com.gurhan.blog.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    @Override
    public void destroy() {
		// TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {
       
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        System.out.println("Filtre çalıştı");
        if (req.getSession(false) == null) {
            req.setAttribute("hataMesaji", "Lütfen Giriş Yapın");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            System.out.println("session zaten var");
        }
        chain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

    }

}
