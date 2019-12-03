<%@page import="Modelo.Cep"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta CEP</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

</head>

<body>


	<section class="content container-fluid">
		<!-- Pagina Dinamica -->

		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Consulta de CEP <small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
				<li class="active">Cep</li>
			</ol>
		</section>
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box box-default">
						<div class="box-header with-border">
							<form action="consultaServlet" method="post">
								<h3 class="box-title"></h3>
								<div class="row">
									<div class="form-group">
										<div class="row">
											<div class="col-lg-2">
												<label>Cep</label>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-2">
												<input id="cep" type="text" class="form-control"
													placeholder="" name="cep" value="">
											</div>
											<input class="btn btn-primary" type="submit" value="Consultar"/>
											
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="box-footer">
							<p>Projeto Desenvolvido usando Servlet/Json - Equipe : Márcio, Carlos, Antônio Sérgio, João Roberto</p>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->
	</section>




	<!-- jQuery 3.1.1 -->
	<script src="plugins/jQuery/jquery-3.1.1.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>
	<!-- page script -->

</body>
</html>