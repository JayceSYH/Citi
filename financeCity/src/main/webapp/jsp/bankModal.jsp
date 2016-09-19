<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<script language="javascript" type="text/javascript">
function setBankValue(pid,pname,price){
	document.getElementById("protype").value="银行理财";
	document.getElementById("priceType").value="起购金额";
	document.getElementById("propid").value=pid;
	document.getElementById("proname").value=pname;
	document.getElementById("proprice").value=price;
	document.getElementById("purchaseForm").submit();
	return true;
}
</script>


		<!-- 银行理财模态框（Modal） -->
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 550px; height: 1600px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">银行理财</h4>
					</div>
					<div class="modal-body" style="">
						<div style="height: 180px;">
							<div class="product-bigicon bank-circle">
								<span style="font-size: 35px"><b><s:property value="#product.productBank.expectedRate"/></b></span><br /> <span
									style="font-size: 8px;">年化收益率</span>
								<hr class="line" />
								<div style="margin-top: -10%;'">
									<span style="font-size: 20px"><b>银行理财</b></span>
								</div>
							</div>
							<div class="rightPart">
								<span style="font-size: 16px"><s:property value="#product.name"/></span><br /> <span
									style="font-size: 14px">起购金额：￥<s:property value="#product.initial_money"/></span><br /> <span
									style="font-size: 14px">开放日：<s:property value="#product.open_date"/></span><br />
									<input type="submit" class="block-button"
									style="font-size: 20px; margin-top: 10px" id="bankBuy" value="购 &nbsp买"
									 onclick="return setBankValue('<s:property value="#product.pid"/>','<s:property value="#product.name"/>','<s:property value="#product.productBank.purchaseThreshold"/>')"/>
							</div>
						</div>
<!-- 
						<div style="height: 180px; margin-top: 4%">
							<div class="smallBlock"></div>
							<span class="blueFont">收益曲线</span>
							<select style="margin-left:10%;" id="selectDay">
							<option value="7">最近7天</option>
							<option value="15">最近15天</option>
							<option value="30">最近30天</option>
							</select>
							<button  id="dayTJ">确定</button>
							<div class="income"></div>
						</div>
 -->
					<div style="height: 100%; margin-top: 2%">
					<table border="0"  style="margin-left: 5%; width: 90%;">
						<tr>
							<td><span class="typeFont">基本信息</span></td>
							<td><span style="font-size: 14px"></span></td>
						</tr>
						<tr>
							<td valign="top"><span style="font-size: 14px">产品名称</span></td>
							<td><span class="typeFont"><s:property value="#product.name"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">产品期次</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.session"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">管理机构</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.institutionManage"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">托管机构</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.custodian"/></span></td>
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
							<td><span style="font-size: 14px">预计年化收益率</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.expectedRate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">收益类型</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.incomeType"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">风险等级</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.riskLevel"/></span></td>
						</tr>
					   <tr>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="typeFont">申请赎回</span></td>
							<td><span class="typeFont"></span></td>
						</tr>
												<tr>
							<td><span style="font-size: 14px">是否净值型</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.ifNavType"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">是否封闭</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.ifClose"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">申购费率</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.ratePurchase"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">赎回费率</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.rateRedem"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">广义管理费率</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.rateManage"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">起购金额</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.purchaseThreshold"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">递增购买最小单位</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.increasingUnit"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">募集开始日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.onPurchaseDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">募集截止日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.offPurchaseDate"/></span></td>
						</tr>
							<tr>
							<td><span style="font-size: 14px">起息日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.firstAccrDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">开放日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.onRedemptionDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">期限</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.length"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">赎回速度</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.redemSpeed"/></span></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="typeFont">投资范围</span></td>
							<td><span class="typeFont"></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">理财币种</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.currency"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">投资范围</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.investField"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">投资比例</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.investRatio"/></span></td>
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
							<td><span style="font-size: 14px">产品编码</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.productCode"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">登记编码</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.registerCode"/></span></td>
						</tr>
												<tr>
							<td><span style="font-size: 14px">销售区域</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.salesRegion"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">运行规模上限</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.sizeUpperLimit"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">理财本金及收益支付</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.payType"/></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行对象</span></td>
							<td><span class="typeFont"><s:property value="#product.productBank.objectOriented"/></td>
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