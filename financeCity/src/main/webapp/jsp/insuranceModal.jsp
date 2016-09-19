<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>

<script language="javascript" type="text/javascript">
function setInsValue(pid,pname,price){
	document.getElementById("protype").value="保险";
	document.getElementById("priceType").value="保险面额";
	document.getElementById("propid").value=pid;
	document.getElementById("proname").value=pname;
	document.getElementById("proprice").value=price;
	document.getElementById("purchaseForm").submit();
	return true;
}
</script>
		<!-- 银行理财模态框（Modal） -->
		<div class="modal fade" id="mybModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 550px; height: 800px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">保险</h4>
					</div>
					<div class="modal-body" style="">
							<div style="height: 180px;">
						<div class="product-bigicon insurance-circle">
							<span style="font-size: 35px"><b>12.95</b></span><br /> <span
								style="font-size: 8px;">预计收益率</span>
							<hr class="line" />
							<div style="margin-top: -10%;'">
								<span style="font-size: 20px"><b>保险</b></span>
							</div>
						</div>
						<div class="rightPart">
							<span style="font-size: 22px"><s:property value="#product.name"/></span><br /> <span
								style="font-size: 14px">保障年限：<s:property value="#product.insurance_age"/>年</span><br />
							<input type="submit" class="block-button"
									style="font-size: 20px; margin-top: 10px" id="bankBuy" value="购 &nbsp买"
									 onclick="return setInsValue('<s:property value="#product.pid"/>','<s:property value="#product.name"/>','<s:property value="#product.productInsurance.denomination"/>')"/>
						</div>
					</div>

					<div style="height: 100%; margin-top: 2%">
					<table border="0"  style="margin-left: 5%; width: 90%;">
						<tr>
							<td><span class="typeFont">基本信息</span></td>
							<td><span style="font-size: 14px"></span></td>
						</tr>
						<tr>
							<td valign="top"><span style="font-size: 14px">险种名称</span></td>
							<td><span class="typeFont"><s:property value="#product.name"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">产品发行公司</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.institutionManage"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">购偿金额</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.indemnity"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">保险产品面额</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.denomination"/></span></td>
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
							<td><span style="font-size: 14px">单位金额赔偿</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.indemnity"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">年化收益率</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.yearRate"/></span></td>
						</tr>
							<tr>
							<td><span style="font-size: 14px">保证利率</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.guaranteedRate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">预期利率</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.expectedRate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">日结算利率</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.dayRate"/></span></td>
						</tr>
					   <tr>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="typeFont">购买相关</span></td>
							<td><span class="typeFont"></span></td>
						</tr>
												<tr>
							<td><span style="font-size: 14px">交费方式</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.payType"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">保障年限</span></td>
							<td><span class="typeFont"><s:property value="#product.productInsurance.warrantyPeriod"/></span></td>
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