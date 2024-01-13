<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <h2> Favourite Appointments </h2>
                    <form:form action="/searchFav" method="POST">
            			<div>
            			    <input type="text" placeholder="Search By Job Name/Desc" name="theSearchName" value = "${theSearchName}">
            			    <input type="submit" value="Search"/>
            			</div>
                    </form:form>
		<!-- Add Table Content here -->
		<table border = "1">
			<tr>
			<th> Sno. </th>
				<th> Job Title
				        &nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=jobTitle,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=jobTitle"> Asc </a>
				</th>
				<th> Job Description
				        &nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=jobDescription,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=jobDescription"> Asc </a>
				</th>
				<th> Minimum Experience
						&nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=experience,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=experience"> Asc </a>
				</th>
				<th> Salary
						&nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=salary,desc"> Desc </a>
                        &nbsp &nbsp <a href= "/searchFav?page=0&size=5&theSearchName=${theSearchName}&sort=salary"> Asc </a>
				</th>
				<th> Action</th>
			</tr>
			<c:set var="index" value="${page * 5 + 1}" />
			<c:forEach var="tempJob" items="${jobs}">

			<c:url var="isFavLink" value="/job/markFav">
            				<c:param name="jobId" value="${tempJob.id}"/>
            				<c:param name="isFav" value="false"/>
            			</c:url>
					<tr>
					    <td>${index}</td>
						<td> ${tempJob.jobTitle} </td>
						<td> ${tempJob.jobDescription} </td>
						<td> ${tempJob.experience} </td>
						<td> Rs. ${tempJob.salary} </td>
						<td> <a href="${isFavLink}">Remove Favourite</a> </td>
					</tr>

					<c:set var="index" value="${index + 1}" />
			</c:forEach>
		</table>
		</div>
	</div>
	<br><br>
        	<c:choose>
                <c:when test="${totalPage == 0}">
                    No Record Found
                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                            &nbsp &nbsp<a href="/searchFav?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <br><br>
            	<br><br>
            	<div id="container">
            			<a href="${pageContext.request.contextPath}/job/list">Back to Job List Page</a>
            	</div>

</body>
</html>