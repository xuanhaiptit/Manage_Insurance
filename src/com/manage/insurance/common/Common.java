package com.manage.insurance.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manage.insurance.constant.Constants;
import com.manage.insurance.model.Company;
import com.manage.insurance.model.Insurance;
import com.manage.insurance.model.User;
import com.manage.insurance.model.UserInfo;
import com.manage.insurance.properties.ConfigMysql;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * Class Common Contains sub-mothod handlers for the project
 * 
 * @author lexuanhai
 *
 */
public class Common {

    /**
     * Method check data isEmpty
     * 
     * @param str String to check
     * @return true if isEmpty false if notisEmpty
     */
    public static boolean isEmpty(String str) {
        if (str.isEmpty() || str == null) {
            return true;
        }
        return false;
    }

    /**
     * Encrypt specified signature string by MD5 algorithm
     * 
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String cryptMD5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Check Session Login
     * 
     * @param session of HttpSession
     * @return true if isLogin, false if Not is login
     */
    public static boolean isLogin(HttpSession session) {
        if (session.getAttribute(Constants.LOGIN_SESSISON) != null) {
            return true;
        }
        return false;
    }

    /**
     * Method percent Increase to String
     * 
     * @param str String
     * @return String percent
     */
    public static String changeStringIfNull(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    /**
     * Character conversion function
     * 
     * @param str String
     * @turn string str
     */
    public static String placeWildCard(String str) {
        str = str.replace("\\", "\\\\");
        str = str.replace("%", "\\%");
        str = str.replace("_", "//%");
        return str;
    }

    /**
     * Hàm tính toán logic để tạo ra các trang cần hiển thị ở chuỗi paging theo trang hiện tại
     * 
     * @param totalUser tổng sô user
     * @param limit số lượng cần hiển thị trên 1 trang
     * @param currentPage trang hiện tại
     * @return List<Integer> Danh sách các trang cần hiển thị ở chuỗi paging theo trang hiện tại
     * 
     */

    public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
        List<Integer> listPage = new ArrayList<>();
        int totalPage = 0;
        int limitPage = getLimitPage(); // số đánh số trang có thể hiển thị trên view (1|2|3)
        int getCurrentRange = (currentPage - 1) / limitPage; // số thứ tự dãy hiện tại( 1|2|3: STT 0; 4|5|6: STT 1)
        int pagingFirst = getCurrentRange * limitPage + 1; // số đánh trang đầu hiện sẽ hiện (1, 4, 7)
        if (totalUser % limit == 0) {
            totalPage = totalUser / limit; // tìm số trang, trường hợp chẵn
        } else {
            totalPage = totalUser / limit + 1; // tìm số trang, trường hợp lẻ
        }
        if (pagingFirst + limitPage > totalPage) { // nếu trường hơp số limitPage lớn hơn số trang có thể hiện
            limitPage = totalPage - pagingFirst; // xét lại limitPage
        } else {
            limitPage = limitPage - 1;
        }
        for (int i = pagingFirst; i <= pagingFirst + limitPage; i++) {
            listPage.add(i);
        }
        return listPage;
    }

    /**
     * Xử lý logic để tính ra vị trí cần lấy dựa và currentPage và limit
     * 
     * @param currentPage Vị trí Trang hiện tại
     * @param limit số lượng cần hiển thị trên 1 trang
     * @return vị trí cần lấy
     */
    public static int getOffset(int currentPage, int limit) {
        int offset = (currentPage - 1) * limit;
        return offset;
    }

    /**
     * Lấy số lượng hiển thị LimitPage (số trang có thể click) trên 1 trang từ file config.properties
     * 
     * @return int số lượng recorscần lấy
     */

    public static int getLimitPage() {
        // lấy số trang có thể click tại propeties
        int limitPage = Integer.parseInt(ConfigMysql.getString("limitPage"));
        return limitPage;
    }

    /**
     * Tính tổng số trang
     * 
     * @param totalUser tổng số User
     * @param limit số lượng cần hiển thị trên 1 trang
     * @return tổng số trang
     */

    public static int getTotalPage(int totalUser, int limit) {
        int totalPage = 0;
        if (totalUser % limit == 0) {
            totalPage = totalUser / limit; // tìm số trang, trường hợp chẵn
        } else {
            totalPage = totalUser / limit + 1; // tìm số trang, trường hợp lẻ
        }
        return totalPage;
    }

