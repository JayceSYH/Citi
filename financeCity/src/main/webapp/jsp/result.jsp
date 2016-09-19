<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Hermit
  Date: 16/9/3
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>

<script>
    var currentPage = ${currentPage};
    var pageLength = ${pageLength};

    var searchResult;
    searchResult = ${searchResultJSON};

    console.log(searchResult);

</script>

<s:form id="purchaseForm" action="purchaseOne" method="post">
	<input type="hidden" id="propid" name="propid"/>
	<input type="hidden" id="proname" name="proname"/>
	<input type="hidden" id="protype" name="protype"/>
	<input type="hidden" id="proprice" name="proprice"/>
   <input type="hidden" id="priceType" name="priceType"/>
<div id="product_wrapper" class="product-wrapper">

    <s:set name="size" value="1"/>
    <s:iterator id="product" value="#request.searchResult">
        <s:set name="size" value="#size+1"/>
        <s:if test="#product.productType=='bank'">
            <div class="product bank-financing" data-toggle="modal" data-target="#myModal">
                <div class="bank-circle circle">
                    <h1><s:property value="#product.year_rate"/></h1>
                    <h4>年化收益率</h4>
                    <hr>
                    <h3>银行理财</h3>
                </div>
                <div class="more-info">
                    <h3><s:property value="#product.name"/></h3>
                    <br>
                    <div class="column">
                        <span class="column-item u1of3 tag">产品类型</span>
                        <span class="column-item info"><s:property value="#product.product_type"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">收益类型</span>
                        <span class="column-item info"><s:property value="#product.income_type"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">起购金额</span>
                        <span class="column-item info">¥<s:property value="#product.initial_money"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">发行银行</span>
                        <span class="column-item info"><s:property value="#product.distributor_bank"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">发行机构</span>
                        <span class="column-item info"><s:property value="#product.distributor_institution"/></span>
                    </div>
                </div>
            </div>
        </s:if>
        <s:elseif test="#product.productType=='fund'">
            <div class="product fund" data-toggle="modal" data-target="#mymModal">
                <div class="fund-circle circle">
                    <h1><s:property value="#product.year_rate"/></h1>
                    <h4>收益率</h4>
                    <hr>
                    <h3>基金</h3>
                </div>
                <div class="more-info">
                    <h3><s:property value="#product.name"/></h3>
                    <br>
                    <div class="column">
                        <span class="column-item u1of3 tag">产品状态</span>
                        <span class="column-item info"><s:property value="#product.state"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">最新净值</span>
                        <span class="column-item info">¥<s:property value="#product.net_value"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">产品编号</span>
                        <span class="column-item info"><s:property value="#product.pid"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">基金类型</span>
                        <span class="column-item info"><s:property value="#product.type"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">管理费率</span>
                        <span class="column-item info"><s:property value="#product.mng_charge_rate"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">成立日</span>
                        <span class="column-item info"><s:property value="#product.est_date"/></span>
                    </div>
                </div>
            </div>
        </s:elseif>
        <s:elseif test="#product.productType=='insurance'">
            <div class="product insurance" data-toggle="modal" data-target="#mybModal">
                <div class="insurance-circle circle">
                    <h1><s:property value="#product.year_rate"/></h1>
                    <h4>收益率</h4>
                    <hr>
                    <h3>保险</h3>
                </div>
                <div class="more-info">
                    <h3><s:property value="#product.name"/></h3>
                    <br>
                    <div class="column">
                        <span class="column-item u1of3 tag">保障年限</span>
                        <span class="column-item info"><s:property value="#product.insurance_age"/>年</span>
                    </div>
                    <%--<div class="column">--%>
                        <%--<span class="column-item u1of3 tag">保障年龄</span>--%>
                        <%--<span class="column-item info"><s:property value="#product.insurance_age"/>岁</span>--%>
                    <%--</div>--%>
                    <div class="column">
                        <span class="column-item u1of3 tag">保额区间</span>
                        <span class="column-item info">¥<s:property value="#product.amount_in_force[0]"/>~¥<s:property value="#product.amount_in_force[1]"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">缴费方式</span>
                        <span class="column-item info"><s:property value="#product.way_of_charge"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">发行机构</span>
                        <span class="column-item info"><s:property value="#product.distributor"/></span>
                    </div>
                </div>
            </div>
        </s:elseif>
        <s:elseif test="#product.productType=='bond'">
            <div class="product bond" data-toggle="modal" data-target="#myjModal">
                <div class="bond-circle circle">
                    <h1><s:property value="#product.year_rate"/></h1>
                    <h4>收益率</h4>
                    <hr>
                    <h3>债券</h3>
                </div>
                <div class="more-info">
                    <h3><s:property value="#product.name"/></h3>
                    <br>
                    <div class="column">
                        <span class="column-item u1of3 tag">票面利率</span>
                        <span class="column-item info"><s:property value="#product.nominal_interest_rate"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">期限</span>
                        <span class="column-item info"><s:property value="#product.life"/>年</span>
                    </div>
                    <%--<div class="column">--%>
                        <%--<span class="column-item u1of3 tag">到期日</span>--%>
                        <%--<span class="column-item info"><s:property value="#product."/></span>--%>
                    <%--</div>--%>
                    <div class="column">
                        <span class="column-item u1of3 tag">债券类型</span>
                        <span class="column-item info"><s:property value="#product.type"/></span>
                    </div>
                    <div class="column">
                        <span class="column-item u1of3 tag">债券代码</span>
                        <span class="column-item info"><s:property value="#product.code"/></span>
                    </div>
                </div>
            </div>
        </s:elseif>
    </s:iterator>

    <s:iterator var="counter" begin="size" end="8">
        <div class="product nothing"></div>
    </s:iterator>

</div>
</s:form>
<div class="paging-wrapper">
    <hr class="fix">
    <span id="first_page" class="paging-item"><i class="fa fa-long-arrow-left"></i> 第一页</span>
    <span id="pre_page" class="paging-item"><i class="fa fa-long-arrow-left"></i></span>
    <ul class="paging-item page-number">
        <s:iterator var="counter" begin="(#request.currentPage-3)>1?(#request.currentPage-3):1" end="#request.pageLength">
            <s:if test="top<=(#request.currentPage+2)||top>(#request.pageLength-2)">
                <s:if test="top==#request.currentPage">
                    <li class="page-num current"><s:property value="top"/></li>
                </s:if>
                <s:if test="top!=#request.currentPage">
                    <li class="page-num"><s:property value="top"/></li>
                </s:if>
            </s:if>
            <s:if test="top==(#request.currentPage+3)&&top<=(#request.pageLength-2)">
                <li>...</li>
            </s:if>
        </s:iterator>
    </ul>
    <span id="next_page" class="paging-item"><i class="fa fa-long-arrow-right"></i></span>
    <span id="last_page" class="paging-item">最后一页 <i class="fa fa-long-arrow-right"></i></span>
</div>
<jsp:include page='bankModal.jsp'/>
<jsp:include page='bondModal.jsp'/>
<jsp:include page='fundModal.jsp'/>
<jsp:include page='insuranceModal.jsp'/>
