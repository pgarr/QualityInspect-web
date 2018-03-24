<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>

<title>List Forms</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Quality Inspect</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<h3>Forms list</h3>

			<input type="button" value="New Form"
				onclick="window.location.href='selectItem'; return false;"
				class="button" />

			<table>
				<tr>
					<th>Item type</th>
					<th>Form</th>
					<th>Description</th>
				</tr>

				<c:forEach var="tempForm" items="${forms}">

					<c:url var="viewLink" value="/form/view">
						<c:param name="formId" value="${tempForm.id}" />
					</c:url>

					<tr>
						<td>${tempForm.item.name}</td>
						<td><a href="${viewLink}">${tempForm.name}</a></td>
						<td>${tempForm.description}</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>