<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="View/js/app-ajax.js" type="text/javascript"></script>
<SCRIPT SRC="jquery.js" TYPE="text/javascript"></SCRIPT>
</head>
<body>
    <p>
        <label for="pcategory">Product Category</label> <select name="pcategory" id="pcategory" size="0">
            <option value="1">Category 1</option>
            <option value="2">Category 2</option>
            <option value="3">Category 3</option>
        </select>
    </p>
    <p>
        <label for="pname">Product Name:</label> <select name="state" id="state">
            <option value="1">Product Name 1 For Category 1</option>
            <option value="2">Product Name 2 For Category 1</option>
            <option value="3">Product Name 3 For Category 1</option>
        </select>
    </p>

    <p>Select a new car from the list.</p>

    <select id="mySelect" onchange="myFunction()">
        <option value="Audi">Audi
        <option value="BMW">BMW
        <option value="Mercedes">Mercedes
        <option value="Volvo">Volvo
    </select>

    <p>When you select a new car, a function is triggered which outputs the value of the selected car.</p>

    <p id="demo"></p>

    <script>
function myFunction() {
    var x = document.getElementById("mySelect").value;
    document.getElementById("demo").innerHTML = "You selected: " + x;
}
</script>

</body>
<script type="text/javascript">
	$category = $('#pcategory');

	$onChange = "IndexController(this.selectedvalue) {
		$.ajax({
			type : "GET",
			url : "index.do",
			data : {
				category : $category.attr("selectedIndex")
			},
			success : function(data) {
				$("#state").html(data)
			}
		});
	});
</script>
</html>