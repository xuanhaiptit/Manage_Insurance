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
import com.manage.insurance.logic.InsuranceLogic;
import com.manage.insurance.logic.UserLogic;
import com.manage.insurance.logic.impl.CompanyLogicImpl;
import com.manage.insurance.logic.impl.InsuranceLogicImpl;
import com.manage.insurance.logic.impl.UserLogicImpl;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.User;
import com.manage.insurance.model.UserInfo;
import com.manage.insurance.util.Validate;

/**
 * Servlet implementation class EditUserInfoController
 */
@WebServlet(urlPatterns = {Constants.EDIT_USER_INFO_CONTROLLER})
public class EditUserInfoController extends HttpServlet {

    private static final long serialVersionUID = 8069397917500838919L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            CompanyLogic companyLogic = new CompanyLogicImpl();
            UserLogic userLogic = new UserLogicImpl();
            InsuranceLogic insuranceLogic = new InsuranceLogicImpl();
            String typeAction = request.getParameter(Constants.TYPE_ACTION);
            if (typeAction.equals(Constants.TYPE_EDIT)) {
                int userInternalId = Integer.parseInt(request.getParameter(Constants.USER_INTERNAL_ID));
                if (userLogic.isUserInfo(userInternalId)) {
                    List<Company> listCompany = companyLogic.findAllCompany();
                    UserInfo userInfo = userLogic.getUserInfo(userInternalId);
                    User user = userLogic.getUserByUserInternalId(userInfo);
                    Company company = companyLogic.getCompanyByUserInternalId(userInfo);
                    Insurance insurance = insuranceLogic.getInsuranceByUserInternalId(userInfo);
                    request.setAttribute(Constants.RADIO_CHECKED, 1);
                    request.setAttribute(Constants.RADIO_DISPLAY, 1);
                    request.setAttribute(Constants.USER_SEX_DIVISION, user.getUserSexDivision());
                    request.setAttribute(Constants.USER_REQUEST, user);
                    request.setAttribute(Constants.COMPANY_REQUEST, company);
                    request.setAttribute(Constants.INSURANCE_REQUEST, insurance);
                    request.setAttribute(Constants.COMPANY_INTERNAL_ID, userInfo.getCompanyInternalId());
                    request.setAttribute(Constants.LIST_COMPANY_REQUEST, listCompany);
                }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH05);
                requestDispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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

            if (radioValue.equals(Constants.VALUE_RADIO_1)) { // click radio exist Company
                company.setCompanyInternalId(Integer.valueOf(companyInternalId));
                user.setCompanyInternalId(Integer.valueOf(companyInternalId));
                request.setAttribute(Constants.RADIO_CHECKED, 1);
                request.setAttribute(Constants.RADIO_DISPLAY, 1);
            } else { // click radio Not exist Company
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
            if (messageErrorValidate.size() != 0) {
                request.setAttribute(Constants.MESSAGE_ERROR_VALIDATE, messageErrorValidate);
                request.setAttribute(Constants.USER_REQUEST, user);
                request.setAttribute(Constants.COMPANY_REQUEST, company);
                request.setAttribute(Constants.INSURANCE_REQUEST, insurance);
                request.setAttribute(Constants.USER_SEX_DIVISION, userSexDivision);
                request.setAttribute(Constants.COMPANY_INTERNAL_ID, companyInternalId);
                request.setAttribute(Constants.LIST_COMPANY_REQUEST, listCompany);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH05);
                requestDispatcher.forward(request, response);
            } else {
                UserLogic userLogic = new UserLogicImpl();
                if (userLogic.updateUserInfo(user, company, insurance)) {
                    response.sendRedirect(request.getContextPath() + Constants.MH02_CONTROLLER + "?typeAction="
                            + Constants.GET_LIST_INSURANCE_DEFAULT + "&&toAction=" + Constants.EDIT_USER);
                }
            }
        } catch (SQLException e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }

}
