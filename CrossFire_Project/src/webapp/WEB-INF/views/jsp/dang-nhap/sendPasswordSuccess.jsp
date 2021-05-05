<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${pageContext.request.contextPath}/comeback" class="login-form" modelAttribute="user">
	<div class="form-group">
		<button type="submit"
			class="btn form-control btn-primary rounded submit px-3">Quay
			lại trang đăng nhập</button>
	</div>
</form:form>