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

		<table>

			<tr>
				<td><label>Item name:</label></td>
				<td>${form.item.name}</td>
			</tr>

			<tr>
				<td><label>Maker:</label></td>
				<td>${form.item.itemDetail.maker}</td>
			</tr>

			<tr>
				<td><label>Description:</label></td>
				<td>${form.item.itemDetail.description}</td>
			</tr>

		</table>

		<br>

		<form:form action="saveForm" modelAttribute="form" method="POST">

			<table>
				<tbody>

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

			<br>

			<table>
				<tr>
					<th>Number</th>
					<th>Description</th>
					<th>Details</th>
				</tr>

				<c:forEach var="step" items="${form.steps}" varStatus="status">
					<tr>
						<td>${step.number}</td>
						<td><form:input path="steps[${status.index}].description" /></td>
						<td><form:input path="steps[${status.index}].details" /></td>
					</tr>


				</c:forEach>
			</table>

		</form:form>
	</div>

</body>

</html>
