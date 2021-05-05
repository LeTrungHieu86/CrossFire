<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p class="text-center">Để lấy được mã xác thực xin nhập đầy đủ các thông tin bên dưới.</p>
<form:form method="POST" action="${pageContext.request.contextPath}/xac-thuc"
	modelAttribute="user" class="login-form">
	<div class="form-group">
		<div
			class="icon d-flex align-items-center justify-content-center">
			<span class="fa fa-user"></span>
		</div>
		<form:input path="userName" cssClass="form-control"
			placeholder="Tên tài khoản" required = "required" minlength="5" maxlength="15" value = "${userName}"/>
	</div>
	<div class="form-group">
		<div
			class="icon d-flex align-items-center justify-content-center">
			<span class="fa fa-envelope-o"></span>
		</div>
		<form:input type="Email" path="userEmail" cssClass="form-control" 
			placeholder="Tài khoản email" required = "required" minlength="11" maxlength="45" value = "${userEmail}"/>
	</div>
	<div class="form-group">
		<button type="submit"
			class="btn form-control btn-primary rounded submit px-3">Gửi Mã Xác Nhận</button>
	</div>
</form:form>