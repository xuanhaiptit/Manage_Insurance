<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="view/css/style.css" rel="stylesheet" type="text/css" />
<title>Insurance Card Management</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="View/js/jquery-1.10.2.js"></script>
<script src="View/js/jquery-ui.js"></script>
<link rel="stylesheet" href="View/css/jquery-ui.css" />
<script src="View/js/script.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    <form action="editUserInfo" method="post">
        <table border="0" width="500px" cellpadding="0" cellspacing="0" style="margin-left: 100px">
            <tr>
                <td align="left" colspan="2">
                    <h2>Edit Information Insurance Card</h2>
                </td>
            </tr>
            <c:forEach var="item" items="${messageErrorValidate}">
                <tr>
                    <td class="errMsg" colspan="2">
                        <div style="padding-left: 120px">
                            <c:out value="${item}" />
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td class="lbl_left" height="30">Insurance Number</td>
                <td align="left"><input type="text" name="insuranceNumber"
                    value="<c:out value="${insurance.insuranceNumber}" escapeXml="true"/>" size="20" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">User Full Name</td>
                <td align="left"><input type="text" name="userFullName"
                    value="<c:out value="${user.userFullName}" escapeXml="true"/>" size="20" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">User Sex Division</td>
                <td align="left"><select name="userSexDivision">
                        <option value="M" ${userSexDivision=='M' ? 'selected = "selected"' : ''}>Male</option>
                        <option value="F" ${userSexDivision=='F' ? 'selected = "selected"' : ''}>Female</option>
                </select></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Birth Date</td>
                <td align="left"><input type="text" id="datepicker_birthDate" name="birthdate"
                    value="<c:out value="${user.birthdateTypeString}" escapeXml="true"/>" size="25" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Company</td>
                <td align="left" height="30"><input name="radioValue" type="radio" value="1"
                    onclick="showhidediv(this);" ${radioChecked==1 ? 'checked = "checked"' : ''} /> The is Company<br />
                    <div id="one" class="CF" style="display:${radioDisplay==1 ? 'block' : 'none'};">
                        <select style="margin: 10px" name="companyInternalId">
                            <c:forEach var="company" items="${listCompany}">
                                <c:choose>
                                    <c:when test="${company.companyInternalId == companyInternalId}">
                                        <option value="${company.companyInternalId}" selected="selected">
                                            ${company.companyName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${company.companyInternalId}">${company.companyName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <table border="1" style="margin-left: 20px" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>Company Name</td>
                                <td><input type="text" value="<c:out value="aaa" escapeXml="true"/>" size="25" /></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><input type="text" value="<c:out value="bbb" escapeXml="true"/>" size="25" /></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>aa</td>
                            </tr>
                            <tr>
                                <td>Mobile</td>
                                <td>aa</td>
                            </tr>
                        </table>
                    </div> <input name="radioValue" type="radio" value="2" onclick="showhidediv(this);"
                    ${radioChecked==0 ? 'checked = "checked"' : ''} /> Register Company New
                    <div id="two" style="display:${radioDisplay==0 ? 'block' : 'none'};" class="CF">
                        <table border="1" style="margin-left: 20px" cellpadding="0" cellspacing="0">
                            <tr style="margin-left: 100px">
                                <td>Company Name</td>
                                <td><input type="text" name="companyName"
                                    value="<c:out value="${company.companyName}" escapeXml="true"/>" size="25" /></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><input type="text" name="address"
                                    value="<c:out value="${company.address}" escapeXml="true"/>" size="25" /></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="email"
                                    value="<c:out value="${company.email}" escapeXml="true"/>" size="25" /></td>
                            </tr>
                            <tr>
                                <td>Mobile</td>
                                <td><input type="text" name="telephone"
                                    value="<c:out value="${company.telephone}" escapeXml="true"/>" size="25" /></td>
                            </tr>
                        </table>
                    </div></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Place Of Register</td>
                <td align="left"><input type="text" name="placeOfRegister"
                    value="<c:out value="${insurance.placeOfRegister}" escapeXml="true"/>" size="25" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Insurance Start Date</td>
                <td align="left"><input type="text" name="insuranceStartDate" id="datepicker_insuranceStartDate"
                    value="<c:out value="${insurance.insuranceStartDateTypeString}" escapeXml="true"/>" size="25" /></td>
            </tr>
            <tr>
                <td class="lbl_left" height="30">Insurance End Date</td>
                <td align="left"><input type="text" id="datepicker_insuranceEndDate" name="insuranceEndDate"
                    value="<c:out value="${insurance.insuranceEndDateTypeString}" escapeXml="true"/>" size="25" /></td>
            </tr>
        </table>
        <input type="hidden" value="" name="userId" />
        <%--         <c:set var="url" value="showUserInfo.do?typeAction=showUserInfo&&userInternalId=${user.userInternalId}" /> --%>
        <div style="padding-left: 45px;">
            <table border="0" cellpadding="4" cellspacing="0">
                <tr>
                    <td><input type="button" value="Back"
                        onclick="location.href='showUserInfo.do?typeAction=showUserInfo&&userInternalId=${user.userInternalId}'" /></td>
                    <td><input type="submit" value="Update" /></td>
                </tr>
            </table>
        </div>
        <script language="JavaScript" type="text/javascript">
									function showhidediv(rad) {
										var rads = document
												.getElementsByName(rad.name);
										document.getElementById('one').style.display = (rads[0].checked) ? 'block'
												: 'none';
										document.getElementById('two').style.display = (rads[1].checked) ? 'block'
												: 'none';
									}
								</script>
    </form>
</body>
</html>