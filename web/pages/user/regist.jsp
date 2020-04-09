<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
	<!--静态包含base标签，css样式，jquery文件-->
	<%@include file="/pages/common/head.jsp"%>
<script type="text/javascript">
	$(function () {
		$("#username").blur(function () {
			//1 获取用户名
			var username = this.value;
			$.getJSON("http://localhost:8088/mall_02/UserServlet","action=ajaxExistUsername&username=" + username,function (data) {
				if (data.existUsername) {
					$("span.errorMsg").text("用户名已存在！");
				} else {
					$("span.errorMsg").text("用户名可用！");
				}
			});
		});

		$("#code_img").click(function () {
			this.src = "Kaptcha.jpg?d=" + new Date();
		});


		//给注册绑定单击事件
		$("#sub_btn").click(function () {
			//用户名验证
			var usernameText = $("#username").val();
			var usernamePatt = /^\w{5,12}$/;
			if (!usernamePatt.test(usernameText)){
				$("span.errorMsg").text("用户名不合法！");
				return false;
			}
			//密码验证
			var passwordText = $("#password").val();
			var passwordPatt = /^\w{5,12}$/;
			if (!passwordPatt.test(passwordText)){
				$("span.errorMsg").text("密码不合法！");
				return false;
			}
			//确认密码
			var repwdText = $("#repwd").val();
			if (repwdText != passwordText){
				$("span.errorMsg").text("确认密码错误！");
				return false;
			}
			//邮箱验证
			var emailText = $("#email").val();
			var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!emailPatt.test(emailText)){
				$("span.errorMsg").text("邮箱格式错误！");
				return false;
			}
			var codeText = $("#code").val();
			codeText = $.trim(codeText);
			if (codeText == ""){
				$("span.errorMsg").text("验证码不能为空");
				return false;
			}
			$("span.errorMsg").text("");

		});
	});

</script>

	<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<!--img class="logo_img" alt="" src="../../static/img/logo.jpg" -->
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">
									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
								</span>
							</div>
							<div class="form">
								<form action="UserServlet"method="post">
									<input type="hidden"name="action"value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1"
										   name="username" id="username"
										   value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1"
										   name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1"
										   name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email"
										   id="email"
										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"/>

									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;" name="code" id="code"/>
									<img id="code_img" alt="" src="Kaptcha.jpg" style="float: right; margin-right: 40px;width: 110px;height: 30px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>