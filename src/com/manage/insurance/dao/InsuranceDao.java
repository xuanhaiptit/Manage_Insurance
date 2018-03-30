package com.manage.insurance.dao;

import java.sql.SQLException;

import com.manage.insurance.model.Insurance;

/**
 * Inteface InsuranceDao Processed data related to the Insurance
 * 
 * @author lexuanhai
 *
 */
public interface InsuranceDao {
    /**
     * Method check exist Insurance Number
     * 
     * @param insuranceNumber
     * @return true if not exist, false if exist
     * @throws SQLException
     */
    boolean isNotExistInsuranceNumber(String insuranceNumber) throws SQLException;

    /**
     * Method add Insurance in table Insurance
     * 
     * @param insurance
     * @return generatedKey
     */
    int addInsurance(Insurance insurance);

    /**
     * Method update Insurance
     * 
     * @param insurance
     * @return true if update Success, false if update failure
     */
    boolean isUpdateInsurance(Insurance insurance);
}
