/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gurhan.blog.dao;

import java.sql.SQLException;

/**
 *
 * @author gurhan
 */
public class DAOManager {
    	public static DAO dao;
	
	public static void createDao(String kullanciAdi, String sifre, String path) throws ClassNotFoundException, SQLException {
		dao = new DAO(kullanciAdi, sifre, path);
	}
}
