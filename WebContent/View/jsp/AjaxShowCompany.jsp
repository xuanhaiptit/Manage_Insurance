<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
    <table border="1" style="margin-left: 20px" cellpadding="0" cellspacing="0" id="table">
        <c:forEach var="company" items="${listCompany}">
            <c:choose>
                <c:when test="${company.companyInternalId == companyInternalId}">
                    <tr>
                        <td>Company Name</td>
                        <td><input type="text" value="<c:out value="${company.companyName}" escapeXml="true"/>"
                            size="25" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" value="<c:out value="${company.email}" escapeXml="true"/>" size="25" /></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" value="<c:out value="${company.address}" escapeXml="true"/>"
                            size="25" /></td>
                    </tr>
                    <tr>
                        <td>Telephone</td>
                        <td><input type="text" value="<c:out value="${company.telephone}" escapeXml="true"/>"
                            size="25" /></td>
                    </tr>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</body>
</html>