    /**
     * Method standardized String
     * 
     * @param str
     * @return string after standardized
     */
    public static String standardizedString(String str) {
        str = str.toLowerCase();
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    /**
     * Method standard Personalized Name
     * 
     * @param str
     * @return String after standard Personalized
     */
    public static String standardPersonalizedName(String str) {
        str = deleteExtraCharacters(str);
        str = standardizedString(str);
        String temp[] = str.split(" ");
        str = ""; // ? ^-^
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) // ? ^-^
                str += " ";
        }
        return str;
    }

    /**
     * Method delete Extra Characters
     * 
     * @param str
     * @return str extra characters
     */
    public static String deleteExtraCharacters(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        str = pattern.matcher(str).replaceAll("");
        str = str.replaceAll("[^A-Za-z. ]", "");
        return str;
    }

    /**
     * Method get value user after formatting
     * 
     * @param user
     * @return user after formatting
     */
    public static User standardPersonalizedUser(User user) {
        user.setUserFullName(standardPersonalizedName(user.getUserFullName()));
        user.setBirthdate(Date.valueOf(user.getBirthdateTypeString()));
        return user;
    }

    /**
     * Method standard Personalized Company
     * 
     * @param company
     * @return Company after formatting
     */
    public static Company standardPersonalizedCompany(Company company) {
        company.setCompanyName(standardPersonalizedName(company.getCompanyName()));
        company.setAddress(standardPersonalizedName(company.getAddress()));
        return company;
    }

    /**
     * Method standard Personalized Insurance
     * 
     * @param insurance
     * @return Insurance after formatting
     */
    public static Insurance standardPersonalizedInsurance(Insurance insurance) {
        insurance.setPlaceOfRegister(standardPersonalizedName(insurance.getPlaceOfRegister()));
        insurance.setInsuranceStartDate(Date.valueOf(insurance.getInsuranceStartDateTypeString()));
        insurance.setInsuranceEndDate(Date.valueOf(insurance.getInsuranceEndDateTypeString()));
        return insurance;
    }

    /**
     * The handler converts Object data to toString
     *
     * @param object object passed in
     * @param type key to see which object it belongs to
     * @return String string needs to convert
     */
    public static String convertToStringObject(Object object) {
        StringBuilder str = new StringBuilder();
        UserInfo info = new UserInfo();
        info = (UserInfo) object;
        str.append(info.getUserFullName() + "," + info.getUserSexDivision());
        str.append("," + info.getBirthdate() + ", " + info.getInsuranceNumber() + "," + info.getInsuranceStartDate());
        str.append("," + info.getInsuranceEndDate() + ", " + info.getPlaceOfRegister());
        return str.toString();
    }

    /**
     * The function retrieves the data from the object into a String
     *
     * @param data object data to transfer
     * @return string data string
     */
    public static String getDataExport(List<?> data) {
        StringBuilder builder = new StringBuilder();
        for (Object object : data) {
            builder.append(Common.convertToStringObject(object) + "\n");
        }
        return builder.toString();
    }

    /**
     * Send data to csv file
     *
     * @param response HttpServletResponse
     * @param listData lists the required data for the file
     * @param type data type will retrieve
     * @param filename wants to name the file
     * @throws IOException
     *
     */
    public static void writerDataInFile(HttpServletResponse response, List<?> listData) throws IOException {
        String str = getDataExport(listData);
        response.setContentType("text/csv");
        String reportName = Constants.FILE_NAME_EXPORT + System.currentTimeMillis() + ".csv";
        response.setHeader("Content-disposition", "attachment; " + "filename=" + reportName);
        response.getWriter().write(str);
    }

    /**
     * Method write Data In File PDF
     * 
     * @param response
     * @param pdfReportStream
     */
    public static void writeDataInFilePDF(HttpServletResponse response, ByteArrayOutputStream pdfReportStream) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
        String reportName = Constants.FILE_NAME_EXPORT + System.currentTimeMillis() + ".pdf";
        response.setHeader("Content-disposition", "attachment; " + "filename=" + reportName);
    }

    /**
     * Method get Company By CompanyItenalId
     * 
     * @param companyInternalId
     * @param listCompany
     * @return company
     */
    public static Company getCompanyByCompanyItenalId(String companyInternalId, List<Company> listCompany) {
        Company company = new Company();
        for (Company dataCompany : listCompany) {
            if (dataCompany.getCompanyInternalId() == Integer.parseInt(companyInternalId)) {
                company = dataCompany;
                break;
            }
        }
        return company;
    }

    /**
     * Method put Company In Map
     * 
     * @param map
     * @param company
     * @return map
     */
    public static Map<String, Object> putCompanyInMap(Map<String, Object> map, Company company) {
        map.put(Constants.KEY_JASPER_REPORT_COMPANY_NAME, company.getCompanyName());
        map.put(Constants.KEY_JASPER_REPORT_ADDRESS, company.getAddress());
        map.put(Constants.KEY_JASPER_REPORT_EMAIL, company.getEmail());
        map.put(Constants.KEY_JASPER_REPORT_TELEPHONE, company.getTelephone());
        return map;
    }

    /**
     * Method set Japer print
     * 
     * @param fileJasperReport
     * @param map
     * @return jasperPrint
     * @throws IOException
     * @throws JRException
     */
    public static JasperPrint setJaperPrint(String fileJasperReport, Map<String, Object> map)
            throws IOException, JRException {
        InputStream jrxmlInput = Common.class.getClassLoader().getResource(fileJasperReport).openStream();
        JasperDesign design = JRXmlLoader.load(jrxmlInput);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JRDataSource jrDataSource = new JREmptyDataSource();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, jrDataSource);
        return jasperPrint;
    }
}
