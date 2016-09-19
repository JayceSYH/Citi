<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI() + "?"
			+ request.getQueryString();
	request.setAttribute("basePath", basePath);
%>
<title>InvestGO</title>
<base href="<%=basePath%>">
	<link href="${basePath}css/common.css" rel="stylesheet">
	<%--<link href="${basePath}css/order.css" rel="stylesheet">--%>
	<link href="${basePath}css/mycss.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>

	<div class="main" style="height: 480px">
		<div class="container"
			style="margin-top: 30px; background-color: white;">
			<br />
			<div class="smallBlock"></div>
			<span class="blueFont">注册</span>
			<div class="signupContent">
				<s:form action="register" method="post">
					<table border="0" style="margin-left: 15%; width: 70%">
						<tr>
							<td><span style="font-size: 14px">手机号码</span></td>
							<td><input type="text" class="inputSize" name="mobile"></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">短信验证码</span></td>
							<td><input type="text" style="height: 30px; width: 150px"
								name="verify">
								<button class="platButton">获取</button></td>

						</tr>
						<tr>
							<td><span style="font-size: 14px">昵称</span></td>
							<td><input type="text" class="inputSize" name="nickname"></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">密码</span></td>
							<td><input type="password" class="inputSize" name="password">
							</td>
						</tr>
						<tr>
							<td colspan="2"><span style="font-size: 14px; color: red"
								id="error"></span></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="wideButton"
								value="完成注册"></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</div>
	<s:include value="footer.jsp"></s:include>
<script type="text/javascript">
	var msg = "${requestScope.message}";
	if (msg != "") {
		document.getElementById('error').innerHTML=msg;
	}
</script>
</body>
</html>