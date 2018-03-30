package com.manage.insurance.logic;

import java.sql.SQLException;

import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.UserInfo;

/**
 * Inteface InsuranceLogic Processed data related to the Insurance
 * 
 * @author lexuanhai
 *
 */
public interface InsuranceLogic {
    /**
     * Method check exist Insurance Number
     * 
     * @param insuranceNumber
     * @return true if not exist, false if exist
     * @throws SQLException
     */
    boolean isNotExistInsuranceNumber(String insuranceNumber) throws SQLException;

    /**
     * method get Insurance by UserInfo
     * 
     * @param userInfo
     * @return insurance
     * @throws SQLException
     */
    Insurance getInsuranceByUserInternalId(UserInfo userInfo) throws SQLException;

}
