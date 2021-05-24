<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
				<form:form role="form" id = "formAccountInfor"
					action="${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan/them-moi"
					modelAttribute="product" enctype="multipart/form-data">
					<c:if test="${not empty ErrorMesage}">
						<div style="text-align: center;background-color: #b33030; margin:10px;border-radius: 5px;"> 
							<label style="margin-bottom: 10px;margin-top: 10px; text-align: center; color: 
							white; padding:5px">
								${ErrorMesage}</label></div>
					</c:if>
					<c:if test="${not empty SuccessMesage}">
						<div style="text-align: center;background-color: green; margin:10px;border-radius: 5px;"> 
							<label style="margin-bottom: 10px;margin-top: 10px; text-align: center; color: 
							white; padding:5px">
								${SuccessMesage}</label></div>
					</c:if>
					<div class="box-body">
						<div class="form-group">
							<div class="form-group">
								<label>Loại Tài Khoản </label>
								<form:input class="form-control" type="text" path="productTitle"
									placeholder="Loại Tài Khoản" required="required" maxlength="25" value = "${productTitle}"/>
								<form:errors path="productTitle" cssStyle="color: #ff0000;" />
							</div>
							<label>Mã Tài Khoản </label>
							<form:input path="productCode" class="form-control" type="text"
								id="productCode" placeholder="Mã Tài Khoản" required="required"
								maxlength="10" value = "${productCode}"/>
							<form:errors path="productCode" cssStyle="color: #ff0000;" />
						</div>
						<div class="form-group">
							<label>Cấp VIP Ingame </label>
							<form:input path="productVipIngameLevel" type="number"
								pattern="/^-?\d+\.?\d*$/"
								onKeyPress="if(this.value.length==2) return false;" min="0"
								class="form-control" id="productVipIngameLevel"
								placeholder="Cấp VIP Ingame" value = "${productVipIngameLevel}" />
						</div>
						<div class="form-group">
							<label>Số VIP </label>
							<form:input path="productVipNumber" type="number"
								pattern="/^-?\d+\.?\d*$/"
								onKeyPress="if(this.value.length==2) return false;" min="0"
								class="form-control" id="productVipNumber" placeholder="Số VIP" value = "${productVipNumber}" />
						</div>
						<div class="form-group">
							<label>Thông Tin Tài Khoản </label>
							<form:input path="productInfo" type="text" class="form-control"
								id="productInfo1" placeholder="Thông Tin Tài Khoản"
								required="required" value = "${productInfo}"/>
							<form:errors path="productInfo" cssStyle="color: #ff0000;" />
						</div>
						<div class="form-group">
							<label>Giá Tài Khoản </label>
							<form:input path="productPrice" type="number" pattern="/^-?\d+\.?\d*$/"
								 min="0" class="form-control"
								id="productPrice" placeholder="VND" value = "${productPrice}" />
							<form:errors path="productPrice" cssStyle="color: #ff0000;" />
						</div>
						<div class="form-group">
							<label>Ảnh VIP Ingame</label> <input type="file"
								name="ingameImagefile" value = "${ingameImagefile}"/>
						</div>
						<div class="form-group">
							<label>Ảnh Tài Khoản</label>
							<div class="container my-4" style="width: auto">
								<div class="file-loading" style="width: 100%; height: 10px">
									<input id="productImage" name="productImageFile" type="file"
										multiple required="required" value = "${productImage}">
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary" name="insert" value="insert">Đăng ký</button>
						<button type="reset" class="btn btn-primary">Nhập Lại</button>
						<button type="submit" class="btn btn-primary" name="comeback" value="comeback">Quay lại</button>
	
					</div>
				</form:form>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (right) -->
	</div>
	<!-- /.row -->
</section>

>
<!-- /.content -->