package com.manage.insurance.logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manage.insurance.dao.CompanyDao;
import com.manage.insurance.dao.impl.CompanyDaoImpl;
import com.manage.insurance.logic.CompanyLogic;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.UserInfo;

/**
 * Class CompanyLogicImpl implements CompanyLogic
 * 
 * @author lexuanhai
 */
public class CompanyLogicImpl implements CompanyLogic {

    @Override
    public List<Company> findAllCompany() throws SQLException {
        CompanyDao companyDao = new CompanyDaoImpl();
        List<Company> listCompany = new ArrayList<>();
        listCompany = companyDao.findAllCompany();
        return listCompany;
    }

    @Override
    public Company getCompanyByUserInternalId(UserInfo userInfo) throws SQLException {
        Company company = new Company();
        company.setCompanyInternalId(userInfo.getCompanyInternalId());
        company.setCompanyName(userInfo.getCompanyName());
        company.setAddress(userInfo.getAddress());
        company.setEmail(userInfo.getEmail());
        company.setTelephone(userInfo.getTelephone());
        return company;
    }
}
