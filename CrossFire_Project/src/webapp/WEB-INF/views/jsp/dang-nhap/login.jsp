<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3 class="text-center mb-0">Welcome</h3>
<p class="text-center">Đăng nhập bằng cách nhập các thông tin
	bên dưới</p>
<form:form method="POST" action="LoginThanhCong"
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
			<span class="fa fa-lock"></span>
		</div>
		<form:password path="userPassword" cssClass="form-control"
			placeholder="Mật khẩu" showPassword="true" required = "required" minlength="5" maxlength="15" value = "${userPassword}"/>
	</div>
	<div class="form-group d-md-flex">
		<div class="w-100 text-md-right">
			<a href="${pageContext.request.contextPath}/gui-ma-xac-thuc">Quên mật khẩu</a>
		</div>
	</div>
	<div class="form-group">
		<button type="submit"
			class="btn form-control btn-primary rounded submit px-3">Đăng
			Nhập</button>
	</div>
</form:form>