
package com.manage.insurance.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import com.manage.insurance.dao.BaseDao;
import com.manage.insurance.properties.ConfigMysql;

/**
 * Class BaseDaoImpl implements inteface BaseDao
 * 
 * @author LeXuanHai
 */
public class BaseDaoImpl implements BaseDao {
    protected Connection conn;

    /*
     * (non-Javadoc)
     * 
     * @see Dao.BaseDao#getConnect()
     */
    @Override
    public Connection getConnect() {
        String classname = ConfigMysql.getString("classname");
        String url = ConfigMysql.getString("url");
        String user = ConfigMysql.getString("username");
        String password = ConfigMysql.getString("password");
        try {
            Class.forName(classname);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Ket noi khong thanh cong");
        }
        return conn;
    }
}
