package com.manage.insurance.model;

import java.sql.Date;

/**
 * Entity user in Table User
 * 
 * @author lexuanhai
 *
 */
public class User {
    private int userInternalId;
    private String userFullName;
    private String userSexDivision;
    private Date birthdate;
    private Company company;
    private Insurance insurance;
    private String birthdateTypeString;
    private int companyInternalId;
    private int insuranceInternalId;

    public User() {}

    public User(int userInternalId, String userFullName, String userSexDivision, Date birthdate, Company company,
            Insurance insurance) {
        super();
        this.userInternalId = userInternalId;
        this.userFullName = userFullName;
        this.userSexDivision = userSexDivision;
        this.birthdate = birthdate;
        this.company = company;
        this.insurance = insurance;
    }

    public int getUserInternalId() {
        return userInternalId;
    }

    public void setUserInternalId(int userInternalId) {
        this.userInternalId = userInternalId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserSexDivision() {
        return userSexDivision;
    }

    public void setUserSexDivision(String userSexDivision) {
        this.userSexDivision = userSexDivision;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    /**
     * @return the birthdateTypeString
     */
    public String getBirthdateTypeString() {
        return birthdateTypeString;
    }

    /**
     * @param birthdateTypeString the birthdateTypeString to set
     */
    public void setBirthdateTypeString(String birthdateTypeString) {
        this.birthdateTypeString = birthdateTypeString;
    }

    /**
     * @return the companyInternalId
     */
    public int getCompanyInternalId() {
        return companyInternalId;
    }

    /**
     * @param companyInternalId the companyInternalId to set
     */
    public void setCompanyInternalId(int companyInternalId) {
        this.companyInternalId = companyInternalId;
    }

    /**
     * @return the insuranceInternalId
     */
    public int getInsuranceInternalId() {
        return insuranceInternalId;
    }

    /**
     * @param insuranceInternalId the insuranceInternalId to set
     */
    public void setInsuranceInternalId(int insuranceInternalId) {
        this.insuranceInternalId = insuranceInternalId;
    }

}
