<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Car list</title>
<style type="text/css">
@import url("style.css");
</style>
</head>
<body>
	<%
		String log = (String) session.getAttribute("user_login");
	%>
	<%
		if (log == null || "".equals(log)) {
	%>
	<a href="index.html">Click</a> to login
	<%
		} else {
	%>
	<h3>
		You are logged in as:
		<%=log%>
		<a href="login?a=exit">Exit</a>
	</h3>
	<%
		}
	%>
	<table>
		<tr>
			<td><table class="menu">
					<tr>
						<th><form action="adverts" method="get">
								<input type="submit" value="All adverts">
							</form></th>
					</tr>
					<tr>
						<th><form action="adverts">
								Min price <input type="text" min="0" name="priceMin">
								<p>
									Max price <input type="text" min="0" name="priceMax">
								<p>
									<input type="submit" value="Car price..">
							</form></th>
					</tr>
					<tr>
						<th><form action="adverts">
								<input type="text" name="brand"><input type="submit"
									class="short" value="Car brand..">
							</form></th>
					</tr>
					<tr>
						<th><form action="adverts">
								<input type="text" name="model"><input type="submit"
									class="short" value="Car model..">
							</form></th>
					</tr>
					<tr>
						<th><form action="adverts">
								<input type="text" name="year"><input type="submit"
									class="short" value="Car year..">
							</form></th>
					</tr>
					<tr>
						<th><form action="NewAdvert.jsp">
								<input type="submit" value="Add advert">
							</form></th>
					</tr>
				</table></td>
			<td><table class="list">
					<c:if test="${list ne null }">
						<tr class="head">
							<th colspan="7"><c:if test="${all ne null }">
									<h1>Full list</h1>
								</c:if> <c:if test="${price ne null }">
									<h1>
										Price
										<c:out value="${price}" />
										list
									</h1>
								</c:if> <c:if test="${brand ne null }">
									<h1>
										<c:out value="${brand}" />
										list
									</h1>
								</c:if> <c:if test="${model ne null }">
									<h1>
										<c:out value="${model}" />
										list
									</h1>
								</c:if> <c:if test="${year ne null }">
									<h1>
										Year
										<c:out value="${year }" />
										list
									</h1>
								</c:if></th>
						</tr>
						<tr>
							<th>Title</th>
							<th>Price</th>
							<th>Telephone</th>
							<th>Brand</th>
							<th>Model</th>
							<th>Year</th>
							<th>Info</th>
						</tr>
						<c:forEach items="${list}" var="car">
							<tr>
								<td><c:out value="${car.getTitle() }"></c:out>
								<td><c:out value="${car.getPrice() }"></c:out>
								<td><c:out value="${car.getTelephone() }"></c:out>
								<td><c:out value="${car.getBrand() }"></c:out>
								<td><c:out value="${car.getModel() }"></c:out>
								<td><c:out value="${car.getYear() }"></c:out>
								<td><a href="adverts?getAdvert=${car.hashCode() }">More
										info</a>
						</c:forEach>
					</c:if>
					<c:if test="${getAdvert ne null}">
						<tr>
							<th>Title
							<td><c:out value="${getAdvert.getTitle() }"></c:out>
						<tr>
							<th>Specification
							<td><c:out value="${getAdvert.getSpecification() }"></c:out>
						<tr>
							<th>Price
							<td><c:out value="${getAdvert.getPrice() }"></c:out>
						<tr>
							<th>User
							<td><c:out value="${getAdvert.getUser() }"></c:out>
						<tr>
							<th>Telephone
							<td><c:out value="${getAdvert.getTelephone() }"></c:out>
						<tr>
							<th>Brand
							<td><c:out value="${getAdvert.getBrand() }"></c:out>
						<tr>
							<th>Model
							<td><c:out value="${getAdvert.getModel() }"></c:out>
						<tr>
							<th>Year
							<td><c:out value="${getAdvert.getYear() }"></c:out>
						<tr>
							<th>New car
							<td><c:out value="${getAdvert.isNewCar()}"></c:out>
						<tr>
							<th colspan="2">
								<form action="adverts" method="post">
									<input type="hidden" name="doing" value="del"> <input
										type="hidden" name="hash" value="${getAdvert.hashCode()}">
									<input type="submit" value="Del">
								</form>
					</c:if>
				</table></td>
		</tr>
	</table>
</body>
</html>