<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<head>
	<title>Job Search</title>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Job Management System</h2>
		</div>
	</div>
	<div id="container">
		<h3> Post Job</h3>
		<form:form action="saveJob" modelAttribute="job" method="POST">
		<!-- Associate the data with a given customer with a hidden form param -->
		<form:hidden path="id"/> <!-- Customer.setId will be called -->
		<table>
			<tbody>
				<tr>
					<td><label> Job Title:</label></td>
					<td><form:input path="jobTitle" /> <form:errors path="jobTitle" /></td>
				</tr>
				<tr>
					<td><label> Job Description:</label></td>
					<td><form:textarea path="jobDescription" /><form:errors path="jobDescription" /></td>
				</tr>
				<tr>
					<td><label> Minimum Experience:</label></td>
					<td><form:input path="experience" type = "number"/><form:errors path="experience" /></td>
				</tr>
				<tr>
                	<td><label> Salary(in INR):</label></td>
                	<td><form:input path="salary" type = "number" step="0.01" /><form:errors path="salary" /></td>
                </tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>

			</tbody>
		</table>
		</form:form>
	</div>
	<!--  Adding The link to the bottom of the page rather than at the top -->
	<br><br>
	<br><br>
	<div id="container">
			<a href="${pageContext.request.contextPath}/job/list">Back to Job List Page</a>
	</div>

</body>
</html>