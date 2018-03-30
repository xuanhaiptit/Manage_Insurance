package com.manage.insurance.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;
import com.manage.insurance.logic.UserLogic;
import com.manage.insurance.logic.impl.UserLogicImpl;
import com.manage.insurance.model.UserInfo;

/**
 * Servlet implementation class ShowUserController
 */
@WebServlet(urlPatterns = {Constants.MH03_SHOW_USER_INFO_CONTROLLER})
public class ShowUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserLogic userLogic = new UserLogicImpl();
            String typeAction = request.getParameter(Constants.TYPE_ACTION);
            if (typeAction.equals(Constants.SHOW_USER_INFO)) {
                int userInternalId = Integer.parseInt(request.getParameter(Constants.USER_INTERNAL_ID));
                if (userLogic.isUserInfo(userInternalId)) {
                    UserInfo userInfo = userLogic.getUserInfo(userInternalId);
                    request.setAttribute(Constants.USER_INFO_REQUEST, userInfo);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH03);
                    requestDispatcher.forward(request, response);
                }
            } ;
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
            UserLogic userLogic = new UserLogicImpl();
            String typeAction = request.getParameter(Constants.TYPE_ACTION);
            if (typeAction.equals(Constants.ACTION_DELETE)) {
                int userInternalId = Integer.parseInt(request.getParameter(Constants.USER_INTERNAL_ID));
                if (userLogic.deleteUser(userInternalId)) {
                    response.sendRedirect(request.getContextPath() + Constants.MH02_CONTROLLER + "?typeAction="
                            + Constants.GET_LIST_INSURANCE_DEFAULT + "&&toAction=" + Constants.DELETE_USER);
                }
            }
        } catch (SQLException e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }

}
