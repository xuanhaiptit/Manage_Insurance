package com.manage.insurance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;
import com.manage.insurance.util.Validate;

/**
 * Servlet implementation class LoginController
 * 
 * @author HaiLX
 */
@WebServlet(urlPatterns = {Constants.LOGIN_CONTROLLER})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = -8141957361734296063L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginName = request.getParameter(Constants.USER_NAME);
        String passWord = request.getParameter(Constants.PASS_WORD);

        try {
            ArrayList<String> messageError = (ArrayList<String>) Validate.messageErrorLogin(loginName, passWord);
            if (messageError.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute(Constants.LOGIN_SESSISON, loginName);
                response.sendRedirect(request.getContextPath() + Constants.MH02_CONTROLLER + "?typeAction="
                        + Constants.GET_LIST_INSURANCE_DEFAULT + "&&toAction=" + Constants.LOGIN);
            } else {
                request.setAttribute(Constants.MESSAGE_ERROR, messageError);
                request.setAttribute(Constants.USER_NAME, loginName);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH01);
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }
}
