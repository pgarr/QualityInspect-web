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

			<h3>Inspections list</h3>
			<c:url var="items" value="/item/list" />
			<a href="${items}">Manage items & forms</a>

			<table>
				<tr>
					<th>Status</th>
					<th>Batch</th>
					<th>Item</th>
					<th>Inspection form</th>
					<th>Result</th>
					<th>Serial number</th>
					<th>Completion date</th>
					<th>Completion time</th>
				</tr>

				<c:forEach var="tempInspection" items="${inspections}">

					<c:url var="viewLink" value="/inspection/view">
						<c:param name="inspectionId" value="${tempInspection.id}" />
					</c:url>

					<tr>
						<td>${tempInspection.completed}</td>
						<td>${tempInspection.batch}</td>
						<td>${tempInspection.form.item.name}</td>
						<td>${tempInspection.form.name}</td>
						<td>${tempInspection.mainResult}</td>
						<td><a href="${viewLink}">${tempInspection.serialNumber}</a></td>
						<td>${tempInspection.completionDate}</td>
						<td>${tempInspection.completionTime}</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>

</body>

</html>