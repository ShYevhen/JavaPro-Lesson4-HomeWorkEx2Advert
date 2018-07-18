<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new advert</title>
<style type="text/css">
@import url("regstyle.css");
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
	<form method="post" action="adverts" class="form">
		<input type="hidden" name="doing" value="add">
		<p>
			<label for="titel"><span class="formTextRed">*</span> Title</label> <input
				type="text" name="title" id="title" required="required"
				pattern="{2,40}" />
		</p>
		<p>
			<label for="specification">Specification</label> <input type="text"
				name="specification" id="specification" pattern="{0,600}" />
		</p>
		<p>
			<label for="price"><span class="formTextRed">*</span> Price</label> <input
				type="text" name="price" id="price" required="required"
				pattern="[0-9]{1,20}" />
		</p>
		<p>
			<label for="telephone"><span class="formTextRed">*</span>
				Telephone</label> <input type="text" name="telephone" id="telephone"
				required="required" pattern="[0-9_+]{1,20}" />
		</p>
		<p>
			<label for="brand"><span class="formTextRed">*</span> Brand</label> <input
				type="text" name="brand" id="brand" required="required"
				pattern="[A-Za-z0-9_-]{1,40}" />
		</p>
		<p>
			<label for="model"><span class="formTextRed">*</span> Model</label> <input
				type="text" name="model" id="model" required="required"
				pattern="[A-Za-z0-9_-]{2,40}" />
		</p>
		<p>
		<p>
			<label for="year"><span class="formTextRed">*</span> Year</label> <input
				type="text" name="year" id="year" required="required"
				pattern="[0-9]{4,4}" />
		</p>
		<p>
			<input type="checkbox" name="newcar" id="newcar" /> <label
				for="newcar" class="confirm">New car</label>
		</p>
		<p class="submit">
			<input type="submit" value="Add" name="send" />
		</p>
	</form>
</body>
</html>