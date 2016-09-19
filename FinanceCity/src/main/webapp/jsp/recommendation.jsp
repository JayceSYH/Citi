<%--
  Created by IntelliJ IDEA.
  User: Sorumi
  Date: 16/9/11
  Time: 下午2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="edu.nju.service.POJO.CommonPortfolio"%>
<%@ page language="java" import="edu.nju.service.POJO.CommonProductInfo"%>

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
    <link href="${basePath}css/recommendation.css" rel="stylesheet">

    <script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
    <script>
        ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];
    </script>
    <link href='https://fonts.googleapis.com/css?family=Lato:100' rel='stylesheet' type='text/css'>

    <script src="${basePath}js/recommendation.js"></script>
<script type="text/javascript">
function buySubmit(id){
	document.getElementById('hidValue').value=id;
	document.getElementById('buyCombine').submit();
	return true;
}
</script>
</head>
<body>
<s:include value="header.jsp"></s:include>

<div class="main">
<s:form action="buyCombine" name="buyCombine" method="post" id="buyCombine">
<input type="hidden" id="hidValue" name="hidValue"/>
    <div class="container top-margin inline-container">
       <%List<CommonPortfolio> recArr=(List<CommonPortfolio>)request.getAttribute("recArr"); 
       int arraySize=recArr.size();
       for(int i=0;i<recArr.size();i++){ 
        		String divId = "radarChart"+i;
        		int flowScore=recArr.get(i).getFlow_score();
        		int lthScore=recArr.get(i).getLength_score();
        		int riskscore=recArr.get(i).getRisk_score();
        		int yieldscore=recArr.get(i).getYield_score();
        		int j=i+1;
        %>
     
        <div class="recommendation-container">
            <div class="recommendation-header">
                <h6>推荐<%=j %></h6>
                <h5><span>¥ &nbsp;</span><%=recArr.get(i).getTotal_amount() %></h5>
            </div>
            <ul>
            <%List<CommonProductInfo> commonList=recArr.get(i).getProducts();
            for(int k=0;k<commonList.size();k++){
            	String percentId = "percentChart"+k;
            	%>
                <li class="product">
                    <div class="product-title">
                        <span><%=commonList.get(k).getName() %></span>
                       <!-- <span><%=commonList.get(k).getProductType()%></span> 
                        <span><%=commonList.get(k).getPercentage()%></span>
                       --> 
                        <span>¥&nbsp;<%=commonList.get(k).getAmount() %></span>
                    </div>
                    <div id=<%=percentId %> class="product-chart"></div>
                </li>
                <script type="text/javascript">
                zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
                zingchart.THEME="classic";
                var per=<%=commonList.get(k).getPercentage()%>;
                per=per.toFixed(1)+"%";
                var myConfig = {
                    "globals": {
                        "font-family":"Lato",
                        "font-weight":"100"
                    },
                    "graphset":[
                        {
                            "type":"ring",
                            "background-color":"#fff",
                            "tooltip":{
                                "visible":0
                            },
                            "plotarea":{
                                "margin":"0% 0% 0% 0%"
                            },
                            "plot":{
                                "slice":15,
                                "ref-angle":270,
                                "detach":false,
                                "hover-state":{
                                    "visible":false
                                },
                                "value-box":{
                                    "visible":true,
                                    "type":"first",
                                    "connected":false,
                                    "placement":"center",
                                    "text":per,
                                    "rules":[
                                        {
                                            "rule":"%v > 50",
                                            "visible":false
                                        }
                                    ],
                                    "font-color":"#000",
                                    "font-size":"12px"
                                },
                                "animation":{
                                    "delay":0,
                                    "effect":2,
                                    "speed":"600",
                                    "method":"0",
                                    "sequence":"1"
                                }
                            },
                            "series":[
                                {
                                    "values":[<%=commonList.get(k).getPercentage()%>],
                                    "background-color":"#FDCB0A",
                                    "border-color":"#fff",
                                    "border-width":"1px",
                                    "shadow":0
                                },
                                {
                                    "values":[100-<%=commonList.get(k).getPercentage()%>],
                                    "background-color":"#eee",
                                    "border-color":"#fff",
                                    "border-width":"1px",
                                    "shadow":0
                                }
                            ]
                        }
                    ]
                };

                zingchart.render({
                    id : '<%=percentId %>',
                    data : myConfig,
                    height: 50,
                    width: 50,
                    hideprogresslogo: true
                });
                </script>
                
                <%} %>
            </ul>





            <%--<h6 class="radar-title">预计收益曲线图</h6>--%>
            <div id=<%=divId %> class="radar-chart"></div>

			   <script type="text/javascript">
        zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/"
        zingchart.THEME="classic";
        var myConfig =
        {
            "type": "radar",
            "plot": {
                "aspect": "line"
            },
            "background-color": "Transparent",

            "title": {
                "background-color": "none",
                "font-color": "000",
                //"font-size": "22px"
            },
            "tooltip": {
                //"text": "%t<br>%k Is %v",
                "shadow": 0,
                "border-radius": 3
            },
            "scale-k": {
                "background-color": "none",
                "values": [
                    "收益性",
                    "流动性",
                    "平均期限",
                    "风险性",
                ],
                "item": {
                    "font-color":"#808080",
                    "font-weight":"100",
                    "font-size": "10px",
                    "padding-left": "5px",
                    "padding-bottom": "5px"
                },
                "guide": {
                    "line-color": "",
                    "line-style": "solid",
                    "line-width": "2px",
                    "items": [
                        {
                            "background-color": "#fff"
                        }
                    ]
                },
                "tick": {
                    "visible": false
                }
            },
            "scale-v": {
                "values": [
                    30,40,60,80,100
                ],
                "item": {
                    "font-color":"Transparent",
                    "padding-left": "0px",
                    "font-size": "10px"
                },
                "ref-line": {
                    "line-color": ""
                },

                "tick": {
                    "line-color": ""
                }
            },
            "series": [
                {
                    "values": [
                        <%=yieldscore%>,<%=flowScore%>, <%=lthScore%>,<%=riskscore%>
                    ],
                    "aspect": "line",
                    "text": "ER",
                    "line-color": "#11b7f3",
                    "background-color": "none",
                    "line-width": "2px",
                    "alpha": "1",
                    "marker": {
                        "background-color": "#11b7f3",
                        "size": "3",
                        "border-color": "#11b7f3",
                        "alpha": "1"
                    }
                },
                {
                    "aspect": "line",
                    "text": "ENT",
                    "line-color": "#11b7f3",
                    "line-width": "4px",
                    "alpha": "0",
                    "marker": {
                        "background-color": "#666666",
                        "size": "4",
                        "border-color": "#666666",
                        "alpha": "0.55"
                    }
                }
            ]
        };

        zingchart.render({
            id : '<%=divId %>',
            data : myConfig,
            height: 300,
            width: 300
        });
        </script>
            <div class="recommendation-footer">

                <button style="width:270px;color:white" class="button-style" id="btnA" onclick="return buySubmit('<%=recArr.get(i).getCheckCode()%>')">购买此组合 -></button>
            </div>
        </div>
		<%}%>
      

    </div>
    </s:form>
</div>


<s:include value="footer.jsp"></s:include>

</body>
</html>
