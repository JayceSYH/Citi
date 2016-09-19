<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>

<script language="javascript" type="text/javascript">
function setBondValue(pid,pname,price){
	document.getElementById("protype").value="债券";
	document.getElementById("priceType").value="面值";
	document.getElementById("propid").value=pid;
	document.getElementById("proname").value=pname;
	document.getElementById("proprice").value=price;
	document.getElementById("purchaseForm").submit();
	return true;
}
</script>
		<!-- 债券模态框（Modal） -->
		<div class="modal fade" id="myjModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 550px; height: 1400px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">债券</h4>
					</div>
					<div class="modal-body" style="">
					<div style="height: 180px;">
						<div class="product-bigicon bond-circle">
							<span style="font-size: 35px"><b><s:property value="#product.nominal_interest_rate"/></b></span><br /> <span
								style="font-size: 8px;">年利率</span>
							<hr class="line" />
							<div style="margin-top: -10%;'">
								<span style="font-size: 20px"><b>债券</b></span>
							</div>
						</div>
						<div class="rightPart">
							<span style="font-size: 22px">2016年记账式附息(十七期)国债 160017</span><br />
							<span style="font-size: 14px">债券类型：<s:property value="#product.type"/></span><br /> <span
								style="font-size: 14px">期限：<s:property value="#product.life"/></span><br />
							<input type="submit" class="block-button"
									style="font-size: 20px; margin-top: 10px" id="bankBuy" value="购 &nbsp买"
									 onclick="return setBondValue('<s:property value="#product.pid"/>','<s:property value="#product.name"/>','<s:property value="#product.productBond.par"/>')"/>
					
						</div>
					</div>


					<div style="height: 100%; margin-top: 2%">
					<table border="0"  style="margin-left: 5%; width: 90%;">
						<tr>
							<td><span class="typeFont">基本信息</span></td>
							<td><span style="font-size: 14px"></span></td>
						</tr>
						<tr>
							<td valign="top"><span style="font-size: 14px">债券名称</span></td>
							<td><span class="typeFont"><s:property value="#product.name"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">债券简称</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.abbrName"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">债券代码</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.productCode"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">债券分类</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.type"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行单位</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.institutionIssue"/></span></td>
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
							<td><span style="font-size: 14px">票面利率</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.coupon"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">调整后年利率</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.adjustYearlyRate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">计息、付息方式</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.couponType"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">付息频率(整数次/年)</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.couponFreq"/></span></td>
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
							<td><span style="font-size: 14px">期限</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.length"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行起始日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.onIssueDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行截止日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.offIssueDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">状态</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.state"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">到期日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.maturityDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">起息日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.firstAccrDate"/></span></td>
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
							<td><span style="font-size: 14px">面值(元)</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.par"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行价(元)</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.issuePrice"/></span></td>
						</tr>
												<tr>
							<td><span style="font-size: 14px">上市地</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.exchange"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发布时间</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.publishDate"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">上市流通日</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.listDate"/></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行额(亿元)</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.releaseAmount"/></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">认购对象</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.objectApplyBuy"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">信用级别</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.creditGrade"/></span></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行方式</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.releaseWay"/></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">发行对象</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.objectOriented"/></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">主乘销机构</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.institutionUnderwriter"/></td>
						</tr>
						<tr>
							<td><span style="font-size: 14px">税收状况</span></td>
							<td><span class="typeFont"><s:property value="#product.productBond.taxState"/></td>
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