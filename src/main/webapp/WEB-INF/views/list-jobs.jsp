<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<title>Job List</title>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2> Job Management System</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		<!--  Add "Add Job" Button -->
		<input type="button" value="Post Job"
			   onclick="window.location.href='showFormForAdd'; return false;"
			   class="add-button"/>
			   <br>
			   <br>
			   <br>
		<!-- Add Table Content here -->
		<table border = "1">
			<tr>
				<th> Job Title</th>
				<th> Job Description </th>
				<th> Minimum Experience </th>
				<th> Salary </th>
				<th> Action</th>
			</tr>
			<c:forEach var="tempJob" items="${jobs}">

			<!-- Add embedded link to update the customer -->
			<c:url var="updateLink" value="/job/showFormForUpdate">
				<c:param name="jobId" value="${tempJob.id}"/>
			</c:url>
			<c:url var="deleteLink" value="/job/showFormForDelete">
				<c:param name="jobId" value="${tempJob.id}"/>
			</c:url>

					<tr>
						<td> ${tempJob.jobTitle} </td>
						<td> ${tempJob.jobDescription} </td>
						<td> ${tempJob.experience} </td>
						<td> Rs. ${tempJob.salary} </td>
						<td>
							<a href="${updateLink}">Update</a>
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to clear this job?'))) return false">
							|Clear</a>
						</td>
					</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>