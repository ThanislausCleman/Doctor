<%@page import="com.doctorService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col">

				<h1>Item Management V9</h1>
				<form id="formItem" name="formItem" method="post" action="Doctor.jsp">
					Doctor name: <input id="name" name="name" type="text" class="form-control form-control-sm">
					<br> 
					Doctor Specialization:<input id="specialization" name="specialization" type="text" class="form-control form-control-sm">
					<br> 
					Doctor NIC:<input id="nic" name="nic" type="text" class="form-control form-control-sm">
					<br>
					Doctor Mobile:<input id="mobile" name="mobile" type="text" class="form-control form-control-sm">
					<br> 
					Doctor email:<input id="email" name="email" type="text" class="form-control form-control-sm">
					<br>
					Doctor DoctorFee: <input id="doctorfee" name="doctorfee" type="text" class="form-control form-control-sm">
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidDocIDSave" name="hidDocIDSave" value="">
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divItemsGrid">
				<%
				doctorService itemObj = new doctorService();
				out.print(itemObj.readDoctor());
				%>
</div>



			</div>
		</div>

	</div>




</body>
</html>