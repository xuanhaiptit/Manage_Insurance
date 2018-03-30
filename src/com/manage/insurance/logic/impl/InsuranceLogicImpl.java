package com.manage.insurance.logic.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.dao.InsuranceDao;
import com.manage.insurance.dao.impl.InsuranceDaoImpl;
import com.manage.insurance.logic.InsuranceLogic;
import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.UserInfo;

public class InsuranceLogicImpl implements InsuranceLogic {
    InsuranceDao insuranceDao = new InsuranceDaoImpl();

    @Override
    public boolean isNotExistInsuranceNumber(String insuranceNumber) throws SQLException {
        boolean check = insuranceDao.isNotExistInsuranceNumber(insuranceNumber);
        return check;
    }

    @Override
    public Insurance getInsuranceByUserInternalId(UserInfo userInfo) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT_DATE_VIEW);
        Insurance insurance = new Insurance();
        insurance.setInsuranceInternalId(userInfo.getInsuranceInternalId());
        insurance.setInsuranceNumber(userInfo.getInsuranceNumber());
        insurance.setInsuranceStartDate(userInfo.getInsuranceStartDate());
        insurance.setInsuranceEndDate(userInfo.getInsuranceEndDate());
        insurance.setInsuranceStartDateTypeString(dateFormat.format(userInfo.getInsuranceStartDate()).toString());
        insurance.setInsuranceEndDateTypeString(dateFormat.format(userInfo.getInsuranceEndDate()).toString());

        insurance.setPlaceOfRegister(userInfo.getPlaceOfRegister());
        return insurance;
    }
}
