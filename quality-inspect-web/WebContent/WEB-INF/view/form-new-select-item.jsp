<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- This is old jsp, not used actually -->
<!DOCTYPE html>
<html>

<head>
<title>Select Item</title>

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
			<h3>Select Item</h3>
		</div>

		<form action="newForm" method="POST">

			<table>
				<tbody>
					<tr>
						<td><label>Item type:</label></td>
						<td><form:select path="itemId" items="${itemsMap}" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Accept" class="save" /></td>
					</tr>

				</tbody>
			</table>

		</form>
	</div>

</body>

</html>
