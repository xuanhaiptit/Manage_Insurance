package com.manage.insurance.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;
import com.manage.insurance.properties.MessageProperties;

/**
 * Servlet implementation class SystemErrorController
 * 
 * @author HaiLX
 */
@WebServlet(urlPatterns = {Constants.ERROR_CONTROLLER})
public class SystemErrorController extends HttpServlet {

    private static final long serialVersionUID = 8482849406753558146L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemErrorController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter(Constants.TYPE_ACTION);
        if (Constants.SYSTEM_ERROR.equals(type)) {
            String msgError = MessageProperties.getString(Constants.MSG_ERROR);
            request.setAttribute(Constants.SYSTEM_ERROR, msgError);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_ERROR);
        dispatcher.forward(request, response);
    }
}
