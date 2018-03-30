package com.manage.insurance.dao;

import java.sql.SQLException;
import java.util.List;

import com.manage.insurance.model.Company;

/**
 * Inteface CompanyDao Processed data related to the Company
 * 
 * @author lexuanhai
 *
 */
public interface CompanyDao {

    /**
     * Get all list data Company of table Company
     * 
     * @return listCompany
     * @throws SQLException
     */
    List<Company> findAllCompany() throws SQLException;

    /**
     * Method add Company in table Company
     * 
     * @param company
     * @return generatedKey
     */
    int addCompany(Company company);
}
