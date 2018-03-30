package com.manage.insurance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.manage.insurance.dao.UserDao;
import com.manage.insurance.model.User;
import com.manage.insurance.model.UserInfo;

/**
 * Class UserDaoImpl Implement Inteface UserDao
 * 
 * @author lexuanhai
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    PreparedStatement statement;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public UserDaoImpl() {}

    @Override
    public List<UserInfo> getListUserInfo(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister, String typeSort, int offset, int limit) throws SQLException {
        List<UserInfo> listUserInfo = new ArrayList<>();
        try {
            conn = getConnect();
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(
                        " SELECT DISTINCT(user_internal_id), user_full_name, user_sex_division, birthdate, insurance_number, ");
                sqlBuilder.append(
                        " insurance_start_date, insurance_end_date,  place_of_register, company_name, address, email, telephone ");
                sqlBuilder.append(" FROM tbl_user AS user ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_company AS company ON user.company_internal_id = company.company_internal_id ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_insurance AS insurance ON user.insurance_internal_id = insurance.insurance_internal_id ");
                sqlBuilder.append(" WHERE company.company_internal_id = ? ");
                sqlBuilder.append("     AND user.user_full_name LIKE ? ");
                sqlBuilder.append("     AND insurance.insurance_number LIKE ? ");
                sqlBuilder.append("     AND insurance.place_of_register LIKE ? ");
                sqlBuilder.append(" ORDER BY user.user_full_name " + typeSort);
                sqlBuilder.append(" LIMIT ? OFFSET ? ; ");

                statement = conn.prepareStatement(sqlBuilder.toString());
                int temp = 1;
                statement.setInt(temp++, companyInternalId);
                statement.setString(temp++, "%" + userFullName + "%");
                statement.setString(temp++, "%" + insuranceNumber + "%");
                statement.setString(temp++, "%" + placeOfRegister + "%");
                statement.setInt(temp++, limit);
                statement.setInt(temp++, offset);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    UserInfo userInfo = new UserInfo();
                    int index = 1;
                    userInfo.setUserInternalId(resultSet.getInt(index++));
                    userInfo.setUserFullName(resultSet.getString(index++));
                    userInfo.setUserSexDivision(resultSet.getString(index++));
                    userInfo.setBirthdate(resultSet.getDate(index++));
                    userInfo.setInsuranceNumber(resultSet.getString(index++));
                    userInfo.setInsuranceStartDate(resultSet.getDate(index++));
                    userInfo.setInsuranceEndDate(resultSet.getDate(index++));
                    userInfo.setPlaceOfRegister(resultSet.getString(index++));
                    userInfo.setCompanyName(resultSet.getString(index++));
                    userInfo.setAddress(resultSet.getString(index++));
                    userInfo.setEmail(resultSet.getString(index++));
                    userInfo.setTelephone(resultSet.getString(index++));
                    listUserInfo.add(userInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return listUserInfo;
    }

    @Override
    public int getTotalUserInfo(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister, String typeSort) throws SQLException {
        int totalUser = 0;
        try {
            conn = getConnect();
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" SELECT COUNT(user_internal_id) ");
                sqlBuilder.append(" FROM tbl_user AS user ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_company AS company ON user.company_internal_id = company.company_internal_id ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_insurance AS insurance ON user.insurance_internal_id = insurance.insurance_internal_id ");
                sqlBuilder.append(" WHERE company.company_internal_id = ? ");
                sqlBuilder.append("     AND user.user_full_name LIKE ? ");
                sqlBuilder.append("     AND insurance.insurance_number LIKE ? ");
                sqlBuilder.append("     AND insurance.place_of_register LIKE ? ");
                sqlBuilder.append(" ORDER BY user.user_full_name " + typeSort + " ; ");

                statement = conn.prepareStatement(sqlBuilder.toString());
                int temp = 1;
                statement.setInt(temp++, companyInternalId);
                statement.setString(temp++, "%" + userFullName + "%");
                statement.setString(temp++, "%" + insuranceNumber + "%");
                statement.setString(temp++, "%" + placeOfRegister + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    totalUser = resultSet.getInt(1);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return totalUser;
    }

    @Override
    public boolean isUserInfo(int userInternalId) throws SQLException {
        int totalUser = 0;
        try {
            conn = getConnect();
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" SELECT COUNT(user_internal_id ) ");
                sqlBuilder.append(" FROM tbl_user ");
                sqlBuilder.append(" WHERE user_internal_id = ? ; ");
                statement = conn.prepareStatement(sqlBuilder.toString());
                statement.setInt(1, userInternalId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    totalUser = resultSet.getInt(1);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        if (totalUser == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserInfo getUserInfo(int userInternalId) throws SQLException {
        UserInfo userInfo = new UserInfo();
        try {
            conn = getConnect();
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(
                        " SELECT user.company_internal_id, user.insurance_internal_id, user_full_name, user_sex_division, birthdate, insurance_number, ");
                sqlBuilder.append(" insurance_start_date, insurance_end_date,  place_of_register, company_name ");
                sqlBuilder.append(" FROM tbl_user AS user ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_company AS company ON user.company_internal_id = company.company_internal_id ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_insurance AS insurance ON user.insurance_internal_id = insurance.insurance_internal_id ");
                sqlBuilder.append(" WHERE user.user_internal_id = ? ; ");

                statement = conn.prepareStatement(sqlBuilder.toString());
                statement.setInt(1, userInternalId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int index = 1;
                    userInfo.setUserInternalId(userInternalId);
                    userInfo.setCompanyInternalId(resultSet.getInt(index++));
                    userInfo.setInsuranceInternalId(resultSet.getInt(index++));
                    userInfo.setUserFullName(resultSet.getString(index++));
                    userInfo.setUserSexDivision(resultSet.getString(index++));
                    userInfo.setBirthdate(resultSet.getDate(index++));
                    userInfo.setInsuranceNumber(resultSet.getString(index++));
                    userInfo.setInsuranceStartDate(resultSet.getDate(index++));
                    userInfo.setInsuranceEndDate(resultSet.getDate(index++));
                    userInfo.setPlaceOfRegister(resultSet.getString(index++));
                    userInfo.setCompanyName(resultSet.getString(index++));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return userInfo;
    }

    @Override
    public boolean deleteUser(int userInternalId) throws SQLException {
        try {
            if (conn != null) {
                String sql = "DELETE FROM tbl_user WHERE user_internal_id = ? ; ";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, userInternalId);
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        try {
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" INSERT INTO tbl_user (`company_internal_id`, `insurance_internal_id`, ");
                sqlBuilder.append(" `user_full_name`, `user_sex_division`, `birthdate`) ");
                sqlBuilder.append(" VALUES (?, ?, ?, ?, ?); ");
                statement = conn.prepareStatement(sqlBuilder.toString());
                int index = 1;
                statement.setInt(index++, user.getCompanyInternalId());
                statement.setInt(index++, user.getInsuranceInternalId());
                statement.setString(index++, user.getUserFullName());
                statement.setString(index++, user.getUserSexDivision());
                statement.setDate(index++, user.getBirthdate());
                int temp = statement.executeUpdate();
                if (temp > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isUpdateUser(User user) {
        try {
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" UPDATE `tbl_user` ");
                sqlBuilder.append(" SET `company_internal_id`= ?, `insurance_internal_id`= ? , ");
                sqlBuilder.append(" `user_full_name`=? , `user_sex_division`= ? , `birthdate`= ? ");
                sqlBuilder.append(" WHERE `user_internal_id`= ? ; ");

                statement = conn.prepareStatement(sqlBuilder.toString());
                int temp = 1;
                statement.setInt(temp++, user.getCompanyInternalId());
                statement.setInt(temp++, user.getInsuranceInternalId());
                statement.setString(temp++, user.getUserFullName());
                statement.setString(temp++, user.getUserSexDivision());
                statement.setDate(temp++, user.getBirthdate());
                statement.setInt(temp++, user.getUserInternalId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserInfo> getListUserInfoExport(int companyInternalId, String userFullName, String insuranceNumber,
            String placeOfRegister) throws SQLException {
        List<UserInfo> listUserInfo = new ArrayList<>();
        try {
            conn = getConnect();
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(
                        " SELECT DISTINCT(user_internal_id), user_full_name, user_sex_division, birthdate, insurance_number, ");
                sqlBuilder.append(
                        " insurance_start_date, insurance_end_date,  place_of_register, company_name, address, email, telephone ");
                sqlBuilder.append(" FROM tbl_user AS user ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_company AS company ON user.company_internal_id = company.company_internal_id ");
                sqlBuilder.append(
                        "     INNER JOIN tbl_insurance AS insurance ON user.insurance_internal_id = insurance.insurance_internal_id ");
                sqlBuilder.append(" WHERE company.company_internal_id = ? ");
                sqlBuilder.append("     AND user.user_full_name LIKE ? ");
                sqlBuilder.append("     AND insurance.insurance_number LIKE ? ");
                sqlBuilder.append("     AND insurance.place_of_register LIKE ? ; ");

                statement = conn.prepareStatement(sqlBuilder.toString());
                int temp = 1;
                statement.setInt(temp++, companyInternalId);
                statement.setString(temp++, "%" + userFullName + "%");
                statement.setString(temp++, "%" + insuranceNumber + "%");
                statement.setString(temp++, "%" + placeOfRegister + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    UserInfo userInfo = new UserInfo();
                    int index = 1;
                    userInfo.setUserInternalId(resultSet.getInt(index++));
                    userInfo.setUserFullName(resultSet.getString(index++));
                    userInfo.setUserSexDivision(resultSet.getString(index++));
                    userInfo.setBirthdate(resultSet.getDate(index++));
                    userInfo.setInsuranceNumber(resultSet.getString(index++));
                    userInfo.setInsuranceStartDate(resultSet.getDate(index++));
                    userInfo.setInsuranceEndDate(resultSet.getDate(index++));
                    userInfo.setPlaceOfRegister(resultSet.getString(index++));
                    userInfo.setCompanyName(resultSet.getString(index++));
                    userInfo.setAddress(resultSet.getString(index++));
                    userInfo.setEmail(resultSet.getString(index++));
                    userInfo.setTelephone(resultSet.getString(index++));
                    listUserInfo.add(userInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return listUserInfo;
    }
}
