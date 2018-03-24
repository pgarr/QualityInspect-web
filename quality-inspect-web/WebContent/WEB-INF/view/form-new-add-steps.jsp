<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>Add Steps To New Form</title>

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

			<h3>New Form</h3>

			<table>
				<tr>
					<th>Item type:</th>
					<td>${form.itemId}</td>
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

				<%-- 				<c:forEach var="tempStep" items="${form.steps}">
					<tr>
						<td>${tempStep.number}</td>
						<td>${tempStep.description}</td>
						<td>${tempStep.details}</td>
					</tr>


				</c:forEach> --%>



				<%-- 		<table>
	
				<c:forEach var="tempStep" items="${form.steps}">
					<tr>
						<td><form:input path="tempStep.number" /></td>
						<td><form:input path="tempStep.description" /></td>
						<td><form:input path="tempStep.details" /></td>
					</tr>


				</c:forEach>
			</table> --%>

			</table>

		</div>
	</div>

</body>

</html>