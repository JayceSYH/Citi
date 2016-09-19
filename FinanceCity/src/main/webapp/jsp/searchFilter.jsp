<%--
  Created by IntelliJ IDEA.
  User: Hermit
  Date: 16/8/24
  Time: 08:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
    request.setAttribute("basePath", basePath);
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link type="text/css" rel="stylesheet" href="${basePath}plugins/bootstrap-3.3.5-dist/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}plugins/bootstrap-toggle-master/css/bootstrap-toggle.min.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}plugins/ion.rangeSlider-master/css/ion.rangeSlider.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}plugins/ion.rangeSlider-master/css/ion.rangeSlider.skinFlat.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}/plugins/font-awesome-4.6.3/css/font-awesome.min.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}css/buttons.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}css/order.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}css/mycss.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}css/common.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}css/searchResult.css"/>
    <link type="text/css" rel="stylesheet" href="${basePath}css/searchFilter.css"/>

    <script>
        var search_list = "<s:property value="#request.bankInstitutionList"/>";
        console.log(search_list);
    </script>

    <script type="text/javascript" rel="script" src="js/jquery.min.js"></script>
    <script type="text/javascript" rel="script" src="plugins/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" rel="script" src="plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" rel="script" src="plugins/ion.rangeSlider-master/js/ion.rangeSlider.min.js"></script>
    <script type="text/javascript" rel="script" src="plugins/bootstrap-toggle-master/js/bootstrap-toggle.min.js"></script>
    <script type="text/javascript" rel="script" src="js/searchFilter-all.js"></script>
 <script type="text/javascript" rel="script" src="js/Chart-1.0.1-beta.4.js"></script>
    <title>InvestGO</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="main-content">
    <div class="filter-container">
        <form>
            <div class="input-add-on search-wrapper">
                <h1 class="input-add-on-item">关键字</h1>
                <input id="all_keywords" type="text" class="input-add-on-field search-input"/>
                <button class="button button-3d button-primary button-rounded input-add-on-item search-button" type="button">搜 索</button>
            </div>
            <div class="input-add-on filter-wrapper">
                <h1 class="input-add-on-item">筛&nbsp;&nbsp;&nbsp;选</h1>
                <div class="input-add-on-field input-add-on selections">
                    <select id="all_type" class="input-add-on-field left type-selector">
                        <option value="all">All</option>
                        <option value="bank">银行理财</option>
                        <option value="bond">债券</option>
                        <option value="fund">基金</option>
                        <option value="insurance">保险</option>
                    </select>
                    <select class="input-add-on-field right">
                        <option class="display-none">——————</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <button id="all_filter_button" class="button button-3d button-primary button-rounded input-add-on-item filter-button" type="button">筛 选</button>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">年化收益率</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="all_yearly_income_rate" name="annualized-return" type="text" id="all_annualized_return" name="annualized_return" value="" class="input-add-on-field annualized-return annualized-return"/>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">期限</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="all_expiration" name="range" type="text" id="all_range" name="range" value="" class="input-add-on-field range"/>
                    </div>
                </div>
                <div class="input-add-on income-item u1of4">
                    <span class="tag">是否封闭</span>
                    &nbsp;&nbsp;
                    <div class="toggle-wrapper">
                        <input id="all_is_close_ended" name="open" type="checkbox" checked data-toggle="toggle" data-size="small" data-height="30px" data-on="是" data-off="否" data-onstyle="info">
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="filter-container">
        <form>
            <div class="input-add-on search-wrapper">
                <h1 class="input-add-on-item">关键字</h1>
                <input id="bank_keywords" type="text" class="input-add-on-field search-input"/>
                <button class="button button-3d button-primary button-rounded input-add-on-item search-button" type="button">搜 索</button>
            </div>
            <div class="input-add-on filter-wrapper">
                <h1 class="input-add-on-item">筛&nbsp;&nbsp;&nbsp;选</h1>
                <div class="input-add-on-field input-add-on selections">
                    <select id="bank_type" class="input-add-on-field left type-selector">
                        <option value="all">All</option>
                        <option value="bank">银行理财</option>
                        <option value="bond">债券</option>
                        <option value="fund">基金</option>
                        <option value="insurance">保险</option>
                    </select>
                    <select class="input-add-on-field right">
                        <option class="display-none">——————</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <button id="bank_filter_button" class="button button-3d button-primary button-rounded input-add-on-item filter-button" type="button">筛 选</button>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">预计年利率</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="bank_yearly_income_rate" type="text" id="annualized_return" name="annualized_return" value="" class="input-add-on-field annualized-return"/>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">起购金额</span>
                    <div class="slider-wrapper input-add-on-field">
                        <div class="range-wrapper">
                            <input id="bank_initial_amount_start" type="text" class="range-input" value="0"/>
                            <label>—</label>
                            <input id="bank_initial_amount_end" type="text" class="range-input"/>
                        </div>
                        <%--<input id="bank_initial_amount" type="text" id="purchase_amount" name="purchase_amount" value="" class="input-add-on-field"/>--%>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">期限</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="bank_expiration" type="text" id="range" name="range" value="" class="input-add-on-field range"/>
                    </div>
                </div>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">管理机构&nbsp;&nbsp;</span>
                    <select id="bank_institution_manage" class="input-add-on-field">
                        <option value="">所有机构</option>
                        <s:iterator id="bank" value="#request.bankInstitutionList">
                            <option><s:property value="#bank"/></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">收益类型</span>
                    <select id="bank_income_type" class="input-add-on-field">
                        <s:iterator id="bank" value="#request.bankYieldList" status="tl">
                            <option value="<s:property value="#tl.index"/>"><s:property value="#bank"/></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">是否封闭</span>
                    &nbsp;&nbsp;
                    <input id="bank_is_close_ended" type="checkbox" checked data-toggle="toggle" data-size="small" data-height="30px" data-on="是" data-off="否" data-onstyle="info">
                </div>
            </div>
        </form>
    </div>
    <div class="filter-container">
        <form>
            <div class="input-add-on search-wrapper">
                <h1 class="input-add-on-item">关键字</h1>
                <input id="bond_keywords" type="text" class="input-add-on-field search-input"/>
                <button class="button button-3d button-primary button-rounded input-add-on-item search-button" type="button">搜 索</button>
            </div>
            <div class="input-add-on filter-wrapper">
                <h1 class="input-add-on-item">筛&nbsp;&nbsp;&nbsp;选</h1>
                <div class="input-add-on-field input-add-on selections">
                    <select id="bond_type" class="input-add-on-field left type-selector">
                        <option value="all">All</option>
                        <option value="bank">银行理财</option>
                        <option value="bond">债券</option>
                        <option value="fund">基金</option>
                        <option value="insurance">保险</option>
                    </select>
                    <select class="input-add-on-field right">
                        <option class="display-none">——————</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <button id="bond_filter_button" class="button button-3d button-primary button-rounded input-add-on-item filter-button" type="button">筛 选</button>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">预计年利率</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="bond_yearly_income_rate" type="text" id="bond_annualized_return" name="annualized_return" value="" class="input-add-on-field annualized-return"/>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">期限</span>
                    &nbsp;&nbsp;
                    &nbsp;&nbsp;
                    <div class="slider-wrapper input-add-on-field">
                        <input id="bond_expiration" type="text" id="bond_range" name="range" value="" class="input-add-on-field range"/>
                    </div>
                </div>
                <div class="income-item u1of4">

                </div>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">到期日</span>
                    &nbsp;&nbsp;
                    &nbsp;&nbsp;
                    <input type="text" id="bond_expiration_date" class="input-add-on-field form_datetime" readonly/>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">状态</span>
                    &nbsp;&nbsp;
                    &nbsp;&nbsp;
                    <select id="bond_state" class="input-add-on-field">
                        <s:iterator var="counter" id="state" value="#request.bondStateList" status="tl">
                            <option value="<s:property value="#tl.index"/>"><s:property value="#state"/></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="input-add-on income-item u1of4">

                </div>
            </div>
        </form>
    </div>
    <div class="filter-container">
        <form>
            <div class="input-add-on search-wrapper">
                <h1 class="input-add-on-item">关键字</h1>
                <input id="fund_keywords" type="text" class="input-add-on-field search-input"/>
                <button class="button button-3d button-primary button-rounded input-add-on-item search-button" type="button">搜 索</button>
            </div>
            <div class="input-add-on filter-wrapper">
                <h1 class="input-add-on-item">筛&nbsp;&nbsp;&nbsp;选</h1>
                <div class="input-add-on-field input-add-on selections">
                    <select id="fundtype" class="input-add-on-field left type-selector">
                        <option value="all">All</option>
                        <option value="bank">银行理财</option>
                        <option value="bond">债券</option>
                        <option value="fund">基金</option>
                        <option value="insurance">保险</option>
                    </select>
                    <select class="input-add-on-field right">
                        <option class="display-none">——————</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <button id="fund_filter_button" class="button button-3d button-primary button-rounded input-add-on-item filter-button" type="button">筛 选</button>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">管理机构</span>
                    <select id="fund_institution_manage" class="input-add-on-field">
                        <option value="">所有机构</option>
                        <s:iterator id="fund" value="#request.fundInstitutionList">
                            <option><s:property value="#fund"/></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">目标类型</span>
                    <select id="fund_type" class="input-add-on-field">
                        <s:iterator id="target" value="#request.fundTargetList" status="tl">
                            <option value="<s:property value="#tl.index"/>"><s:property value="#target"/></option>
                        </s:iterator>
                    </select>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">状态</span>
                    <select id="fund_state" class="input-add-on-field">
                        <s:iterator id="state" value="#request.fundStateList" status="tl">
                            <option value="<s:property value="#tl.index"/>"><s:property value="#state"/></option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item u1of4">
                    <span class="tag">最新净值</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="fund_net_value" type="text" name="annualized_return" value="" class="input-add-on-field annualized-return"/>
                    </div>
                </div>
                <div class="input-add-on income-item u1of4">
                    <span class="tag">期限</span>
                    &nbsp;&nbsp;
                    <div class="slider-wrapper input-add-on-field">
                        <input id="fund_expiration" type="text" name="range" value="" class="input-add-on-field range"/>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">是否封闭</span>
                    &nbsp;&nbsp;
                    <div class="toggle-wrapper">
                        <input id="fund_is_close_ended" type="checkbox" checked data-toggle="toggle" data-size="small" data-height="30px" data-on="是" data-off="否" data-onstyle="info">
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">按收益排序</span>
                    &nbsp;&nbsp;
                    <div class="toggle-wrapper">
                        <input id="fund_sort_open" type="checkbox" checked data-toggle="toggle" data-size="small" data-height="30px" data-on="是" data-off="否" data-onstyle="info">
                    </div>
                </div>
                <div class="input-add-on income-item u1of20">
                    <span id="fund_sort_type" class="tag">升序<i class="fa fa-long-arrow-up"></i></span>
                </div>
            </div>
        </form>
    </div>
    <div class="filter-container">
        <form>
            <div class="input-add-on search-wrapper">
                <h1 class="input-add-on-item">关键字</h1>
                <input id="insurance_keywords" type="text" class="input-add-on-field search-input"/>
                <button class="button button-3d button-primary button-rounded input-add-on-item search-button" type="button">搜 索</button>
            </div>
            <div class="input-add-on filter-wrapper">
                <h1 class="input-add-on-item">筛&nbsp;&nbsp;&nbsp;选</h1>
                <div class="input-add-on-field input-add-on selections">
                    <select id="insurance_type" class="input-add-on-field left type-selector">
                        <option value="all">All</option>
                        <option value="bank">银行理财</option>
                        <option value="bond">债券</option>
                        <option value="fund">基金</option>
                        <option value="insurance">保险</option>
                    </select>
                    <select class="input-add-on-field right">
                        <option class="display-none">——————</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                    </select>
                </div>
                <button id="insurance_filter_button" class="button button-3d button-primary button-rounded input-add-on-item filter-button" type="button">筛 选</button>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">保障年限</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="insurance_length_of_years" type="text" id="insurance_annualized_return" name="annualized_return" value="" class="input-add-on-field"/>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">预期利率</span>
                    &nbsp;&nbsp;
                    <div class="slider-wrapper input-add-on-field">
                        <input id="insurance_income_rate" type="text" id="insurance_range" name="range" value="" class="input-add-on-field annualized-return"/>
                    </div>
                </div>
                <div class="income-item u1of4">

                </div>
            </div>
            <div class="income-wrapper">
                <div class="input-add-on income-item">
                    <span class="tag">发行公司</span>
                    <div class="toggle-wrapper input-add-on-field">
                        <select id="insurance_distributor" class="input-add-on-field">
                            <option value="">所有机构</option>
                            <s:iterator id="insurance" value="#request.insuranceInstitutionList">
                                <option><s:property value="#insurance"/></option>
                            </s:iterator>
                        </select>
                    </div>
                </div>
                <div class="input-add-on income-item">
                    <span class="tag">保险产品面额</span>
                    <div class="slider-wrapper input-add-on-field">
                        <input id="insurance_price" class="input-add-on-field" type="text" name="insurance-price" value=""/>
                    </div>
                </div>

                <div class="income-item u1of4">

                </div>
            </div>
        </form>
    </div>

    <div class="result-container">

    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
