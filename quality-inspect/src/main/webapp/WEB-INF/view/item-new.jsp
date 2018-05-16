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
			<h3>New Item</h3>
		</div>

		<form:form action="saveItem" modelAttribute="item" method="POST">

			<table>
				<tbody>
					<tr>
						<td><label>Item type:</label></td>
						<td><form:input path="name" /></td>
					</tr>

					<tr>
						<td><label>Maker:</label></td>
						<td><form:input path="itemDetail.maker" /></td>
					</tr>

					<tr>
						<td><label>Description:</label></td>
						<td><form:input path="itemDetail.description" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</tbody>
			</table>

		</form:form>

		<input type="button" value="Cancel"
			onclick="window.location.href='list'; return false;" class="button" />

	</div>

</body>

</html>
