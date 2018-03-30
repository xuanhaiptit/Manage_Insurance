package com.manage.insurance.logic.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.manage.insurance.common.Common;
import com.manage.insurance.constant.Constants;
import com.manage.insurance.dao.CompanyDao;
import com.manage.insurance.dao.InsuranceDao;
import com.manage.insurance.dao.UserDao;
import com.manage.insurance.dao.impl.BaseDaoImpl;
import com.manage.insurance.dao.impl.CompanyDaoImpl;
import com.manage.insurance.dao.impl.InsuranceDaoImpl;
import com.manage.insurance.dao.impl.UserDaoImpl;
import com.manage.insurance.logic.UserLogic;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.User;
import com.manage.insurance.model.UserInfo;

/**
 * Class UserLogicImpl Implement Inteface UserLogic
 * 
 * @author lexuanhai
 *
 */
public class UserLogicImpl extends BaseDaoImpl implements UserLogic {
    UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserInfo> getListUserInfo(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister, String typeSort, int offset, int limit) throws SQLException {
        userFullName = Common.changeStringIfNull(userFullName);
        insuranceNumber = Common.changeStringIfNull(insuranceNumber);
        placeOfRegister = Common.changeStringIfNull(placeOfRegister);
        userFullName = Common.placeWildCard(userFullName);
        insuranceNumber = Common.placeWildCard(insuranceNumber);
        placeOfRegister = Common.placeWildCard(placeOfRegister);
        return userDao.getListUserInfo(companyInternalId, userFullName, insuranceNumber, placeOfRegister, typeSort,
                offset, limit);
    }

    @Override
    public int getTotalUserInfo(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister, String typeSort) throws SQLException {
        userFullName = Common.changeStringIfNull(userFullName);
        insuranceNumber = Common.changeStringIfNull(insuranceNumber);
        placeOfRegister = Common.changeStringIfNull(placeOfRegister);
        userFullName = Common.placeWildCard(userFullName);
        insuranceNumber = Common.placeWildCard(insuranceNumber);
        placeOfRegister = Common.placeWildCard(placeOfRegister);
        return userDao.getTotalUserInfo(companyInternalId, userFullName, insuranceNumber, placeOfRegister, typeSort);
    }

    @Override
    public boolean isUserInfo(int userInternalId) throws SQLException {
        return userDao.isUserInfo(userInternalId);
    }

    @Override
    public UserInfo getUserInfo(int userInternalId) throws SQLException {
        return userDao.getUserInfo(userInternalId);
    }

    @Override
    public boolean deleteUser(int userInternalId) throws SQLException {
        return userDao.deleteUser(userInternalId);
    }

    @Override
    public boolean addUserInfo(User user, Company company, Insurance insurance) throws SQLException {
        Connection conn = getConnect();
        boolean flag = false;
        try {
            if (conn != null) {
                UserDao userDao = new UserDaoImpl(conn);
                InsuranceDao insuranceDao = new InsuranceDaoImpl(conn);
                CompanyDao companyDao = new CompanyDaoImpl(conn);
                company = Common.standardPersonalizedCompany(company);
                user = Common.standardPersonalizedUser(user);
                insurance = Common.standardPersonalizedInsurance(insurance);
                conn.setAutoCommit(false);
                if (user.getCompanyInternalId() == 0) {
                    user.setCompanyInternalId(companyDao.addCompany(company));;
                }
                user.setInsuranceInternalId(insuranceDao.addInsurance(insurance));
                if (user.getCompanyInternalId() != 0 && user.getInsuranceInternalId() != 0) {
                    flag = userDao.addUser(user);
                }
                if (flag == true) {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            conn.rollback();
            flag = false;
        } finally {
            conn.close();
        }
        return flag;
    }

    @Override
    public User getUserByUserInternalId(UserInfo userInfo) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_VIEW);
        User user = new User();
        user.setUserInternalId(userInfo.getUserInternalId());
        user.setCompanyInternalId(userInfo.getCompanyInternalId());
        user.setInsuranceInternalId(userInfo.getInsuranceInternalId());
        user.setUserFullName(userInfo.getUserFullName());
        user.setUserSexDivision(userInfo.getUserSexDivision());
        user.setBirthdate(userInfo.getBirthdate());
        user.setBirthdateTypeString(dateFormat.format(userInfo.getBirthdate()));
        return user;
    }

    @Override
    public boolean updateUserInfo(User user, Company company, Insurance insurance) throws SQLException {
        Connection conn = getConnect();
        boolean flag = false;
        try {
            if (conn != null) {
                UserDao userDao = new UserDaoImpl(conn);
                InsuranceDao insuranceDao = new InsuranceDaoImpl(conn);
                CompanyDao companyDao = new CompanyDaoImpl(conn);
                user = Common.standardPersonalizedUser(user);
                company = Common.standardPersonalizedCompany(company);
                insurance = Common.standardPersonalizedInsurance(insurance);
                conn.setAutoCommit(false);
                if (user.getCompanyInternalId() == 0) {
                    user.setCompanyInternalId(companyDao.addCompany(company));
                }
                if (insuranceDao.isUpdateInsurance(insurance)) {
                    flag = userDao.isUpdateUser(user);
                }
                if (flag == true) {
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            flag = false;
        } finally {
            conn.close();
        }
        return flag;
    }

    @Override
    public List<UserInfo> getListUserInfoExport(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        return userDao.getListUserInfoExport(companyInternalId, userFullName, insuranceNumber, placeOfRegister);
    }
}
