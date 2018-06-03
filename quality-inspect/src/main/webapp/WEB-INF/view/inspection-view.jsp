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
			<h3>Inspection</h3>
		</div>



		<form:form action="saveInspection" modelAttribute="inspection"
			method="POST">

			<table>
				<tbody>

					<form:hidden path="id" />
					<form:hidden path="completed" />

					<tr>
						<td><label>Item name:</label></td>
						<td>${inspection.form.item.name}</td>
					</tr>

					<tr>
						<td><label>Form name:</label></td>
						<td>${inspection.form.name}</td>
					</tr>

					<tr>
						<td><label>Serial number:</label></td>
						<td><form:input path="serialNumber"
								readonly="${inspection.completed}" /></td>
					</tr>

					<tr>
						<td><label>Created:</label></td>
						<td><form:input path="creationDate" readonly="true" /> <form:input
								path="creationTime" readonly="true" /></td>
					</tr>

					<tr>
						<td><label>Completed:</label></td>
						<td><form:input path="completionDate" readonly="true" /> <form:input
								path="completionTime" readonly="true" /></td>
					</tr>

					<tr>
						<td><label>Inspector:</label></td>
						<td><form:input path="inspector"
								readonly="${inspection.completed}" /></td>
					</tr>

					<tr>
						<td><label>Place:</label></td>
						<td><form:input path="place"
								readonly="${inspection.completed}" /></td>
					</tr>

					<tr>
						<td><label>Batch:</label></td>
						<td><form:input path="batch"
								readonly="${inspection.completed}" /></td>
					</tr>

					<tr>
						<td><label>Main result:</label></td>
						<td><form:input path="mainResult"
								readonly="${inspection.completed}" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" name="Save" value="Save"
							class="save" /></td>
					</tr>

				</tbody>
			</table>

			<br>

			<table>
				<tr>
					<th>Number</th>
					<th>Description</th>
					<th>Details</th>
					<th>Good</th>
					<th>Accepted</th>
					<th>Not accepted</th>
					<th>Note</th>
					<th></th>
				</tr>

				<c:forEach var="result" items="${inspection.results}"
					varStatus="status">

					<tr>
						<td>${result.step.number}</td>
						<form:hidden path="results[${status.index}].step.number" />

						<td>${result.step.description}</td>
						<form:hidden path="results[${status.index}].step.description" />

						<td>${result.step.details}</td>
						<form:hidden path="results[${status.index}].step.details" />

						<td><form:radiobutton path="results[${status.index}].result"
								value="1" disabled="${inspection.completed}" /></td>

						<td><form:radiobutton path="results[${status.index}].result"
								value="2" disabled="${inspection.completed}" /></td>

						<td><form:radiobutton path="results[${status.index}].result"
								value="3" disabled="${inspection.completed}" /></td>

						<td><form:input path="results[${status.index}].note"
								readonly="${inspection.completed}" /></td>

					</tr>

				</c:forEach>

			</table>

		</form:form>

	</div>

</body>

</html>
