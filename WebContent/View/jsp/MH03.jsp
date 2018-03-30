<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="view/css/style.css" rel="stylesheet" type="text/css" />
<title>Insurance Card Management</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <form action="showUserInfo.do?typeAction=delete&&userInternalId=${userInfo.userInternalId}" method="post" name="inputform" onsubmit="return confirm('${mesageConfirm}');">
        <h3 style="margin-left: 30px">Detailed insurance card information</h3>
        <p style="margin-left: 30px">
            <input type="button" value="back" onclick="location.href='MH02.do?typeAction=back'" />
        </p>
        <table style="margin-left: 30px" class="tbl_input" border="1" width="400px" cellpadding="0" cellspacing="0">
            <tr>
                <td class="lbl_left" height="30">Insurance Number</td>
                <td align="left">${userInfo.insuranceNumber}</td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">User Full Name</td>
                <td align="left"><c:out value="${userInfo.userFullName}" escapeXml="true" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">User Sex Division</td>
                <td align="left"><c:if test="${userInfo.userSexDivision == 'M'}">
                                     <c:out value="Nam" />
                                 </c:if> 
                                 <c:if test="${userInfo.userSexDivision == 'F'}">
                                     <c:out value="Nu" />
                                 </c:if>
                </td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Birth Date</td>
                <td align="left"><fmt:formatDate value="${userInfo.birthdate}" pattern="dd-MM-yyyy" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Company Name</td>
                <td align="left">${userInfo.companyName}</td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Place Of Register</td>
                <td align="left">${userInfo.placeOfRegister}</td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Insurance Date</td>
                <td align="left"><fmt:formatDate value="${userInfo.insuranceStartDate}" pattern="dd-MM-yyyy" /> to 
                                 <fmt:formatDate value="${userInfo.insuranceEndDate}" pattern="dd-MM-yyyy" /></td>
            </tr>
        </table>
        <div style="padding-left: 30px;">
            <table border="0" cellpadding="4" cellspacing="0" width="300px">
                <tr>
                    <td align="center" height="30"><input type="submit" value="Delete" /></td>
                    <td><input type="button" value="Update"
                        onclick="location.href='editUserInfo.do?typeAction=edit&&userInternalId=${userInfo.userInternalId}'" /></td>
                </tr>
            </table>
            <p>
                <input type="hidden" value="${userInfo.userInternalId}" name="userId" />
            </p>
        </div>
    </form>
</body>
</html>