<%--
  Created by IntelliJ IDEA.
  User: Sorumi
  Date: 16/9/11
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="edu.nju.vo.TradeHistoryVO"%>
<%@ page language="java" import="edu.nju.service.POJO.AssetValue"%>
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
    <link href="${basePath}css/asset.css" rel="stylesheet">

    <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
    <script src="https://www.amcharts.com/lib/3/pie.js"></script>
    <script src="https://www.amcharts.com/lib/3/serial.js"></script>
    <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>

    <script src="${basePath}js/asset.js"></script>
<script type="text/javascript">
	var msg = "${requestScope.tipMessage}";
	if (msg != "") {
		alert(msg);
	}
	</script>
</head>
<body>
<s:include value="header.jsp"></s:include>

<div class="main" style="margin-bottom:20px">
    <div class="container top-margin inline-container">

        <div class="asset-allocation asset-block">
            <h5 class="title">资产配置</h5>
            <div id="allocation-chart"></div>
        </div>
		<script language="javascript" type="text/javascript">
		function initAllocationChart() {
			var names = new  Array();  
			var buys = new  Array();  
			<%List<String> nameArr=(List<String>)request.getAttribute("proArr");
			List<Double> buyArr=(List<Double>)request.getAttribute("buyArr");
			List<Double> currentArr=(List<Double>)request.getAttribute("currentArr");
			List<Integer> pidArr=(List<Integer>)request.getAttribute("pidArr");
			int arrLth=0;
			   if(nameArr!=null)
			   {
				arrLth=nameArr.size();
			    for(int i=0;i<nameArr.size();i++)
			    {
			  %>
			  names[<%=i%>]='<%=nameArr.get(i)%>';
			  buys[<%=i%>]='<%=buyArr.get(i)%>';
			  var lth=<%=nameArr.size()%>;
			  <%   } 
			   }
			%>
			var arrayObj = new Array([lth]);
			for(i=0;i<lth;i++){
			var key1 = 'title';
			var key2 = 'value';
			var map = {};
			map[key1] = names[i];
			map[key2] = buys[i];
			arrayObj[i]=map;
				
			}
			var chart = AmCharts.makeChart( "allocation-chart", {
			    "type": "pie",
			    "theme": "light",
			    "dataProvider": 
			       arrayObj,
			    "titleField": "title",
			    "valueField": "value",
			    "labelRadius": 5,

			    "radius": "30%",
			    "innerRadius": "60%",
			    "labelText": "[[title]]",
			    "export": {
			        "enabled": false
			    }
			} );
		}
		

		function submitSold(pid){
			document.getElementById('hiddenId').value=pid;
			return true;
		}
		
		function generatechartData() {
			var dateTime = new  Array();  
			var valueArr=new Array();
			<%List<String> dateArr=(List<String>)request.getAttribute("dateArr");
			List<Double> valueArr=(List<Double>)request.getAttribute("valueArr");
			int hth=0;
			   if(dateArr!=null)
			   {
				   hth=dateArr.size();
			    for(int i=0;i<dateArr.size();i++)
			    {
			    	%>
			    	dateTime[<%=i%>]='<%=dateArr.get(i)%>';
			    	valueArr[<%=i%>]='<%=valueArr.get(i)%>';
			    	<%
			    }
			   }
			%>
			  var chartData = [];
		    for (var i = 0; i < dateTime.length; i++) {
		        // we create date objects here. In your data, you can have date strings
		        // and then set format of your dates using chart.dataDateFormat property,
		        // however when possible, use date objects, as this will speed up chart rendering.
		        chartData.push({
		            date: dateTime[i],
		            visits: valueArr[i]
		        });
		    }
		    return chartData;
		}
		</script>
        <div class="asset-price asset-block">
            <h5 class="title">资产价值曲线</h5>
            <div id="price-chart"></div>
        </div>

        <div class="asset-product asset-block">
            <h5 class="title">投资产品</h5>
            <s:form action="sold" name="sold" method="post">
             <input type="hidden" id="hiddenId" name="hiddenId"/>
            <table>
                <tr>
                    <th class="product-name">产品</th>
                    <th class="product-price">投资金额(￥)</th>
                    <th class="product-total">本利和(￥)</th>
                    <th></th>
                </tr>
                <%for(int i=0;i<arrLth;i++){%>
                	<tr>
                    <td width=220><span style="font-size: 12px"><%=nameArr.get(i) %></span></td>
                    <td><%=buyArr.get(i) %></td>
                    <td><%=currentArr.get(i) %></td>
                    <%int pid=pidArr.get(i); %>
                    <td><button class="button-style" onclick="return submitSold('<%=pid%>')">卖出</button></td>
                </tr>
                <% } %>
            </table>
            </s:form>
        </div>

        <div class="asset-timeline asset-block">
            <h5 class="title">投资时间线</h5>
            <table>
			<% List<TradeHistoryVO> tradeList=(List<TradeHistoryVO>)request.getAttribute("tradeList");%>
                	<s:iterator value="#request.tradeList">
                <tr>
                    <td>
                        <div class="opeartion-block timeline-due"><s:property value="TradingType" /></div>
                    </td>
                    <td><span style="font-size:12px"><s:property value="Date" /></span></td>
                    <td><span style="font-size:12px"><s:property value="ProductName" /></span></td>
                    <td><span style="font-size:12px"><s:property value="TradingVolume" /></span></td>
                </tr>
					</s:iterator>
            </table>
        </div>
    </div>
</div>

<s:include value="footer.jsp"></s:include>

</body>
</html>
