<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  <!-- Content Header (Page header) -->
 <section class="content-header">
   <h1>
     Thông tin tài khoản CF
   </h1>
   <ol class="breadcrumb">
     <li><a href="#"><i class="fa fa-dashboard"></i> Trang Chủ</a></li>
     <li class="active">Thông tin tài khoản CF</li>
   </ol>
 </section>

 <!-- Main content -->
 <section class="content">
   <div class="row">
     <div class="col-xs-12">
       <div class="box">
         <div class="box-header">
         <spring:url value="/trang-chu/thong-tin-tai-khoan/them-moi" var="productInfoUrl" />
           <button class="btn btn-block btn-success" style="width: 90px;margin-top: 8px; text-align: left;" onclick="location.href='${productInfoUrl}'">Thêm Mới</button>
         </div><!-- /.box-header -->
         <div class="box-body">
           <table id="example1" class="table table-bordered table-striped">
             <thead>
               <tr>
                 <th>Loại Tài Khoản</th>
                 <th>Mã Tài Khoản</th>
                 <th>Cấp VIP Ingame</th>
                 <th>Số VIP</th>
                 <th>Thông Tin Tài Khoản</th>
                 <th>Giá Tài Khoản</th>
                 <th>Tổng số ảnh</th>
                 <th>Thực hiện</th>
               </tr>
             </thead>
             <tbody>
	             <c:forEach var="product" items="${productData}">
			       <tr>
	                 <td>${product.productTitle}</td>
	                 <td>${product.productCode}</td>
	                 <td>${product.productVipIngameLevel}</td>
	                 <td>${product.productVipNumber}</td>
	                 <td>${product.productInfo}</td>
	                 <td>${product.productPrice}</td>
	                 <td>${product.count}</td>
	                 <td>
	                   <button type="submit" class="btn btn-info" onclick="/Them-moi">Chỉnh Sửa</button>
	                   <button type="button" class="btn btn-info" style="background-color: #dd4b39">Xóa</button>
	                 </td>
	               </tr>
				</c:forEach>
             </tbody>
           </table>
         </div><!-- /.box-body -->
       </div><!-- /.box -->
     </div><!-- /.col -->
   </div><!-- /.row -->
 </section><!-- /.content -->