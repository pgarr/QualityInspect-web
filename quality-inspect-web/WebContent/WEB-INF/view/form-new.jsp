<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Add Form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/form-style.css" />
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Quality Inspect</h2>
		</div>

		<div id="container">
			<h3>New Form</h3>
		</div>

		<form:form action="addSteps" modelAttribute="form" method="POST">

			<table>
				<tbody>
					<tr>
						<td><label>Item type:</label></td>
						<td><form:select path="itemId" items="${itemsMap}" /></td>
					</tr>

					<tr>
						<td><label>Form name:</label></td>
						<td><form:input path="name" /></td>
					</tr>

					<tr>
						<td><label>Form description:</label></td>
						<td><form:input path="description" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Accept" class="save" /></td>
					</tr>

				</tbody>
			</table>

		</form:form>
	</div>

</body>

</html>
