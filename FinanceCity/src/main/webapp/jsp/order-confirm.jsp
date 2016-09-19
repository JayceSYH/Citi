<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>订单信息确认</title>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI() + "?"
			+ request.getQueryString();
	request.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>">
    <link href="${basePath}css/order.css" rel="stylesheet">
    <link href="${basePath}css/common.css" rel="stylesheet">

    <script type="text/javascript" rel="stylesheet" src="../js/jquery.min.js"></script>
    <script type="text/javascript" rel="stylesheet" src="../js/order-confirm.js"></script>

</head>
<body>

<s:include value="header.jsp"></s:include>


<div class="main">
    <div class="container">
        <div class="top-bar white-bg">
            <a class="select-section current">
                <div class="circle">1</div>
                <span class="title">确认订单信息</span>
            </a>
            <div class="line"></div>
            <a class="select-section">
                <div class="circle">2
                </div>
                <span class="title">完成支付</span>
            </a>
        </div>

        <div class="order-info-confirm white-bg">
            <div class="title-rect"></div>
            <div class="inside-container">
                <h2>订单信息</h2>
                <div class="clear"></div>
                <table>
                    <tr>
                        <th class="product-section">产品名称</th>
                        <th class="product-price">价格</th>
                        <th class="product-quantity">购买金额</th>
                    </tr>
                    <s:iterator value="#request.proList">
                    <tr>
                        <td>
                            <div class="product-icon bank-circle">理</div>
                            <div class="product-title">
                                <span class="main-title"><s:property value="Name" /></span>
                                </br>
                                <span class="sub-title"><s:property value="Type" /></span>
                            </div>
                            <div class="clear"></div>
                        </td>
                        <td style="text-align:center;">￥<s:property value="BuyingValue" /></td>
                        <td  style="text-align:center;">
                            <input type="text" id="quantity1" name="quantity" value="1" class="size"/>
                        </td>
                    </tr>
                    </s:iterator>
                </table>
            </div>
            <div class="clear"></div>
        </div>

        <div class="confirm-order white-bg">
            <div class="title-rect"></div>
            <div class="inside-container">
                <div class="total">
                    <span class="total-title">合计:</span>
                    <span class="total-sum">￥1200</span>
                </div>
                <button id="order_confirm" class="block-button">提交订单</button>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<s:include value="footer.jsp"></s:include>

</body>
</html>