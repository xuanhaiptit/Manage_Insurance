
package com.manage.insurance.dao;

import java.sql.Connection;

/**
 * Interface Processed data related to the Database
 * 
 * @author HaiLX
 */
public interface BaseDao {

    /**
     * Connect to DB
     * 
     * @return connecttion
     */
    public Connection getConnect();
}
