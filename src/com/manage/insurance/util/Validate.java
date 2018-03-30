package com.manage.insurance.util;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.manage.insurance.common.Common;
import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.MessageCodeConstant;
import com.manage.insurance.logic.InsuranceLogic;
import com.manage.insurance.logic.impl.InsuranceLogicImpl;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.User;
import com.manage.insurance.properties.AccountListProperties;
import com.manage.insurance.properties.MessageProperties;

/**
 * Class validate in project
 * 
 * @author lexuanhai
 *
 */
public class Validate {
    /**
     * Method add message Error in Login
     * 
     * @param userName is passed
     * @param passWord is passed
     * @return list Message Error
     */
    public static List<String> messageErrorLogin(String userName, String passWord) {
        ArrayList<String> messageError = new ArrayList<String>();
        if (Common.isEmpty(userName) || Common.isEmpty(passWord)) {
            if (Common.isEmpty(userName)) {
                messageError.add(MessageCodeConstant.ERROR_ENTER_USERNAME);
            }
            if (Common.isEmpty(passWord)) {
                messageError.add(MessageCodeConstant.ERROR_ENTER_PASSWORD);
            }
        } else {
            if (!isLogin(userName, passWord)) {
                messageError.add(MessageCodeConstant.ERROR_LOGIN_NAME_OR_PASSWORD_IS_INCORRECT);
            }
        }
        return messageError;
    }

