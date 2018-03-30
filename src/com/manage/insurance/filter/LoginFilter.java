
package com.manage.insurance.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.insurance.common.Common;
import com.manage.insurance.constant.Constants;
import com.manage.insurance.constant.ScreenConstant;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"*.do, *.jsp"})
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {}

    /**
     * @see Filter#destroy()
     */
    public void destroy() {}

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        String loginController = Constants.LOGIN_CONTROLLER; // /login.do
        if (!loginController.equals(servletPath)) {
            if (!Common.isLogin(session)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH01);
                requestDispatcher.forward(request, response);
            } else if (servletPath.contains(".jsp")) {
                response.sendRedirect(request.getContextPath() + Constants.MH02_CONTROLLER);
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (session != null) {
                chain.doFilter(req, resp);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ScreenConstant.SCREEN_MH01);
                requestDispatcher.forward(request, response);
            }
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
