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
								<form:input class="form-control" type="text" path="productTitle" id="productTitle"
									placeholder="Loại Tài Khoản" required="required" maxlength="25" value = "${productBO.productTitle}"/>
								<form:errors path="productTitle" cssStyle="color: #ff0000;" />
							</div>
							<label>Mã Tài Khoản </label>
							<form:input path="productCode" class="form-control" type="text"
								id="productCode" placeholder="Mã Tài Khoản" required="required"
								maxlength="10" value = "${productBO.productCode}"/>
							<form:errors path="productCode" cssStyle="color: #ff0000;" />
						</div>
						<div class="form-group">
							<label>Cấp VIP Ingame </label>
							<form:input path="productVipIngameLevel" type="number"
								pattern="/^-?\d+\.?\d*$/"
								onKeyPress="if(this.value.length==2) return false;" min="0"
								class="form-control" id="productVipIngameLevel"
								placeholder="Cấp VIP Ingame" value = "${productBO.productVipIngameLevel}" />
						</div>
						<div class="form-group">
							<label>Số VIP </label>
							<form:input path="productVipNumber" type="number"
								pattern="/^-?\d+\.?\d*$/"
								onKeyPress="if(this.value.length==2) return false;" min="0"
								class="form-control" id="productVipNumber" placeholder="Số VIP" value = "${productBO.productVipNumber}" />
						</div>
						<div class="form-group">
							<label>Thông Tin Tài Khoản </label>
							<form:input path="productInfo" type="text" class="form-control"
								id="productInfo1" placeholder="Thông Tin Tài Khoản"
								required="required" value = "${productBO.productInfo}"/>
							<form:errors path="productInfo" cssStyle="color: #ff0000;" />
						</div>
						<div class="form-group">
							<label>Giá Tài Khoản </label>
							<form:input path="productPrice" type="number" pattern="/^-?\d+\.?\d*$/"
								 min="0" class="form-control"
								id="productPrice" placeholder="VND" value = "${productBO.productPrice}" />
							<form:errors path="productPrice" cssStyle="color: #ff0000;" />
						</div>
						<div class="form-group">
							<label>Ảnh VIP Ingame</label> <input type="file"
								name="ingameImagefile" value = "${productBO.productVipIngameImage}"/>
						</div>
						<div class="form-group">
							<label>Ảnh Tài Khoản</label>
		                    <div class ="file-preview" >
		                        <div class="file-drop-zone"> 
		                          <div class="file-preview-thumbnails" id="showFiles">
			                          <c:if test="${not empty productImage}">
			                          <c:forEach var="product" items="${productImage}">
			                              <div class="theme-explorer-fas"> 
				                              <div class="kv-file-content">
				                                  <img class="file-preview-image" src="<c:url value="/upload/"/>${product.productImage}">
				                               </div>
			
			                                   <div class="file-details-cell">
			                                     <div class="explorer-caption"> </div>
			                                   </div>
			
			                                   <div class="file-actions-cell">
			                                     <div class="file-actions">
			                                       <div class="file-footer-buttons">
			                                         <button type="button" class="btn btn-default" onclick="submitForm('D','${product.productCode}',${product.productImageId})">
			                                           <i class="fa fa-trash" aria-hidden="true"></i>
			                                         </button>
			                                       </div>
			                                     </div>
			                                   </div>
			                              </div>
			                              </c:forEach>
			                           </c:if>
		                            </div>
		                          </div>
		                      </div>
		                      <label style="margin-top:2px;background-color: #eee;border: 1px solid #ccc; font-size:14px; border-radius: 3px;padding:2px 7px 2px 7px" for="productImageFile1">Chọn tệp</label>
		                      <span id="coutFile">Không có tệp nào được chọn</span>
		                      <input type="file" class="productImageFile" id="productImageFile1" name="productImageFile" accept='image/*' style="display: none" multiple required="required"/>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="submitForm('${mode}','${product.productCode}',${product.productImageId})" >${update}</button>
						<button type="reset" class="btn btn-primary" onclick="getfocus()">Nhập Lại</button>
						<button class="btn btn-primary" onclick="test()">Quay lại</button>
	
					</div>
				</form:form>
				<script type="text/javascript">
				function submitForm(mode,code,imgId) {
					if(mode == "D"){
						document.getElementById('formAccountInfor').action = "${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan/xoa?image="+imgId;
						document.getElementById("showFiles").focus();
					}
					if(mode == "U"){
						document.getElementById('formAccountInfor').action = "${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan/chinh-sua";
						document.getElementById("showFiles").focus();
					}
					
					if(mode == "I"){
						document.getElementById('formAccountInfor').action = "${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan/them-moi";
						document.getElementById("showFiles").focus();
					}
					
					document.getElementById('formAccountInfor').submit();
				}
				
				function test() {
					location.replace("${pageContext.request.contextPath}/trang-chu/thong-tin-tai-khoan");
				}

				function getfocus() {
					  document.getElementById("productTitle").focus();
					  
				}
				</script>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (right) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->