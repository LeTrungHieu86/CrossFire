<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Thông tin tài khoản CF</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Trang Chủ</a></li>
		<li class="active">Thông tin tài khoản CF</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<!-- form start -->
				<form:form role="form" action="${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan/them-moi" modelAttribute="product" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<div class="form-group">
								<label for="productTitle">Loại Tài Khoản </label> 
								<form:input class="form-control" type="text" id="productTitle" path = "productTitle" placeholder="Loại Tài Khoản"/>
							</div>
							<label for="productCode">Mã Tài Khoản </label> 
							<form:input path = "productCode" class="form-control" type="text" id="productCode" placeholder="Mã Tài Khoản"/>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Cấp VIP Ingame </label>
							<form:input path="productVipIngameLevel" type="text" class="form-control" id="productVipIngameLevel"
								placeholder="Cấp VIP Ingame"/>
						</div>
						<div class="form-group">
							<label for="productVipNumber">Số VIP </label>
							<form:input path="productVipNumber"	type="text" class="form-control" id="productVipNumber"
								placeholder="Số VIP"/>
						</div>
						<div class="form-group">
							<label for="productInfo">Thông Tin Tài Khoản </label> 
							<form:input path="productInfo" type="text" class="form-control" id="productInfo"
								placeholder="Thông Tin Tài Khoản"/>
						</div>
						<div class="form-group">
							<label for="productPrice">Giá Tài Khoản </label>
							<form:input path="productPrice" type="text" class="form-control" id="productPrice"
								placeholder="VND"/>
						</div>
						<div class="form-group">
							<label for="productVipIngameImage">Ảnh VIP Ingame</label>
							 <input type="file" name="productVipIngameImage">
						</div>
						<div class="form-group">
							<label for="productImage">Ảnh Tài Khoản</label>
							<div class="container my-4" style="width: auto">
								<div class="file-loading">
									<input id="productImage" name="productImage" type="file" multiple>
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Đăng ký</button>
						<!-- <button type="submit" class="btn btn-primary">Nhập Lại</button>
						<button type="submit" class="btn btn-primary">Quay lại</button> -->
					</div>
				</form:form>
			</div>
			<!-- /.box -->


		</div>
		<!--/.col (right) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->