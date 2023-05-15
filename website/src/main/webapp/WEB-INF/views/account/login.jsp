<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);	
	}
});

function frmCheck() {
	if ($("#me_id").val() == "") {
		alert("아이디를 입력해주세요.");
		$("#me_id").focus();
		return false;
	}
	if ($("#me_pwd").val() == "") {
		alert("비밀번호를 입력해주세요.");
		$("#me_pwd").focus();
		return false;
	}
	$("#loginform").submit();
}
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>SB Admin 2 - Login</title>
<!-- Custom fonts for this template-->
<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
	<div class="container">
		<!-- Outer Row -->
<div class="row justify-content-center">
	<div class="col-xl-10 col-lg-12 col-md-9">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
					<div class="col-lg-6">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
							</div>
							<form id="loginform" action ="/account/login_action" method="post">
							<c:if test="${not empty rememberCookie.getValue()}">
								<c:set value="checked" var="checked" />
							</c:if>
								<div class="form-group">
									<input type="text" class="form-control form-control-user" id="me_id" name="me_id" aria-describedby="emailHelp" placeholder="Enter Id..." value="${rememberCookie.getValue() }"/>
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-user" id="me_pwd" name="me_pwd" placeholder="Password" />
								</div>
								<div class="form-group">
                                   <div class="custom-control custom-checkbox small">
                                       <input type="checkbox" class="custom-control-input" id="customCheck" name="customCheck" valeu="true" ${checked} />
                                       <label class="custom-control-label" for="customCheck">아이디 기억</label>
                                   </div>
                               </div>
								<a href="#" class="btn btn-primary btn-user btn-block" onclick="return frmCheck();">로그인</a>
									<hr>
									<a href="/account/register" class="btn btn-google btn-user btn-block">회원가입</a> 
									<a href="/account/search_id" class="btn btn-facebook btn-user btn-block">아이디  찾기</a>
									<a href="/account/search_pwd" class="btn btn-warning btn-user btn-block">비밀번호찾기 찾기</a>
								</form>
								<hr>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
