<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
    request.setAttribute("basePath", basePath);
%>
  <title>InvestGO</title>
 <base href="<%=basePath%>">
<link href="${basePath}css/order.css" rel="stylesheet">
<link href="${basePath}css/mycss.css" rel="stylesheet">
 <title>InvestGO</title>
</head>
<body>
<s:include value="header.jsp"></s:include>
	<div style="height: 600px">
		<div class="container"
			style="margin-top: 30px; background-color: white;">
			<br />
			<div class="smallBlock"></div>
			<span class="blueFont">支付绑定</span>
			<div class="signup2Content" style="width:350px">
			<s:form action="signup3" method="post">
			 <span style="font-size:15px;">支付类型</span><br/><br/>
				  <input id="radio1" type="radio" name="way" value="1" checked="checked">
                  <label for="radio1" style="font-size:18px;"><span><span></span></span><img src="${basePath}img/card.png" height="25" width="40"></img>&nbsp&nbsp&nbsp银行卡</label>
                  <input  id="radio2" type="radio" name="way" value="1">
                  <label for="radio2" style="font-size:18px;"><span><span></span></span><img src="${basePath}img/alipay.jpg" height="25" width="30"></img>&nbsp&nbsp&nbsp&nbsp支付宝</label>
                  <input id="radio3" type="radio" name="way" value="1">
                  <label for="radio3" style="font-size:18px;"><span><span></span></span><img src="${basePath}img/wechat.jpg" height="25" width="30"></img>&nbsp&nbsp&nbsp&nbsp微信支付</label>
                <br/><br/>
                <span style="font-size:15px;">支付绑定</span><br/><br/>
                <span style="font-size:15px;margin-left:30px">具体信息</span><br/><br/>
                <span style="font-size:15px;color:red"></span><br/><br/>
                <button class="wideButton">确定</button>
                </s:form>
			</div>

		</div>
	</div>

	<s:include value="footer.jsp"></s:include>

</body>
</html>