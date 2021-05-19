<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!doctype html>
<html lang="en">
<head>
<title>Đăng nhập</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href='<c:url value = "/resources/css/style.css"/>'>

</head>
<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center"></div>
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap py-5">
						<c:if test="${not empty ErrorMesage}">
						<div style="text-align: center;"> 
							<label style="margin-bottom: 30px; text-align: center; color: 
							white; background-color: #b33030; border-radius: 10px; padding:5px">
								${ErrorMesage}</label></div>
						</c:if>
						<c:if test="${not empty SuccessMesage}">
						<div style="text-align: center;"> 
							<label style="margin-bottom: 30px; text-align: center; color: 
							white; background-color: green; border-radius: 10px; padding:5px">
								${SuccessMesage}</label></div>
						</c:if>
						<div class="img d-flex align-items-center justify-content-center"
							style="background-image: url(<c:url value = "/resources/dist/img/avatar04.png"></c:url>);"></div>
							
						<tiles:insertAttribute name="body" />
						
						<div class="w-100 text-center mt-4 text"></div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- jQuery 2.1.3 -->
	<script
		src='<c:url value = "/resources/plugins/jQuery/jQuery-2.1.3.min.js"/>'></script>
	<!-- Bootstrap 3.3.2 JS -->
	<script
		src='<c:url value = "/resources/bootstrap/js/bootstrap.min.js"/>'></script>
	<script src="<c:url value = "/resources/js/popper.js"/>"></script>
</body>
</html>


