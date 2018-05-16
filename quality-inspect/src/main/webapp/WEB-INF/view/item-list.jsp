<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>

<title>List Items</title>

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

			<h3>Item list</h3>

			<input type="button" value="New Item"
				onclick="window.location.href='newItem'; return false;"
				class="button" />

			<table>
				<tr>
					<th>Item name</th>
					<th>Maker</th>
					<th>Description</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempItem" items="${items}">

					<c:url var="viewFormsLink" value="/form/itemForms">
						<c:param name="itemId" value="${tempItem.id}" />
					</c:url>

					<tr>
						<td>${tempItem.name}</td>
						<td>${tempItem.itemDetail.maker}</td>
						<td>${tempItem.itemDetail.description}</td>
						<td><a href="${viewFormsLink}">Show Forms</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>