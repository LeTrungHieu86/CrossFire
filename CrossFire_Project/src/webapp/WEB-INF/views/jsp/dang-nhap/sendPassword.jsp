<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p class="text-center">Nhập mã xác thực</p>
<form:form method="POST"
	action="${pageContext.request.contextPath}/cap-lai-mat-khau"
	modelAttribute="user" class="login-form">
	<div class="form-group">
		<div class="icon d-flex align-items-center justify-content-center">
			<span class="fa fa-hand-o-right"></span>
		</div>
		<form:password path="userVerifyCode" cssClass="form-control"
			placeholder="Mã xác thực" required="required" minlength="6" maxlength="6"
			value="${userVerifyCode}" />
	</div>
	<div class="form-group">
		<button type="submit"
			class="btn form-control btn-primary rounded submit px-3">Gửi
			Mật khẩu</button>
	</div>
</form:form>