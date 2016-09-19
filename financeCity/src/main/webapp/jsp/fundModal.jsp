<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*"%>
<html>


<script language="javascript" type="text/javascript">
function setFundValue(pid,pname,price){
	document.getElementById("protype").value="基金";
	document.getElementById("priceType").value="最新净值";
	document.getElementById("propid").value=pid;
	document.getElementById("proname").value=pname;
	document.getElementById("proprice").value=price;
	document.getElementById("purchaseForm").submit();
	return true;
}
</script>

<script type="text/javascript">
	var dateArr = new Array();
	var navArr = new Array();
	$(function() {
		$("#dayTJ").click(function() {
			//提交的参数，name和inch是和struts action中对应的接收变量
			var params = {
				day : $("#selectDay").val(),
				pid :<s:property value="#product.pid"/>,
			};
			$.ajax({
				type : "POST",
				url : "dayTJ",
				data : params,
				dataType : "text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
				success : function(json) {
					var obj = $.parseJSON(json); //使用这个方法解析json
					dateArr = eval(obj.dateArr);
					navArr=eval(obj.navArr);
					showChart();
					return true;
				},
				error : function(json) {
					return false;
				}
			});
		});
	});
</script>

<script>
			
		
			
			
			var chartLine = null;
			function showChart(){
				var data = {
						labels : dateArr,
						datasets : [
							{
								lineItemName : "fund",
								fillColor : "rgba(220,220,220,0.5)",
								strokeColor : "rgba(220,220,220,1)",
								pointColor : "rgba(220,220,220,1)",
								pointStrokeColor : "#fff",
								data : navArr
							}
						]
					};
				document.getElementById("myChart").width=450;
				document.getElementById("myChart").height=240;
				document.getElementById("chartDIV").style.width=460;
				document.getElementById("chartDIV").style.height=250;
				var ctx = document.getElementById("myChart").getContext("2d");
				chartLine = new Chart(ctx).Line(data);
				
				initEvent(chartLine, clickCall);
				return true;
			}
			
			function hideChart(){
				document.getElementById("myChart").width=1;
				document.getElementById("myChart").height=1;
				document.getElementById("chartDIV").style.width=1;
				document.getElementById("chartDIV").style.height=1;
			}
			
			function clickCall(evt) {
				var point = chartLine.getPointSingleAtEvent(evt);
				
				if ( point !== null )
					alert( point.label + ": " + point.lineItemName + " ____ " + point.value);
			}
			
			function initEvent(chart, handler) {
				var method = handler;
				var eventType = "click";
				var node = chart.chart.canvas;
								
				if (node.addEventListener) {
					node.addEventListener(eventType, method);
				} else if (node.attachEvent) {
					node.attachEvent("on" + eventType, method);
				} else {
					node["on" + eventType] = method;
				}
			}
		</script>
		<!-- 银行理财模态框（Modal） -->
		<div class="modal fade" id="mymModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 550px; height: 1400px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" onclick="hideChart()">&times;</button>
						<h4 class="modal-title" id="myModalLabel">基金</h4>
					</div>
					<div class="modal-body" style="">
				<div style="height: 180px;">
						<div class="product-bigicon fund-circle">
							<span style="font-size: 35px"><b><s:property value="#product.productFund.expectedRate"/></b></span><br /> <span
								style="font-size: 8px;">收益率</span>
							<hr class="line" />
							<div style="margin-top: -10%;'">
								<span style="font-size: 20px"><b>基金</b></span>
							</div>
						</div>
						<div class="rightPart">
							<span style="font-size: 22px"><s:property value="#product.name"/></span><br /> <span
								style="font-size: 14px">最新净值：¥<s:property value="#product.net_value"/></span><br /> <span
								style="font-size: 14px">状态：<s:property value="#product.state"/></span><br />
							<input type="submit" class="block-button"
									style="font-size: 20px; margin-top: 10px" id="bankBuy" value="购 &nbsp买"
									 onclick="return setFundValue('<s:property value="#product.pid"/>','<s:property value="#product.name"/>','<s:property value="#product.net_value"/>')"/>
						</div>
					</div>

					<div style=" margin-top: 6%">
							<div class="smallBlock"></div>
							<span class="blueFont">收益曲线</span>
							<select style="margin-left:10%;" id="selectDay">
							<option value="7">最近7天</option>
							<option value="15">最近15天</option>
							<option value="30">最近30天</option>
							</select>
							<button class="button-style" id="dayTJ">点击查看</button>
							<div id="chartDIV">
							<canvas id="myChart" width=1 height=1 ></canvas>
							</div>
						</div>
						
					<div style="height: 100%; margin-top: 2%">
					<table border="0"  style="margin-left: 5%; width: 90%;">
						<tr>
							<td><span class="typeFont">基本信息</span></td>
							<td><span style="font-size: 14px"></span></td>
						</tr>
						<tr>
							<td valign="top"><span style="font-size: 14px">法定名称</span></td>
							<td><span class="typeFont"><s:property value="#product.name"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">基金编号</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.productCode"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">管理机构</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.institutionManage"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">托管机构</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.custodian"/></span></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="typeFont">风险收益</span></td>
							<td><span class="typeFont"></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">近一年收益率</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.yearlyRtnRate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">最新净值</span></td>
							<td><span class="typeFont">¥<s:property value="#product.net_value"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">复权净值</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.adjustNav"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">基金投资目标类型</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.category"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">业绩比较基准</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.perfBenchmark"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">跟踪标的</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.targetId"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">风险等级</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.riskLevel"/></span></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="typeFont">申购赎回</span></td>
							<td><span class="typeFont"></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">状态</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.state"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">是否封闭</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.operationMode"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">申购费率</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.ratePurchase"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">赎回费率</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.rateRedem"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">广义管理费率</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.rateManage"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">起购金额</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.purchaseThreshold"/></span></td>
						</tr>
							<tr>
							<td><span style="font-size: 14px">递增购买最小单位</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.increasingUnit"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">募集开始日</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.onPurchaseDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">募集截止日</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.offPurchaseDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">起息日</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.firstAccrDate"/></span></td>
						</tr>
							<tr>
							<td><span style="font-size: 14px">开放日</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.onRedemptionDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">期限</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.length"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">认购速度</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.subscribeSpeed"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">赎回速度</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.redemSpeed"/></span></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="typeFont">其他</span></td>
							<td><span class="typeFont"></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">基金经理</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.managerName"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">基金规模</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.fundSize"/></span></td>
						</tr>
												<tr>
							<td><span style="font-size: 14px">份额规模</span></td>
							<td><span class="typeFont"><s:property value="#product.productFund.shareSize"/></span></td>
						</tr>
					
					</table>
					</div>

				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</html>