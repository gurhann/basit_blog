package com.gurhan.blog.listeners;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.gurhan.blog.dao.DAOManager;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        try {
            DAOManager.dao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        ServletContext context = contextEvent.getServletContext();
        String dbKullaniciAdi = context.getInitParameter("dbKullaniciAdi");
        String dbSifre = context.getInitParameter("dbSifre");
        String dbPath = context.getInitParameter("dbPath");

        try {
            DAOManager.createDao(dbKullaniciAdi, dbSifre, dbPath);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
