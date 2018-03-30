package com.manage.insurance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.manage.insurance.dao.InsuranceDao;
import com.manage.insurance.model.Insurance;

/**
 * Class InsuranceDaoImpl inplement inteface InsuranceDao
 * 
 * @author lexuanhai
 *
 */
public class InsuranceDaoImpl extends BaseDaoImpl implements InsuranceDao {
    PreparedStatement statement;

    public InsuranceDaoImpl(Connection conn) {
        this.conn = conn;
    }

    public InsuranceDaoImpl() {}

    @Override
    public boolean isNotExistInsuranceNumber(String insuranceNumber) throws SQLException {
        int totalInsuranceNumber = 0;
        try {
            conn = getConnect();
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" SELECT COUNT(insurance_internal_id ) ");
                sqlBuilder.append(" FROM tbl_insurance ");
                sqlBuilder.append(" WHERE insurance_number = ? ; ");
                statement = conn.prepareStatement(sqlBuilder.toString());
                statement.setString(1, insuranceNumber);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    totalInsuranceNumber = resultSet.getInt(1);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        if (totalInsuranceNumber >= 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int addInsurance(Insurance insurance) {
        try {
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" INSERT INTO `tbl_insurance` (`insurance_number`, `insurance_start_date`, ");
                sqlBuilder.append(" `insurance_end_date`, `place_of_register`) ");
                sqlBuilder.append(" VALUES (?, ?, ?, ?); ");
                statement = conn.prepareStatement(sqlBuilder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int index = 1;
                statement.setString(index++, insurance.getInsuranceNumber());
                statement.setDate(index++, insurance.getInsuranceStartDate());
                statement.setDate(index++, insurance.getInsuranceEndDate());
                statement.setString(index++, insurance.getPlaceOfRegister());

                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                int generatedKey = 0;
                if (resultSet.next()) {
                    generatedKey = resultSet.getInt(1);
                }
                return generatedKey;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isUpdateInsurance(Insurance insurance) {
        try {
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" UPDATE `tbl_insurance`  ");
                sqlBuilder.append(
                        " SET `insurance_number`= ?, `insurance_start_date`= ?, `insurance_end_date`= ?, `place_of_register`= ? ");
                sqlBuilder.append(" WHERE `insurance_internal_id`= ? ; ");

                statement = conn.prepareStatement(sqlBuilder.toString());
                int temp = 1;
                statement.setString(temp++, insurance.getInsuranceNumber());
                statement.setDate(temp++, insurance.getInsuranceStartDate());
                statement.setDate(temp++, insurance.getInsuranceEndDate());
                statement.setString(temp++, insurance.getPlaceOfRegister());
                statement.setInt(temp++, insurance.getInsuranceInternalId());
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
}
