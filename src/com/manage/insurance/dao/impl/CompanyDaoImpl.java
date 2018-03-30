package com.manage.insurance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manage.insurance.dao.CompanyDao;
import com.manage.insurance.model.Company;

/**
 * Class CompanyDaoImpl Processed data related to the company
 * 
 * @author lexuanhai
 *
 */
public class CompanyDaoImpl extends BaseDaoImpl implements CompanyDao {
    PreparedStatement statement;

    public CompanyDaoImpl() {
        super();
    }

    public CompanyDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Company> findAllCompany() throws SQLException {
        List<Company> listCompany = new ArrayList<>();
        conn = getConnect();
        try {
            if (conn != null) {
                String sql = "SELECT * FROM tbl_company";
                statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Company company = new Company();
                    int index = 1;
                    company.setCompanyInternalId(resultSet.getInt(index++));
                    company.setCompanyName(resultSet.getString(index++));
                    company.setAddress(resultSet.getString(index++));
                    company.setEmail(resultSet.getString(index++));
                    company.setTelephone(resultSet.getString(index++));
                    listCompany.add(company);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return listCompany;
    }

    @Override
    public int addCompany(Company company) {
        try {
            if (conn != null) {
                StringBuilder sqlBuilder = new StringBuilder();
                sqlBuilder.append(" INSERT INTO `tbl_company` (`company_name`, `address`, `email`, `telephone`) ");
                sqlBuilder.append(" VALUES (?, ?, ?, ?); ");
                statement = conn.prepareStatement(sqlBuilder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
                int index = 1;
                statement.setString(index++, company.getCompanyName());
                statement.setString(index++, company.getAddress());
                statement.setString(index++, company.getEmail());
                statement.setString(index++, company.getTelephone());

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
}
