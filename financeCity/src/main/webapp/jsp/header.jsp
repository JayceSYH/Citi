<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
     <%@ page language="java" import="edu.nju.service.Sessions.FinanceCityUser"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
    request.setAttribute("basePath", basePath);
%>
<html>
 <base href="<%=basePath%>">

 <script  type="text/javascript">
    function assets() 
    { 
    document.getAsset.action="investment"; 
    document.getAsset.submit(); 
    } 
     
    function recommend() 
    { 
    document.getAsset.action="recommend"; 
    document.getAsset.submit(); 
    } 
    
    function product() 
    { 
    document.getAsset.action="search"; 
    document.getAsset.submit(); 
    } 
    </script> 

<div class="header shadow">
    <div class="container">
        <div class="header-square"></div>
        <h1 class="header-title"></h1>
        <div class="header-button">
        <%if(session.getAttribute("user")==null) {%>
        <div  style="float:left;">
         <a href="${basePath}/jsp/signup_step1.jsp" ><button class="button-style">注册</button></a>
        </div>
         <div  style="float:left;">
        <s:form action="loginURL" method="post" name='loginURL' theme="simple">
            <a href="javascript:document.loginURL.submit();" style="float:left"><button class="button-style">登录</button></a>
       </s:form>
       </div>
       <% }else{
    	   FinanceCityUser user = (FinanceCityUser)session.getAttribute("user");
    	   String userName=user.getUserName();
       %>
        <s:form name="getAsset" method="post">
      <!--   <span><%=userName %></span> -->
      	  <a><button class="button-style" onclick="product()">产品 </button></a>
	      <a><button class="button-style" onclick="assets()">我的资产 </button></a>
	      <a><button class="button-style" onclick="recommend()">资产推荐 </button></a>
        </s:form>
       <%} %>
        </div>
    </div>

</div>
</html>