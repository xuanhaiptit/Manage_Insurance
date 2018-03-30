package com.manage.insurance.dao;

import java.sql.SQLException;
import java.util.List;

import com.manage.insurance.model.User;
import com.manage.insurance.model.UserInfo;

/**
 * Inteface UserDao Processed data related to the User
 * 
 * @author lexuanhai
 *
 */
public interface UserDao {
    /**
     * Method get List User Info
     * 
     * @param companyInternalId id company Internal of company
     * @param userFullName full name user
     * @param insuranceNumber insurance number
     * @param placeOfRegister place of register
     * @param typeSort type sort of user Full Name
     * @param offset location data to get
     * @param limit the amount of data to take
     * @return List UserInfo
     * @throws SQLException
     */
    List<UserInfo> getListUserInfo(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister, String typeSort, int offset, int limit) throws SQLException;

    /**
     * Method get Total User
     * 
     * @param companyInternalId id company Internal of company
     * @param userFullName full name user
     * @param insuranceNumber insurance number
     * @param placeOfRegister place of register
     * @param typeSort type sort of user Full Name
     * @param offset location data to get
     * @param limit the amount of data to take
     * @return total user
     * @throws SQLException
     */
    int getTotalUserInfo(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
            String typeSort) throws SQLException;

    /**
     * Method check isUserInfo of table User
     * 
     * @param userInternalId id user
     * @return true if isUser, false not isUser
     * @throws SQLException
     */
    boolean isUserInfo(int userInternalId) throws SQLException;

    /**
     * Method get User Info
     * 
     * @param userInternalId id user Internal
     * @return user info
     * @throws SQLException
     */
    UserInfo getUserInfo(int userInternalId) throws SQLException;

    /**
     * Method Delete User of table User
     * 
     * @param userInternalId Id User Internal
     * @return true
     * @throws SQLException
     */
    boolean deleteUser(int userInternalId) throws SQLException;

    /**
     * Method add User in table User
     * 
     * @param user
     * @return true if add Success, false if add not success
     */
    boolean addUser(User user);

    /**
     * Method update User
     * 
     * @param user
     * @return true if update success, else if update failure
     */
    boolean isUpdateUser(User user);

    /**
     * Method get List User Info Export
     * 
     * @param companyInternalId id company Internal of company
     * @param userFullName full name user
     * @param insuranceNumber insurance number
     * @param placeOfRegister place of register
     * @return List UserInfo
     * @throws SQLException
     */
    List<UserInfo> getListUserInfoExport(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister) throws SQLException;
}
