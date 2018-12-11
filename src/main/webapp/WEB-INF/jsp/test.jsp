<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>系统登录 - 超市订单管理系统</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/statics/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	//var valicodeOK = false;
	$(function(){
		/* $("#valicode").blur(function(){
			sendValicode();
		}); */
		$("#btnTest").click(function(){
			sendValicode();
		});
	});
	
	function sendValicode(){
		var fruitList = [{"name":"苹果","price":"10.5","time":"2018-06-17"}
		,{"name":"abcd","price":"10.51","time":"2018-06-17"}];
		//把json对象转换成字符串
		var fruitListStr = JSON.stringify(fruitList);
		
		$.ajax({
			type:"POST",//请求类型
			url:"${pageContext.request.contextPath }/test/testSend.do",//请求的url
			//data:{"fruitList":fruitListStr},//请求参数
			data:{"fruitList":fruitList},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				console.log(data.result);
			}
		});
	}
</script>
</head>
<body>
	<%-- <form class="loginForm"
		action="${pageContext.request.contextPath }/send.do"
		name="actionForm" id="actionForm" method="post">
		<div class="info">${error }</div>
		<div class="inputbox">
			<label for="user">用户名：</label> <input type="text" class="input-text"
				id="userCode" name="userCode" placeholder="请输入用户名" required />
		</div>
		<div class="inputbox">
			<label for="mima">密码：</label> <input type="password"
				id="userPassword" name="userPassword" placeholder="请输入密码" required />
		</div>
		<div class="subBtn">
			<input type="submit" value="登录" /> <input type="reset" value="重置" />
		</div>
	</form>
 --%>
 	<input type="button" id="btnTest" value="测试"/>
 	
 	<form action="${pageContext.request.contextPath}/test/testSend.do" method="POST"> 
		 
		姓名：<input type="text" name="fruitList[0].name" value="哈哈"/>
		性别：<input type="text" name="fruitList[0].price" value="男"/>
		<hr/>
		
		姓名：<input type="text" name="fruitList[1].name" value="呵呵"/>
		性别：<input type="text" name="fruitList[1].price" value="男"/>
		<hr/>

		<input type="submit" value="批量注册"/>
		
	</form>
 	test.jsp
 </body>
</html>
