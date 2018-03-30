package com.manage.insurance.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;
import com.manage.insurance.logic.CompanyLogic;
import com.manage.insurance.logic.UserLogic;
import com.manage.insurance.logic.impl.CompanyLogicImpl;
import com.manage.insurance.logic.impl.UserLogicImpl;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.User;
import com.manage.insurance.util.Validate;

/**
 * Servlet implementation class AddUserInputController
 */
@WebServlet(urlPatterns = {Constants.MH03_ADD_USER_INFO_CONTROLLER})
public class AddUserInputController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserInputController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String screenView = "";
            String typeAction = request.getParameter(Constants.TYPE_ACTION);
            CompanyLogic companyLogic = new CompanyLogicImpl();
            List<Company> listCompany = companyLogic.findAllCompany();
            String companyInternalId = "";
            companyInternalId = request.getParameter(Constants.COMPANY_INTERNAL_ID);
            if (typeAction.equals(Constants.ACTION_AJAX_COMPANY)) {
                screenView = ScreenConstant.SCREEN_MH04_01;
            } else {

                request.setAttribute(Constants.RADIO_CHECKED, 1);
                request.setAttribute(Constants.RADIO_DISPLAY, 1);
                request.setAttribute(Constants.USER_SEX_DIVISION, Constants.MALE);
                request.setAttribute(Constants.LIST_COMPANY_REQUEST, listCompany);
                screenView = ScreenConstant.SCREEN_MH04;
            }
            request.setAttribute(Constants.COMPANY_INTERNAL_ID, companyInternalId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(screenView);
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String companyName = "";
            String address = "";
            String email = "";
            String telephone = "";
            List<String> messageErrorValidate = new ArrayList<>();
            User user = new User();
            Company company = new Company();
            Insurance insurance = new Insurance();
            CompanyLogic companyLogic = new CompanyLogicImpl();
            List<Company> listCompany = companyLogic.findAllCompany();
            // lay cac request tu man hinh
            String insuranceNumber = request.getParameter(Constants.INSURANCE_NUMBER);
            String userFullName = request.getParameter(Constants.USER_FULL_NAME);
            String userSexDivision = request.getParameter(Constants.USER_SEX_DIVISION);
            String birthdate = request.getParameter(Constants.BIRTH_DATE);
            String companyInternalId = request.getParameter(Constants.COMPANY_INTERNAL_ID);
            String radioValue = request.getParameter(Constants.RADIO_VALUE);
            String placeOfRegister = request.getParameter(Constants.PLACE_OF_REGISTER);
            String insuranceStartDate = request.getParameter(Constants.INSURANCE_START_DATE);
            String insuranceEndDate = request.getParameter(Constants.INSURANCE_END_DATE);
            if (radioValue.equals(Constants.VALUE_RADIO_1)) { // click radio isCompany
                company.setCompanyInternalId(Integer.valueOf(companyInternalId));
                user.setCompanyInternalId(Integer.valueOf(companyInternalId));
                request.setAttribute(Constants.RADIO_CHECKED, 1);
                request.setAttribute(Constants.RADIO_DISPLAY, 1);
            } else if (radioValue.equals(Constants.VALUE_RADIO_2)) { // click radio Not is Company
                user.setCompanyInternalId(0);
                company.setCompanyInternalId(0);
                companyName = request.getParameter(Constants.COMPANY_NAME);
                address = request.getParameter(Constants.ADDRESS);
                email = request.getParameter(Constants.EMAIL);
                telephone = request.getParameter(Constants.TELEPHONE);
                request.setAttribute(Constants.RADIO_CHECKED, 0);
                request.setAttribute(Constants.RADIO_DISPLAY, 0);
            }
            // set data in table
            user.setUserFullName(userFullName);
            user.setUserSexDivision(userSexDivision);
            user.setBirthdateTypeString(birthdate);
            company.setCompanyName(companyName);
            company.setAddress(address);
            company.setEmail(email);
            company.setTelephone(telephone);
            insurance.setInsuranceNumber(insuranceNumber);
            insurance.setInsuranceStartDateTypeString(insuranceStartDate);
            insurance.setInsuranceEndDateTypeString(insuranceEndDate);
            insurance.setPlaceOfRegister(placeOfRegister);
            messageErrorValidate = Validate.messageErrorValidate(user, insurance, company);
            request.setAttribute(Constants.COMPANY_INTERNAL_ID, companyInternalId);
            request.setAttribute(Constants.LIST_COMPANY_REQUEST, listCompany);
            if (messageErrorValidate.size() != 0) {
                request.setAttribute(Constants.MESSAGE_ERROR_VALIDATE, messageErrorValidate);
                request.setAttribute(Constants.USER_REQUEST, user);
                request.setAttribute(Constants.COMPANY_REQUEST, company);
                request.setAttribute(Constants.INSURANCE_REQUEST, insurance);
                request.setAttribute(Constants.USER_SEX_DIVISION, userSexDivision);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH04);
                requestDispatcher.forward(request, response);
            } else {
                UserLogic userLogic = new UserLogicImpl();
                if (userLogic.addUserInfo(user, company, insurance)) {
                    response.sendRedirect(request.getContextPath() + Constants.MH02_CONTROLLER + "?typeAction="
                            + Constants.GET_LIST_INSURANCE_DEFAULT + "&&toAction=" + Constants.ADD_USER);
                }
            }
        } catch (SQLException e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }
}
