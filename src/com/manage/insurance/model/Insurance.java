package com.manage.insurance.model;

import java.sql.Date;

/**
 * Entity Insurance in table Insurances
 * 
 * @author lexuanhai
 *
 */
public class Insurance {
    private int insuranceInternalId;
    private String insuranceNumber;
    private Date insuranceStartDate;
    private Date insuranceEndDate;
    private String placeOfRegister;
    private String insuranceStartDateTypeString;
    private String insuranceEndDateTypeString;

    public int getInsuranceInternalId() {
        return insuranceInternalId;
    }

    public void setInsuranceInternalId(int insuranceInternalId) {
        this.insuranceInternalId = insuranceInternalId;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public String getPlaceOfRegister() {
        return placeOfRegister;
    }

    public void setPlaceOfRegister(String placeOfRegister) {
        this.placeOfRegister = placeOfRegister;
    }

    /**
     * @return the insuranceStartDateTypeString
     */
    public String getInsuranceStartDateTypeString() {
        return insuranceStartDateTypeString;
    }

    /**
     * @param insuranceStartDateTypeString the insuranceStartDateTypeString to set
     */
    public void setInsuranceStartDateTypeString(String insuranceStartDateTypeString) {
        this.insuranceStartDateTypeString = insuranceStartDateTypeString;
    }

    /**
     * @return the insuranceEndDateTypeString
     */
    public String getInsuranceEndDateTypeString() {
        return insuranceEndDateTypeString;
    }

    /**
     * @param insuranceEndDateTypeString the insuranceEndDateTypeString to set
     */
    public void setInsuranceEndDateTypeString(String insuranceEndDateTypeString) {
        this.insuranceEndDateTypeString = insuranceEndDateTypeString;
    }

}
