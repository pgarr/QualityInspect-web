<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>

<title>List Forms</title>
</head>

<body>

	<h2>Quality Inspect</h2>
	<br>
	<h3>Forms list</h3>

	<table>
		<tr>
			<th>Object type</th>
			<th>Form</th>
			<th>Description</th>
			<th>Action</th>
		</tr>

		<c:forEach var="tempForm" items="${forms}">

			<c:url var="viewLink" value="/form/view">
				<c:param name="formId" value="${tempForm.id}" />
			</c:url>

			<tr>
				<td>${tempForm.object.name}</td>
				<td>${tempForm.name}</td>
				<td>${tempForm.description}</td>
				<td><a href="${viewLink}">View</a></td>
			</tr>
		</c:forEach>

	</table>

</body>

</html>