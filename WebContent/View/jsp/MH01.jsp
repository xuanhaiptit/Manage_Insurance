<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="view/css/style.css" rel="stylesheet" type="text/css" />
<title>Manage Insurance</title>
</head>
<body>
    <form action="login.do" method="post">
        <center>
            <table class="tbl_input" cellpadding="4" cellspacing="0" width="400px">
                <tr>
                    <th width="120px">&nbsp;</th>
                    <th></th>
                </tr>
                <tr>
                    <th colspan="2" align="left">Login to the Manage insurance</th>
                </tr>
                <c:forEach items="${messageError}" var="error">
                    <tr>
                        <td colspan="2"><font color="#FF0000">${error} </font></td>
                    </tr>
                </c:forEach>

                <tr align="left">
                    <td class="lbl_left">UserName:</td>
                    <td align="left"><input class="txBox" type="text" name="userName" size="22"
                        onfocus="this.style.borderColor='#0066ff';" onblur="this.style.borderColor='#aaaaaa';"
                        value="${fn:escapeXml(userName)}" /></td>
                </tr>
                <tr>
                    <td class="lbl_left">PassWord:</td>
                    <td align="left"><input class="txBox" type="passWord" name="passWord" size="22"
                        onfocus="this.style.borderColor='#0066ff';" onblur="this.style.borderColor='#aaaaaa';"
                        value="${fn:escapeXml(passWord)}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left"><input class="btn btn_wider" type="submit" value="Login" /></td>
                </tr>
            </table>
        </center>
    </form>
</body>
</html>