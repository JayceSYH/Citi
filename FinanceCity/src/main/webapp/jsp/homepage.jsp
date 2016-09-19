<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Invest Go</title>

    <link href="css/common.css" rel="stylesheet">
    <link href="css/homepage.css" rel="stylesheet">

</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div id="homepage_bg">
        <div id="slogan_wrapper">
            <img src="img/homepage_title.png">
            <h1>一站式智能投顾平台</h1>
        </div>
        <Button id="startVoyage">开启智能投顾之旅</Button>
    </div>

    <div id="aim_outer">
        <div id="aim_wrapper">
            <h2>Our aims</h2>
            <hr class="title_line">
            <div id="aim_1" class="aim">
                <img src="img/icon1.png">
                <div class="aim_description">
                    <p>为大众提供充分的投资信息</p>
                    <p>以及合理的资产配置方案</p>
                </div>
                <img class="icon_next" src="img/next.png">
            </div>

            <div id="aim_2" class="aim">
                <img src="img/icon2.png">
                <div class="aim_description">
                    <p>降低普通投资者</p>
                    <p>进行财富管理的成本</p>
                </div>
                <img class="icon_next" src="img/next.png">
            </div>

            <div id="aim_3" class="aim">
                <img src="img/icon3.png">
                <div class="aim_description">
                    <p>逐步实现金融市场</p>
                    <p>资源配置的有效性</p>
                </div>
                <img src="img/final.png">
            </div>
        </div>
    </div>

    <div id="content_outer">
        <div id="content_wrapper">
            <h2>What we do</h2>
            <hr class="title_line">
            <div class="content_items">
                <div class="content_item content_first">
                    <img src="img/contentImg_1.png">
                    <p>满足资金流动性需求</p>
                </div>
                <div class="content_item content_first">
                    <img src="img/contentImg_2.png">
                    <p>针对投资与风险偏好</p>
                </div>
                <div class="content_item content_first">
                    <img src="img/contentImg_3.png">
                    <p>提供多种投资组合</p>
                </div>
                <div class="content_item content_second">
                    <img src="img/contentImg_4.png">
                    <p>一键打包购买</p>
                </div>
                <div class="content_item content_second">
                    <img src="img/contentImg_5.png">
                    <p>实时资产监控</p>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
