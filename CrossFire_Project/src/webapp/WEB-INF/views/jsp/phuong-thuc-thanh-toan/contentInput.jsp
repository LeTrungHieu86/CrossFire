<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>

 <!-- Content Header (Page header) -->
 <section class="content-header">
   <h1>
     Phương Thức Thanh Toán
   </h1>
   <ol class="breadcrumb">
     <li><a href="#"><i class="fa fa-dashboard"></i> Trang Chủ</a></li>
     <li class="active">Phương Thức Thanh Toán</li>
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
         <form role="form">
           <div class="box-body">
             <!-- select -->
             <div class="form-group">
               <label>Loại phương thức thanh toán</label>
               <select class="form-control">
                 <option>...</option>
                 <option>ATM</option>
                 <option>Ví điện tử</option>
               </select>
             </div>
             <div class="form-group">
               <label>Tên ngân hàng / Ví điện tử </label>
               <select class="form-control">
                 <option>...</option>
                 <option>Techcombank </option>
                 <option>MoMo</option> 
               </select>
             </div>
             <div class="form-group">
               <label for="exampleInputPassword1">Số tài khoản ngân hàng / SĐT (Ví điện tử) </label>
               <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Số tài khoản ngân hàng / SĐT(Ví điện tử)">
             </div>
             <div class="form-group">
               <label for="exampleInputPassword1">Chi nhánh ngân hàng </label>
               <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Chi nhánh ngân hàng">
             </div>
             <div class="form-group">
               <label for="exampleInputPassword1">Tên chủ tài khoản ngân hàng / Tên chủ ví điện tử </label>
               <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Tên chủ tài khoản ngân hàng / Tên chủ ví điện tử">
             </div>                  
             <div class="form-group">
               <label for="exampleInputFile">Ảnh ngân hàng / Ảnh ví điện tử</label>
               <input type="file" id="exampleInputFile">
             </div>
           </div><!-- /.box-body -->

           <div class="box-footer">
             <button type="submit" class="btn btn-primary">Nhập Lại</button>
             <button type="submit" class="btn btn-primary">Đăng Ký</button>
           </div>
         </form>
       </div><!-- /.box -->

       
     </div><!--/.col (right) -->
   </div>   <!-- /.row -->
 </section><!-- /.content -->