package com.manage.insurance.logic;

import java.sql.SQLException;
import java.util.List;

import com.manage.insurance.model.Company;
import com.manage.insurance.model.UserInfo;

/**
 * Inteface CompanyLogic Processed data related to the Company
 * 
 * @author lexuanhai
 *
 */
public interface CompanyLogic {
    /**
     * Get all list data Company of table Company
     * 
     * @return listCompany
     * @throws SQLException
     */
    List<Company> findAllCompany() throws SQLException;

    /**
     * method get Company by UserInfo
     * 
     * @param UserInfo
     * @return company
     * @throws SQLException
     */
    Company getCompanyByUserInternalId(UserInfo userInfo) throws SQLException;
}
