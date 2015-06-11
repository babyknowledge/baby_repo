<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link class="cssdeck" rel="stylesheet"
	href="../../resource/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../../resource/css/bootstrap-responsive.min.css" class="cssdeck">
<script type="text/javascript"
	src="../../resource/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>
<title>系统登录</title>
</head>
<body>
	<!-- 	<div class="modal-header"> -->
	<!-- 		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
	<!-- 		<h3>Have an Account?</h3> -->
	<!-- 	</div> -->
	<div class="modal-body">
		<div class="well">
			<!-- 			<ul class="nav nav-tabs"> -->
			<!-- 				<li class="active"><a href="#login" data-toggle="tab">Login</a></li> -->
			<!-- 				<li><a href="#create" data-toggle="tab">Create Account</a></li> -->
			<!-- 			</ul> -->
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane active in" id="login">
					<form class="form-horizontal" action='' method="POST">
						<fieldset>
							<div id="legend">
								<legend class="">Login</legend>
							</div>
							<div class="control-group">
								<!-- Username -->
								<label class="control-label" for="username">用户名</label>
								<div class="controls">
									<input type="text" id="username" name="username" placeholder=""
										class="input-xlarge" required="required">
								</div>
							</div>

							<div class="control-group">
								<!-- Password-->
								<label class="control-label" for="password">密码</label>
								<div class="controls">
									<input type="password" id="password" name="password"
										placeholder="" class="input-xlarge" required="required">
								</div>
							</div>


							<div class="control-group">
								<!-- Button -->
								<div class="controls">
									<button class="btn btn-success">Login</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<!-- 				<div class="tab-pane fade" id="create"> -->
				<!-- 					<form id="tab"> -->
				<!-- 						<label>Username</label> -->
				<!-- 						<input type="text" value="" class="input-xlarge"> -->
				<!-- 						<label>First Name</label> -->
				<!-- 						<input type="text" value="" class="input-xlarge"> -->
				<!-- 						<label>Last Name</label> -->
				<!-- 						<input type="text" value="" class="input-xlarge"> -->
				<!-- 						<label>Email</label> -->
				<!-- 						<input type="text" value="" class="input-xlarge"> -->
				<!-- 						<label>Address</label> -->
				<!-- 						<textarea value="Smith" rows="3" class="input-xlarge"> -->
				<!-- 						</textarea> -->

				<!-- 						<div> -->
				<!-- 							<button class="btn btn-primary">Create Account</button> -->
				<!-- 						</div> -->
				<!-- 					</form> -->
				<!-- 				</div> -->
			</div>
		</div>
	</div>
</body>
</html>