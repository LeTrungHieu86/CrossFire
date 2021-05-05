<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AdminLTE 2 | Log in</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="./src/main/webapp/WEB-INF/views/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="./src/main/webapp/WEB-INF/views/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="./src/main/webapp/WEB-INF/views/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="#"><b>Admin</b> CrossFire</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Đăng nhập để bắt đầu công việc</p>
        <form action="../../index2.html" method="post">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="Email"/>
            <span class="form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password"/>
            <span class="form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">    
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox"> Nhớ mật khẩu
                </label>
              </div>                        
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">Đăng nhập</button>
            </div><!-- /.col -->
          </div>
        </form>
        
        <a href="#">Quên mật khẩu</a><br>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.3 -->
    <script src="./src/main/webapp/WEB-INF/views/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="./src/main/webapp/WEB-INF/views/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="./src/main/webapp/WEB-INF/views/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>