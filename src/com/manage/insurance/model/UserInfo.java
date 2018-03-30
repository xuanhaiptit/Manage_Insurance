package com.manage.insurance.model;

import java.sql.Date;

/**
 * DTO UserInfo
 * 
 * @author lexuanhai
 *
 */
public class UserInfo {
    private int userInternalId;
    private int companyInternalId;
    private int insuranceInternalId;
    private String userFullName;
    private String userSexDivision;
    private Date birthdate;
    private String insuranceNumber;
    private Date insuranceStartDate;
    private Date insuranceEndDate;
    private String placeOfRegister;
    private String companyName;
    private String address;
    private String email;
    private String telephone;

    public UserInfo(int userInternalId, String userFullName, String userSexDivision, Date birthdate,
            String insuranceNumber, Date insuranceStartDate, Date insuranceEndDate, String placeOfRegister,
            String companyName, String address, String email, String telephone) {
        super();
        this.userInternalId = userInternalId;
        this.userFullName = userFullName;
        this.userSexDivision = userSexDivision;
        this.birthdate = birthdate;
        this.insuranceNumber = insuranceNumber;
        this.insuranceStartDate = insuranceStartDate;
        this.insuranceEndDate = insuranceEndDate;
        this.placeOfRegister = placeOfRegister;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }

    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the userInternalId
     */
    public int getUserInternalId() {
        return userInternalId;
    }

    /**
     * @param userInternalId the userInternalId to set
     */
    public void setUserInternalId(int userInternalId) {
        this.userInternalId = userInternalId;
    }

    /**
     * @return the userFullName
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * @param userFullName the userFullName to set
     */
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    /**
     * @return the userSexDivision
     */
    public String getUserSexDivision() {
        return userSexDivision;
    }

    /**
     * @param userSexDivision the userSexDivision to set
     */
    public void setUserSexDivision(String userSexDivision) {
        this.userSexDivision = userSexDivision;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the insuranceNumber
     */
    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    /**
     * @param insuranceNumber the insuranceNumber to set
     */
    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    /**
     * @return the insuranceStartDate
     */
    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    /**
     * @param insuranceStartDate the insuranceStartDate to set
     */
    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    /**
     * @return the insuranceEndDate
     */
    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    /**
     * @param insuranceEndDate the insuranceEndDate to set
     */
    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    /**
     * @return the placeOfRegister
     */
    public String getPlaceOfRegister() {
        return placeOfRegister;
    }

    /**
     * @param placeOfRegister the placeOfRegister to set
     */
    public void setPlaceOfRegister(String placeOfRegister) {
        this.placeOfRegister = placeOfRegister;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
