<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>View Reports</title>
</head>
<body>
	<form method="POST" action="studentReport">
		Enter the student id to view the report : <input type="text" name="id"/> 
		<input type="submit" value="submit"/>
	</form>
</body>
</html>