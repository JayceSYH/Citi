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
    <link href="${basePath}css/common.css" rel="stylesheet">
    <%--<link href="${basePath}css/order.css" rel="stylesheet">--%>
    <link href="${basePath}css/mycss.css" rel="stylesheet">

 <title>InvestGO</title>
</head>
<body>
<s:include value="header.jsp"></s:include>
	<div class="main" style="height: 560px">
		<div class="container"
			style="margin-top: 30px; background-color: white;">
			<br />
			<div class="smallBlock"></div>
			<span class="blueFont">个人信息</span>
			<div class="signup2Content">
				<s:form action="signup2" method="post">
				<span style="font-size:15px">您的生日</span><br/>
				 <select name="year" class="signSelector">
				 <%
				 	for(int i=1900;i<2016;i++){
				 %>
                    <option value=<%=i %>><%=i %></option>
                    <%} %>
                </select>
                <select name="month" class="signSelector">
                     <%
				 	for(int i=1;i<13;i++){
				 %>
                    <option value=<%=i %>><%=i+"月" %></option>
                    <%} %>
                </select>
                <select name="day" class="signSelector">
                   <%
				 	for(int i=1;i<32;i++){
				 %>
                    <option value=<%=i %>><%=i+"日" %></option>
                    <%} %>
                </select>
                <br/><br/>
                <span style="font-size:15px">您来自城市还是农村</span><br/>
                <select name="from" class="signLongSelector">
                    <option value="city">城市</option>
                      <option value="country">农村</option>
                </select>
                  <br/><br/>
                <span style="font-size:15px">您目前每月的收入是多少?(单位：元)</span><br/>
                <input type="text" style="height:26px;width:340px" name="income">
                  <br/><br/>
                <span style="font-size:15px">您目前每月的支出是多少?(单位：元)</span><br/>
                <input type="text" style="height:26px;width:340px" name="outlay">
                <br/><br/>
                <span style="font-size:15px;color:red" id="error"></span><br/>
                <button class="wideButton">确定</button>
                </s:form>
			</div>

		</div>
	</div>
<script type="text/javascript">
	var msg = "${requestScope.message}";
	if (msg != "") {
		document.getElementById('error').innerHTML=msg;
	}
</script>
	<s:include value="footer.jsp"></s:include>

</body>
</html>