    /**
     * Method check userName and passWord have entered correctly
     * 
     * @param userName is Username to check
     * @param passWord is passWord to check
     * @return true if isLogin, false if not isLogin
     */
    public static boolean isLogin(String userName, String passWord) {
        String passByNameInFile = AccountListProperties.getPassByName(userName);
        if (passByNameInFile == null) {
            return false;
        } else {
            String passWordConventCryptMD5 = Common.cryptMD5(passWord);
            if (passWordConventCryptMD5.equals(passByNameInFile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method is Format Date
     * 
     * @param birthdate
     * @return true if isFormat, false if not isFormat
     */
    public static boolean isFormatDate(String birthdate) {
        if (!birthdate.substring(2, 3).equals("/") || !birthdate.substring(5, 6).equals("/")) {
            return false;
        } else {
            int count = 0;
            for (int i = 0; i < birthdate.length(); i++) {
                if (birthdate.charAt(i) == '/') {
                    count++;
                }
            }
            if (count > 2) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Method check integer
     * 
     * @param sData
     * @return true if sData is Integer, false if sData not is Integer
     */
    public static boolean isInteger(String sData) {
        sData = sData.replaceAll("/", "");
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(sData);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method check String is Date
     * 
     * @param dateString
     * @return true if isDate, false if not isDate
     */
    public static boolean isDateFormatOfSql(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(dateString); // parse dateString thành kiểu Date
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }

    /**
     * Method compare two date
     * 
     * @param dateStart
     * @param dateEnd
     * @return true if dateStart bigger dateEnd, false if not dateStart bigger dateEnd
     * @throws ParseException
     */
    public static boolean isDateStartBiggerDateEnd(String dateStart, String dateEnd) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.FORMAT_DATE_VIEW);
        java.util.Date date = new java.util.Date();
        if (dateEnd == "") {
            dateEnd = formatter.format(date);
        }
        java.util.Date dateOne;
        try {
            dateOne = formatter.parse(dateStart);

            java.util.Date dateTwo = formatter.parse(dateStart);
            Calendar calOne = Calendar.getInstance();
            calOne.setTime(dateOne);
            Calendar calTwo = Calendar.getInstance();
            calOne.setTime(dateTwo);
            if (calOne.get(Calendar.YEAR) > calTwo.get(Calendar.YEAR)) {
                return false;
            } else if (calOne.get(Calendar.YEAR) == calTwo.get(Calendar.YEAR)) {
                if (calOne.get(Calendar.MONTH) > calTwo.get(Calendar.MONTH)) {
                    return false;
                }
                if (calOne.get(Calendar.MONTH) == calTwo.get(Calendar.MONTH)
                        && calOne.get(Calendar.DATE) > calTwo.get(Calendar.DATE)) {
                    return false;
                }
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Method Validate Format Date
     * 
     * @param Birthdate
     * @return true if correct format, false if not correct format
     */
    public static boolean validateFormatDate(String birthdate) {
        if (birthdate.length() != 10) {
            return false;
        } else if (!isInteger(birthdate)) {
            return false;
        } else if (!isFormatDate(birthdate)) {
            return false;
        } else if (!isDateFormatOfSql(birthdate)) {
            return false;
        }
        return true;
    }

    /**
     * Method check validate insurance
     * 
     * @param insurance
     * @param user
     * @return
     */
    public static boolean isValidDateInsurance(Insurance insurance, User user) {
        if (isDateStartBiggerDateEnd(user.getBirthdateTypeString(), insurance.getInsuranceStartDateTypeString())
                && isDateStartBiggerDateEnd(insurance.getInsuranceStartDateTypeString(),
                        insurance.getInsuranceEndDateTypeString())) {
            return true;
        }
        return false;
    }

    /**
     * Method get Message Validate User
     * 
     * @param user
     * @return messageErrorValidateUser
     */
    public static List<String> messageErrorValidateUser(User user) {
        List<String> messageErrorValidateUser = new ArrayList<>();
        if (user.getUserFullName().isEmpty()) {
            messageErrorValidateUser.add(MessageProperties.getString(MessageCodeConstant.USER_ENTER_USER_FULL_NAME));
        }
        if (user.getBirthdateTypeString().isEmpty()) {
            messageErrorValidateUser.add(MessageProperties.getString(MessageCodeConstant.USER_ENTER_BIRTH_DAY_01));
        }
        if (!user.getBirthdateTypeString().isEmpty()) {
            if (!validateFormatDate(user.getBirthdateTypeString())) {
                messageErrorValidateUser.add(MessageProperties.getString(MessageCodeConstant.USER_ENTER_BIRTH_DAY_02));
            } else {
                if (isDateStartBiggerDateEnd(user.getBirthdateTypeString(), "")) {
                    messageErrorValidateUser
                            .add(MessageProperties.getString(MessageCodeConstant.USER_ENTER_BIRTH_DAY_03));
                }
            }
        }
        return messageErrorValidateUser;
    }

    /**
     * Method get Message Validate Company
     * 
     * @param company
     * @return messageErrorValidateCompany
     */
    public static List<String> messageErrorValidateCompany(Company company) {
        List<String> messageErrorValidateCompany = new ArrayList<>();
        if (company.getCompanyInternalId() != 0) {
            return messageErrorValidateCompany;
        } else {
            if (company.getCompanyName().isEmpty()) {
                messageErrorValidateCompany
                        .add(MessageProperties.getString(MessageCodeConstant.COMPANY_ENTER_COMPANY_NAME));
            }
            if (company.getAddress().isEmpty()) {
                messageErrorValidateCompany.add(MessageProperties.getString(MessageCodeConstant.COMPANY_ENTER_ADDRESS));
            }
            if (company.getEmail().isEmpty()) {
                messageErrorValidateCompany.add(MessageProperties.getString(MessageCodeConstant.COMPANY_ENTER_EMAIL));
            }
            if (company.getTelephone().isEmpty()) {
                messageErrorValidateCompany
                        .add(MessageProperties.getString(MessageCodeConstant.COMPANY_ENTER_TELEPHONE));
            }
            return messageErrorValidateCompany;
        }
    }

    /**
     * Method get Message Validate Insurance
     * 
     * @param insurance
     * @return messageErrorValidateInsurance
     * @throws SQLException
     */
    public static List<String> messageErrorValidateInsurance(Insurance insurance, User user) throws SQLException {
        InsuranceLogic insuranceLogic = new InsuranceLogicImpl();
        List<String> messageErrorValidateInsurance = new ArrayList<>();
        if (insurance.getInsuranceNumber().isEmpty()) {
            messageErrorValidateInsurance
                    .add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_INSURANCE_NUMBER_01));
        } else {
            if (insurance.getInsuranceNumber().length() != 10) {
                messageErrorValidateInsurance
                        .add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_INSURANCE_NUMBER_02));
            } else {
                if (insuranceLogic.isNotExistInsuranceNumber(insurance.getInsuranceNumber())) {
                    messageErrorValidateInsurance
                            .add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_INSURANCE_NUMBER_03));
                }
            }
        }
        if (insurance.getPlaceOfRegister().isEmpty()) {
            messageErrorValidateInsurance
                    .add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_PLACE_OF_REGISTER));
        }
        if (insurance.getInsuranceStartDateTypeString().isEmpty()
                || !validateFormatDate(insurance.getInsuranceStartDateTypeString())) {
            messageErrorValidateInsurance
                    .add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_START_DATE));
        }
        if (insurance.getInsuranceEndDateTypeString().isEmpty()
                || !validateFormatDate(insurance.getInsuranceEndDateTypeString())) {
            messageErrorValidateInsurance
                    .add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_END_DATE));
        }
        if (insurance.getInsuranceEndDateTypeString().isEmpty()
                && validateFormatDate(insurance.getInsuranceEndDateTypeString())
                && isValidDateInsurance(insurance, user)) {
            messageErrorValidateInsurance.add(MessageProperties.getString(MessageCodeConstant.INSURANCE_ENTER_DATE_02));
        }
        return messageErrorValidateInsurance;
    }

    /**
     * Method get message Error Validate
     * 
     * @param user
     * @param insurance
     * @param company
     * @return messageErrorValidate
     * @throws SQLException
     */
    public static List<String> messageErrorValidate(User user, Insurance insurance, Company company)
            throws SQLException {
        List<String> messageErrorValidate = new ArrayList<>();
        List<String> messageErrorValidateUser = messageErrorValidateUser(user);
        List<String> messageErrorValidateCompany = messageErrorValidateCompany(company);
        List<String> messageErrorValidateInsurance = messageErrorValidateInsurance(insurance, user);
        messageErrorValidate.addAll(messageErrorValidateUser);
        messageErrorValidate.addAll(messageErrorValidateCompany);
        messageErrorValidate.addAll(messageErrorValidateInsurance);
        return messageErrorValidate;
    }
}
