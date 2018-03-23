<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>View Form</title>
</head>

<body>

	<h2>Quality Inspect</h2>
	<br>
	<h3>Form</h3>

	<table>
		<tr>
			<th>Object type:</th>
			<td>${form.object.name}</td>
		</tr>
		<tr>
			<th>Maker:</th>
			<td>${form.object.objectDetail.maker}</td>
		</tr>
		<tr>
			<th>Object description:</th>
			<td>${form.object.objectDetail.description}</td>
		</tr>
		<tr>
			<th>Form:</th>
			<td>${form.name}</td>
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

</body>

</html>