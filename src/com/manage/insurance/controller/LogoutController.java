package com.manage.insurance.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;

/**
 * Servlet implementation class LogoutController
 * 
 * @author HaiLX
 */
@WebServlet(urlPatterns = {Constants.LOGOUT_CONTROLLER})
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 5132695773130379221L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH01);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
        }
    }
}
