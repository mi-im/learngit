<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
	/* if(top.location!=self.location){
	      top.location=self.location;
	 } */
	 
	 	var result = false;
	 
		function sendValicode(){
			var valicode = $("#valicode").val();
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/user/checkValicode.do",
				async:false,  //同步
				data:{"valicode":valicode},
				success:function(dt){
					if(dt==1){
						result = true;
					}else{
						result = false;
						alert("验证码错误");
					}
				}
			});
			return result;
		 }
	 
	 	function checkValicode(){
	 		if(sendValicode()){
	 			return true;
	 		}
	 		return false;
	 	}
	 	
    </script>
    
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市订单管理系统</h1>
        </header>
        <section class="loginCont">
        	
	        <form class="loginForm" action="${pageContext.request.contextPath }/user/logindo.do"  name="actionForm" id="actionForm"  method="post"  
	        	onsubmit="return checkValicode();">
	        	<div class="info" style="height:20px">${error }</div>
				<div class="inputbox" style="height:45px;">
                    <label for="user">用户名：</label>
					<input type="text" class="input-text" id="userCode" name="userCode"  value="${userCode }"  placeholder="请输入用户名" required/>
				</div>	
				<div class="inputbox" style="height:45px;">
                    <label for="mima">密码：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
                </div>
                <div class="inputbox" style="height:45px;">
                    <label>验证码：</label><input type="text" id="valicode" name="valicode"   placeholder="验证码" required style="width:128px;vertical-align:middle;"/>
                    <img id="valicodeImg" title="看不清" style="vertical-align:middle;" src="${pageContext.request.contextPath }/user/valicode.do"
                    	 onclick="this.src='${pageContext.request.contextPath }/user/valicode.do?'+new Date();">
                </div>	
				<div class="subBtn">
                    <input type="submit" value="登录"  style="padding-top: 0px;padding-bottom: 0px;"/>
                    <input type="reset" value="重置" style="padding-top: 0px;padding-bottom: 0px;"/>
                    <a href="${pageContext.request.contextPath }/user/addUser.do">注册</a>
                </div>	
			</form>
        </section>
    </section>
</body>
</html>
