<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<header class="main-header">
	<!-- Logo -->
	<a href="index.html" class="logo"><b>Admin</b> CrossFire</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<form:form action="/thay-doi-mat-khau">
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<!-- User Account: style can be found in dropdown.less -->
					<li class="dropdown user user-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <img
							src='<c:url value = "/resources/dist/img/avatar04.png"></c:url>' class="user-image"
							alt="User Image" /> <span class="hidden-xs">Admin</span>
					</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header"><img
								src='<c:url value = "/resources/dist/img/avatar04.png"></c:url>' class="img-circle"
								alt="User Image" />
								<p>
									Admin - Web Developer <small>Member since Nov. 2012</small>
								</p></li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<button type="submit"  class="btn btn-default btn-flat">Đổi Mật Khẩu</button>
								</div>
								<div class="pull-right">
									<a href="${pageContext.request.contextPath}/dang-xuat" class="btn btn-default btn-flat">Đăng Xuất</a>
								</div>
							</li>
						</ul>
					</li>
				</ul>
		</div>
		</form:form>
	</nav>
</header>