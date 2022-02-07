<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand">User Management App</a>
			</div>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post"></form>
				</c:if>
				
				<c:if test="${user == null}">		
					<form action="insert" method="post"></form>
				</c:if>
				
				<caption>
					<h2>
						<c:if test="${user != null}">
						Edit User
						</c:if>
						<c:if test="${user == null}">
						Add New User
						</c:if>		
					</h2>			
				</caption>
				
				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}'/>" />
				</c:if>
				
				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value = "<c:out value='${user.name}' />" 
						class="form-control" name="name"
						required = "required" >				
				</fieldset>
				
				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value = "<c:out value='${user.email}' />" 
						class="form-control" name="email">				
				</fieldset>
				
				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value = "<c:out value='${user.country}' />" 
						class="form-control" name="country">				
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>
	</div>
</body>
</html>