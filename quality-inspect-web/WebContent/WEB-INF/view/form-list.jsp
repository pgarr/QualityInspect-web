<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>

<title>Forms List</title>

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

			<table>

				<tr>
					<td><label>Item name:</label></td>
					<td>${item.name}</td>
				</tr>

				<tr>
					<td><label>Maker:</label></td>
					<td>${item.itemDetail.maker}</td>
				</tr>

				<tr>
					<td><label>Description:</label></td>
					<td>${item.itemDetail.description}</td>
				</tr>

			</table>

			<c:url var="newFormLink" value="/form/newForm">
				<c:param name="itemId" value="${item.id}" />
			</c:url>

			<a href="${newFormLink}">New Form</a> <br>

			<table>
				<tr>
					<th>Form</th>
					<th>Description</th>
				</tr>

				<c:forEach var="tempForm" items="${forms}">

					<c:url var="viewLink" value="/form/view">
						<c:param name="formId" value="${tempForm.id}" />
					</c:url>

					<tr>
						<td><a href="${viewLink}">${tempForm.name}</a></td>
						<td>${tempForm.description}</td>
					</tr>
				</c:forEach>

			</table>

			<c:url var="back" value="/item/list" />
			<a href="${back}">Back</a>

		</div>
	</div>

</body>

</html>