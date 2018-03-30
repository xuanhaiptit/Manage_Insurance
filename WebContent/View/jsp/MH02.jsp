<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <form action="MH02.do?typeAction=search" method="post">
        <div>
            <p style="margin-left: 30px">List of insurance cards</p>
            <p style="margin-left: 100px">Company name</p>
            <select style="margin-left: 100px" name="companyInternalId" id="mySelect" onchange="myFunction()">
                <c:forEach var="company" items="${listCompany}">
                    <c:choose>
                        <c:when test="${company.companyInternalId == companyInternalId}">
                            <option value="${company.companyInternalId}" selected="selected">${company.companyName}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${company.companyInternalId}">${company.companyName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <p style="margin-left: 30px">Search information</p>
            <table style="margin-left: 30px" class="tbl_input" border="1" cellpadding="10" cellspacing="10"
                width="400px">
                <tr>
                    <td>
                        <table style="margin-left: 5px" class="tbl_input" border="0" cellpadding="5" cellspacing="5">
                            <tr>
                                <td>User name:</td>
                                <td align="left" width="80px"><input class="txBox" type="text" name="userFullName"
                                    size="20" id="userFullName"
                                    value="<c:out value="${fn:escapeXml(userFullName)}" escapeXml="true"/>" /></td>
                            </tr>
                            <tr>
                                <td>Insurance card number:</td>
                                <td align="left" width="80px"><input id="insuranceNumber" class="txBox" type="text"
                                    name="insuranceNumber" size="20"
                                    value="<c:out value="${fn:escapeXml(insuranceNumber)}" escapeXml="true"/>" /></td>
                            </tr>
                            <tr>
                                <td>Register Place:</td>
                                <td align="left" width="80px"><input id="placeOfRegister" class="txBox" type="text"
                                    name="placeOfRegister" size="20"
                                    value="<c:out value="${fn:escapeXml(placeOfRegister)}" escapeXml="true"/>" /></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="Search"
                                    style="width: 150px" /></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <p>
                <input type="button" style="margin-left: 30px" value="Resgiter"
                    onclick="location.href='addUserInfo.do?typeAction=addUser&&companyInternalId=${companyInternalId}'" />
                <c:if test="${!empty  listUserInfo}">
                    <input type="button" style="margin-left: 60px" value="Export CSV"
                        onclick="location.href='exportUser.do?typeAction=ExportCSV'" />
                    <input type="button" style="margin-left: 90px" value="Export PDF"
                        onclick="location.href='exportUser.do?typeAction=ExportPDF'" />
                </c:if>
            </p>
        </div>

        <p>${messageActionSuccess}</p>
        <c:choose>
            <c:when test="${empty  listUserInfo}">
                <p>${MSG01_NO_RECORDS}</p>
            </c:when>
            <c:otherwise>
                <table class="tbl_list" border="1" cellpadding="4" cellspacing="0" width="700px">
                    <tr>
                        <th>User Name <c:if test="${typeSort=='DESC'}">
                                          ▲<a href="MH02.do?typeAction=sort&&typeSort=DESC">▼</a>
                            </c:if> <c:if test="${typeSort=='ASC'}">
                                <a href="MH02.do?typeAction=sort&&typeSort=ASC">▲</a>▼
                                      </c:if>
                        </th>
                        <th>Sex</th>
                        <th>Birthday</th>
                        <th>Insurance Number</th>
                        <th>Period</th>
                        <th>Place Of Register</th>
                    </tr>
                    <c:forEach var="user" items="${listUserInfo}">
                        <tr>
                            <td><a
                                href="showUserInfo.do?typeAction=showUserInfo&&userInternalId=${user.userInternalId}">
                                    <c:out value="${user.userFullName}" escapeXml="true" />
                            </a></td>
                            <td align="center"><c:if test="${user.userSexDivision == 'M'}">
                                    <c:out value="Nam" />
                                </c:if> <c:if test="${user.userSexDivision == 'F'}">
                                    <c:out value="Nu" />
                                </c:if></td>
                            <td align="center"><fmt:formatDate value="${user.birthdate}" pattern="dd-MM-yyyy" /></td>
                            <td align="center">${user.insuranceNumber}</td>
                            <td align="center"><fmt:formatDate value="${user.insuranceStartDate}"
                                    pattern="dd-MM-yyyy" /> ~<fmt:formatDate value="${user.birthdate}"
                                    pattern="dd-MM-yyyy" /></td>
                            <td>${user.placeOfRegister}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

        <!-- Begin vung paging -->
        <table>
            <tr>
                <c:if test="${totalPaging > 1}">
                    <td class="lbl_paging"><c:if test="${pagingFirst > limitPage}">
                            <a href="MH02.do?typeAction=page&numberOfPages=${pagingFirst - limitPage}">&lsaquo;&lsaquo;</a>
                        </c:if> <c:forEach items="${listPaging}" var="Paging">
                            <c:if test="${Paging == currentPage}">
                                <span>${Paging}</span>
                            </c:if>
                            <c:if test="${Paging != currentPage}">
                                <a href="MH02.do?typeAction=page&numberOfPages=${Paging}">${Paging}</a>
                            </c:if>
                        </c:forEach> <c:if test="${pagingFirst +limitPage <= totalPaging}">
                            <a href="MH02.do?typeAction=page&numberOfPages=${pagingFirst +limitPage}">&rsaquo;&rsaquo;</a>
                        </c:if></td>
                </c:if>
            </tr>
        </table>
    </form>
    <script>
        function myFunction() {
            var userFullName = document.getElementById("userFullName");
            var insuranceNumber = document.getElementById("insuranceNumber");
            var placeOfRegister = document.getElementById("placeOfRegister");
            userFullName.value = "";
            insuranceNumber.value = "";
            placeOfRegister.value = "";
            }
    </script>
    </body>
</html>
