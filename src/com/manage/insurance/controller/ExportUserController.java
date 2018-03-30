package com.manage.insurance.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.insurance.common.Common;
import com.manage.insurance.constant.Constants;
import com.manage.insurance.logic.CompanyLogic;
import com.manage.insurance.logic.UserLogic;
import com.manage.insurance.logic.impl.CompanyLogicImpl;
import com.manage.insurance.logic.impl.UserLogicImpl;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.UserInfo;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * Servlet implementation class ExportUserController
 */
@WebServlet(urlPatterns = {Constants.EXPORT_USER_CONTROLLER})
public class ExportUserController extends HttpServlet {

    private static final long serialVersionUID = -3437328703252929002L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("deprecation")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            UserLogic userLogic = new UserLogicImpl();
            CompanyLogic companyLogic = new CompanyLogicImpl();
            List<Company> listCompany = companyLogic.findAllCompany();
            int companyinternalId = Integer.parseInt((String) session.getAttribute(Constants.COMPANY_INTERNAL_ID));
            String userFullName = (String) session.getAttribute(Constants.USER_FULL_NAME);
            String insuranceNumber = (String) session.getAttribute(Constants.INSURANCE_NUMBER);
            String placeOfRegister = (String) session.getAttribute(Constants.PLACE_OF_REGISTER);
            String typeAction = request.getParameter(Constants.TYPE_ACTION);
            List<UserInfo> listUserInfo =
                    userLogic.getListUserInfoExport(companyinternalId, userFullName, insuranceNumber, placeOfRegister);
            if (typeAction.equals(Constants.ACTION_EXPORT_CSV)) {
                Common.writerDataInFile(response, listUserInfo);
            } else if (typeAction.equals(Constants.ACTION_EXPORT_PDF)) {

                Map<String, Object> map = new HashMap<String, Object>();
                Company company = Common.getCompanyByCompanyItenalId(companyinternalId + "", listCompany);
                map = Common.putCompanyInMap(map, company);
                map.put(Constants.KEY_JASPER_REPORT_TABLE_USER_INFO, new JRBeanCollectionDataSource(listUserInfo));
                String fileJasperReport = Constants.FILE_MANAGE_INSURANCE_JRXML;
                JasperPrint jasperPrint = Common.setJaperPrint(fileJasperReport, map);
                JRPdfExporter pdfExporter = new JRPdfExporter();
                pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
                pdfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
                pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
                pdfExporter.exportReport();
                Common.writeDataInFilePDF(response, pdfReportStream);
                OutputStream responseOutputStream = response.getOutputStream();
                responseOutputStream.write(pdfReportStream.toByteArray());
                responseOutputStream.close();
                pdfReportStream.close();
            }
        } catch (Exception e) {
            response.sendRedirect(Constants.ERROR_CONTROLLER + "?typeAction=" + Constants.SYSTEM_ERROR);
            e.printStackTrace();
        }
    }
}
