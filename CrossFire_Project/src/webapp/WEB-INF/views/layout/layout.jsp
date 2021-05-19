<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Admin CrossFire</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link  href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet" type="text/css"/>
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- DATA TABLES -->
    <link href='<c:url value="/resources/plugins/datatables/dataTables.bootstrap.css"/>' rel="stylesheet" type="text/css"/>
    <!-- Ionicons -->
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href='<c:url value="/resources/dist/css/AdminLTE.min.css"/>' rel="stylesheet" type="text/css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href='<c:url value="/resources/dist/css/skins/_all-skins.min.css"/>' rel="stylesheet" type="text/css"/>
    
    <link href='<c:url value="/resources/css/fileinput.css"/>' media="all" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" crossorigin="anonymous">
    <link href='<c:url value="/resources/themes/explorer-fas/theme.css"/>' media="all" rel="stylesheet" type="text/css"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>

    <script src='<c:url value="/resources/js/plugins/piexif.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/plugins/sortable.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/fileinput.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/locales/fr.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/locales/es.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/themes/fas/theme.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/themes/explorer-fas/theme.js"/>' type="text/javascript"></script>

  </head>
  <body class="skin-blue">
    <div class="wrapper">

		<tiles:insertAttribute name="header" />
       <!-- Left side column. contains the logo and sidebar -->
		<tiles:insertAttribute name="menu" />

      <!-- Right side column. Contains the navbar and content of the page -->
      <div class="content-wrapper">
		<tiles:insertAttribute name="body" />
      </div><!-- /.content-wrapper -->
	<tiles:insertAttribute name = "footer" />
    </div><!-- ./wrapper -->

    
    <!-- Bootstrap 3.3.2 JS -->
    <script src='<c:url value = "/resources/bootstrap/js/bootstrap.min.js"/>'></script>
    <!-- DATA TABES SCRIPT -->
    <script src='<c:url value = "/resources/plugins/datatables/jquery.dataTables.js"/>' type="text/javascript"></script>
    <script src='<c:url value = "/resources/plugins/datatables/dataTables.bootstrap.js"/>' type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src='<c:url value = "/resources/dist/js/app.min.js"/>' type="text/javascript"></script>
    <!-- AdminLTE for demo purposes -->
    <script src='<c:url value = "/resources/dist/js/demo.js"/>' type="text/javascript"></script>
    
    <!-- page script -->
    <script type="text/javascript">
      $(function () {
        $("#example1").dataTable();
        $('#example2').dataTable({
          "bPaginate": true,
          "bLengthChange": false,
          "bFilter": false,
          "bSort": true,
          "bInfo": true,
          "bAutoWidth": false
        });
      });
      
      $(document).ready(function () {
          $("#productImage").fileinput({
              'theme': 'explorer-fas',
              'allowedFileExtensions': ['jpg', 'png', 'gif'],
              'uploadUrl': '#',
              overwriteInitial: false,
              initialPreviewAsData: true,
              initialPreview: [
              ],
              initialPreviewConfig: [
              ]
          });
      });
    </script>
  </body>
</html>