package com.manage.insurance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.insurance.common.Common;
import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;
import com.manage.insurance.logic.CompanyLogic;
import com.manage.insurance.logic.UserLogic;
import com.manage.insurance.logic.impl.CompanyLogicImpl;
import com.manage.insurance.logic.impl.UserLogicImpl;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.UserInfo;
import com.manage.insurance.properties.ConfigMysql;
import com.manage.insurance.properties.MessageProperties;

/**
 * Servlet implementation class ListUserController
 * 
 * @author HaiLX
 */
@WebServlet(urlPatterns = {Constants.MH02_CONTROLLER})
public class ListUserController extends HttpServlet {
    private static final long serialVersionUID = -2155529933826027299L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUserController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int offset = 0;
            String messageNoRecords = MessageProperties.getString(Constants.MSG01_NO_RECORDS);
            String messageActionSuccess = "";
            int limit = Integer.parseInt(ConfigMysql.getString(Constants.LIMIT));

            int totalPaging;
            int pagingFirst;
            int limitPage = Common.getLimitPage();
            int currentPage = 1;
            int companyInternalId = 0;
            CompanyLogic companyLogic = new CompanyLogicImpl();
            List<UserInfo> listUserInfo = new ArrayList<>();
            UserLogic userLogic = new UserLogicImpl();
            HttpSession session = request.getSession();
            String typeAction = request.getParameter(Constants.TYPE_ACTION);
            List<Company> listCompany = companyLogic.findAllCompany();
            String companyinternalIdTypeString = (String) session.getAttribute(Constants.COMPANY_INTERNAL_ID);
            if (companyinternalIdTypeString != null) {
                companyInternalId = Integer.parseInt(companyinternalIdTypeString);
            }
            String userFullName = (String) session.getAttribute(Constants.USER_FULL_NAME);
            String insuranceNumber = (String) session.getAttribute(Constants.INSURANCE_NUMBER);
            String placeOfRegister = (String) session.getAttribute(Constants.PLACE_OF_REGISTER);
            String typeSortOfUserFullName = (String) session.getAttribute(Constants.TYPE_SORT);
            if (typeAction.equals(Constants.GET_LIST_INSURANCE_DEFAULT)) { // MH01, MH04, MH05 sang
                companyInternalId = listCompany.get(0).getCompanyInternalId();
                typeSortOfUserFullName = Constants.ASC;
                userFullName = "";
                insuranceNumber = "";
                placeOfRegister = "";
                String toAction = request.getParameter(Constants.TO_ACTION);
                if (toAction.equals(Constants.DELETE_USER)) {
                    messageActionSuccess = MessageProperties.getString(Constants.MSG02_DELETE_SUCCESS);
                } else if (toAction.equals(Constants.ADD_USER)) {
                    messageActionSuccess = MessageProperties.getString(Constants.MSG03_ADD_SUCCESS);
                } else if (toAction.equals(Constants.EDIT_USER)) {
                    messageActionSuccess = MessageProperties.getString(Constants.MSG03_EDIT_SUCCESS);
                }
            } else if (typeAction.equals(Constants.ACTION_SEARCH_BUTTON)) { // click Button Search
                companyInternalId = Integer.parseInt(request.getParameter(Constants.COMPANY_INTERNAL_ID));
                userFullName = request.getParameter(Constants.USER_FULL_NAME);
                insuranceNumber = request.getParameter(Constants.INSURANCE_NUMBER);
                placeOfRegister = request.getParameter(Constants.PLACE_OF_REGISTER);
            } else if (typeAction.equals(Constants.ACTION_SORT)) { // click sort
                if (typeSortOfUserFullName.equals(Constants.ASC)) {
                    typeSortOfUserFullName = Constants.DESC;
                } else {
                    typeSortOfUserFullName = Constants.ASC;
                }
            } else if (typeAction.equals(Constants.ACTION_PAGE)) { // click page
                currentPage = Integer.parseInt(request.getParameter(Constants.NUMBER_OF_PAGES));
                companyInternalId = Integer.parseInt((String) session.getAttribute(Constants.COMPANY_INTERNAL_ID));
            } else if (typeAction.equals(Constants.ACTION_BACK)) { // click back
                currentPage = (int) session.getAttribute(Constants.CURRENT_PAGE);
            }
            int totalUser = userLogic.getTotalUserInfo(companyInternalId, userFullName, insuranceNumber,
                    placeOfRegister, typeSortOfUserFullName);
            if (totalUser == 0) {
                request.setAttribute(Constants.MSG01_NO_RECORDS, messageNoRecords);
            } else {
                List<Integer> listPaging = Common.getListPaging(totalUser, limit, currentPage);
                totalPaging = Common.getTotalPage(totalUser, limit);
                offset = Common.getOffset(currentPage, limit);
                pagingFirst = listPaging.get(0);
                listUserInfo = userLogic.getListUserInfo(companyInternalId, userFullName, insuranceNumber,
                        placeOfRegister, typeSortOfUserFullName, offset, limit);
                request.setAttribute(Constants.PAGING_FIRST, pagingFirst);
                request.setAttribute(Constants.TOTAL_PAGING, totalPaging);
                request.setAttribute(Constants.LIST_PAGING, listPaging);
                request.setAttribute(Constants.MESSAGE_ACTION_SUCCESS, messageActionSuccess);
                session.setAttribute(Constants.LIMIT_PAGE, limitPage);
                session.setAttribute(Constants.CURRENT_PAGE, currentPage);
                session.setAttribute(Constants.TOTAL_USER, totalUser);
                session.setAttribute(Constants.LIMIT, totalUser);
                session.setAttribute(Constants.LIST_COMPANY_REQUEST, listCompany);
                session.setAttribute(Constants.TYPE_SORT, typeSortOfUserFullName);
                request.setAttribute(Constants.LIST_USER_INFO_REQUEST, listUserInfo);
            }
            session.setAttribute(Constants.COMPANY_INTERNAL_ID, companyInternalId + "");
            session.setAttribute(Constants.USER_FULL_NAME, userFullName);
            session.setAttribute(Constants.INSURANCE_NUMBER, insuranceNumber);
            session.setAttribute(Constants.PLACE_OF_REGISTER, placeOfRegister);;
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH02);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }
}
