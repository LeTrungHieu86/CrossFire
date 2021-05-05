<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src='<c:url value = "/resources/dist/img/avatar04.png"></c:url>' class="img-circle"
					alt="User Image" />
			</div>
			<div class="pull-left info">
				<p>Admin</p>

				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="treeview"><a href="${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan"> <i class="fa fa-table"></i>
					<span>Thông tin tài khoản CF</span>
			</a></li>
			<li class="treeview"><a href="${pageContext.request.contextPath}/trang-chu/phuong-thuc-thanh-toan"> <i class="fa fa-table"></i>
					<span>Phương Thức Thanh Toán</span>
			</a></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>