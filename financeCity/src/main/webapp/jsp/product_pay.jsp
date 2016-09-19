<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI() + "?"
			+ request.getQueryString();
	request.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>订单付款</title>
    <link href="${basePath}css/order.css" rel="stylesheet">
    <link href="${basePath}css/common.css" rel="stylesheet">
</head>
<script language="javascript" type="text/javascript">
function submitForm(){
	document.getElementById("submitValue").value=document.getElementById("purValue").value;
	document.getElementById("buyForm").submit();
	return true;
}
</script>
<body>
<s:form id="buyForm" action="buyOne" method="post">
 <input type="hidden" name="propid" id="propid" value='<%=request.getAttribute("propid") %>'/>
  <input type="hidden" name="submitValue" id="submitValue"/>
<s:include value="header.jsp"></s:include>


<div class="main">
    
    <div class="container">
        <div class="top-bar white-bg">
            <a href="./" class="select-section">
                <span class="title">完成支付</span>
            </a>
        </div>

        <div class="order-info-pay white-bg">
            <div class="title-rect"></div>
            <div class="inside-container">
                <h2>订单信息</h2>
                <div class="clear"></div>
                <table>
                    <tr>
                        <th class="product-section">产品名称</th>
                        <th class="product-price"><%=request.getAttribute("priceType") %></th>
                        <th class="product-quantity">购买金额</th>
                    </tr>
                    <tr class="current">
                        <td>
                            <div class="product-title">
                                <span class="main-title"><%=request.getAttribute("proname") %></span>
                                </br>
                                <span class="sub-title"><%=request.getAttribute("protype") %></span>
                            </div>
                            <div class="clear"></div>
                        </td>
                        <td>￥<%=request.getAttribute("proprice") %></td>
                        <td><input type="text" id="purValue" name="purValue" style="width:80px"></td>
                    </tr>
                </table>
            </div>
            <div class="clear"></div>
        </div>

        <div class="pay-way white-bg">
            <div class="title-rect"></div>
            <div class="inside-container">
                <h2>支付方式</h2>
                <div class="clear"></div>
                <form>
                    <div class="pay-section">
                        <input id="radio1" type="radio" name="radio" value="1" checked="checked">
                        <label for="radio1"><span><span></span></span>支付方式1</label>
                    </div>
                    <div class="pay-section">
                        <input id="radio2" type="radio" name="radio" value="2">
                        <label for="radio1"><span><span></span></span>支付方式2</label>
                    </div>
                    <div class="pay-section">
                        <input id="radio3" type="radio" name="radio" value="3">
                        <label for="radio1"><span><span></span></span>支付方式3</label>
                    </div>
                </form>
                <input type="submit" class="block-button" value="确认支付" onclick="return submitForm()"/>
            </div>
            <div class="clear"></div>
        </div>

    </div>
</div>
<s:include value="footer.jsp"></s:include>
</s:form>


</body>
</html>