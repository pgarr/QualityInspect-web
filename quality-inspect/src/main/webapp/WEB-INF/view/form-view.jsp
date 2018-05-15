<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>View Form</title>

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

			<h3>Form</h3>

			<table>

				<tr>
					<th>Item type:</th>
					<td>${form.item.name}</td>
				</tr>

				<tr>
					<th>Maker:</th>
					<td>${form.item.itemDetail.maker}</td>
				</tr>

				<tr>
					<th>Item description:</th>
					<td>${form.item.itemDetail.description}</td>
				</tr>

				<tr>
					<th>Form name:</th>
					<td>${form.name}</td>
				</tr>

				<tr>
					<th>Form description:</th>
					<td>${form.description}</td>
				</tr>

			</table>

			<br>
			<h4>Steps</h4>

			<table>
				<tr>
					<th>Number</th>
					<th>Description</th>
					<th>Details</th>
				</tr>

				<c:forEach var="tempStep" items="${form.steps}">
					<tr>
						<td>${tempStep.number}</td>
						<td>${tempStep.description}</td>
						<td>${tempStep.details}</td>
					</tr>


				</c:forEach>
			</table>

			<c:url var="viewFormsLink" value="/form/itemForms">
				<c:param name="itemId" value="${form.item.id}" />
			</c:url>

			<a href="${viewFormsLink}">Back</a>

		</div>
	</div>

</body>

</html>