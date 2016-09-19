/**
 * Created by Hermit on 16/8/30.
 */
window.onload = function() {
    type_div_list = document.querySelectorAll(".filter-container");
    type_selector_list = document.querySelectorAll(".type-selector");
    filter_button_list = document.querySelectorAll(".filter-button");
    search_button_list = document.querySelectorAll(".search-button");

    initSlider();
    initSort();
    initTypeSelect();
    initFilterButton();
    initDateTools();
};

function initSlider() {
    $(".range").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 72,
        from: 0,
        to: 20,
        prefix: "",
        postfix: "个月"
    });
    $(".annualized-return").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 15,
        step: 0.1,
        from: 0,
        to: 15,
        postfix: "%"
    });
    //$("#bank_initial_amount").ionRangeSlider({
    //    type: "double",
    //    grid: true,
    //    min: 0,
    //    max: 1000000000,
    //    from: 200,
    //    step: 1000,
    //    to: 800,
    //    prefix: "$"
    //});
    $("#insurance_length_of_years").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 100,
        from: 20,
        to: 80,
        postfix: "年"
    });
    $("#insurance_price").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 10,
        step: 0.5,
        from: 1,
        to: 8,
        postfix: "w"
    });
}

var isAscend = true;
function initSort() {
    $("#fund_sort_type").on("click", function() {
        isAscend = !isAscend;
        if(isAscend){
            $("#fund_sort_type").html("升序<i class='fa fa-long-arrow-up'></i>");
        }else{
            $("#fund_sort_type").html("降序<i class='fa fa-long-arrow-down'></i>");
        }
    });
}

var type_index = 0;
var type_list = ["all", "bank", "bond", "fund", "insurance"];
var type_div_list;
var type_selector_list;
function initTypeSelect() {
    var i;
    for(i=0;i<type_div_list.length;i++){
        if(i==type_index){
            $(type_div_list[i]).show();
        }else{
            $(type_div_list[i]).hide();
        }
    }
    $(".type-selector").on("change", function() {
        var type = $(this).val();
        $(".type-selector").val(type);
        var i;
        for(i=0;i<type_list.length;i++){
            if(type==type_list[i]){
                type_index = i;
                $(type_div_list[i]).show();
            }else{
                $(type_div_list[i]).hide();
            }
        }
    });
}

function initDateTools() {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        minView: "month"
    });

    $(".form_datetime").datetimepicker("setStartDate", getNowFormatDate());
}

var filter_button_list;
var search_button_list;
var f_list = [submitAll, submitBank, submitBond, submitFund, submitInsurance];
function initFilterButton() {
    $(".filter-button").on("click", function() {
        var index = 0;
        for(var i=0;i<filter_button_list.length;i++){
            if(this==filter_button_list[i]){
                index = i;
                break;
            }
        }

        f_list[index](1);
    });
    $(".search-button").on("click", function() {
        var index = 0;
        for(var i=0;i<search_button_list.length;i++){
            if(this==search_button_list[i]){
                index = i;
                break;
            }
        }

        f_list[index](1);
    })
}

function submitAll(page_num) {
    var data = {};
    data["keyword"] = $("#all_keywords").val();
    data["type"] = "all";

    var options = {};

    options["yearly_income_rate"] = $("#all_yearly_income_rate").val().split(";");
    options["expiration"] = $("#all_expiration").val().split(";");

    var is_close_ended = document.getElementById("all_is_close_ended").checked;
    if(is_close_ended){
        options["is_close_ended"] = 1;
    }else{
        options["is_close_ended"] = 0;
    }

    data["options"] = options;
    data["page_num"] = page_num+"";
    console.log("data="+JSON.stringify(data));

    $.ajax({
        type: "post",
        url: "jsp/searchFilter",
        data: "data="+JSON.stringify(data),
        cache: false,
        success: function(data) {
            //console.log(data);
            $(".result-container").empty();
            $(".result-container").append(data);

            initPageToggle();
        },
        error: function() {

        }
    });
}

function submitBank(page_num) {
    var data = {};
    data["keyword"] = $("#bank_keywords").val();
    data["type"] = "Bank";

    var options = {};

    options["yearly_income_rate"] = $("#bank_yearly_income_rate").val().split(";");
    options["expiration"] = $("#bank_expiration").val().split(";");

    var initial_amount_start = $("#bank_initial_amount_start").val();
    var initial_amount_end = $("#bank_initial_amount_end").val();

    if(initial_amount_start==null||initial_amount_start==""||initial_amount_end==null||initial_amount_end==""){
        alert("起购金额不能为空");
        return;
    }

    options["initial_amount"] = [""+$("#bank_initial_amount_start").val(), ""+$("#bank_initial_amount_end").val()];
    options["institution_manage"] = $("#bank_institution_manage").val();
    options["income_type"] = $("#bank_income_type").val();

    var is_close_ended = document.getElementById("bank_is_close_ended").checked;
    if(is_close_ended){
        options["is_close_ended"] = 1;
    }else{
        options["is_close_ended"] = 0;
    }

    data["options"] = options;
    data["page_num"] = page_num+"";
    console.log(data);

    $.ajax({
        type: "post",
        url: "searchFilter",
        data: "data="+JSON.stringify(data),
        cache: false,
        success: function(data) {
            console.log(data);
            $(".result-container").empty();
            $(".result-container").append(data);

            initPageToggle();
        },
        error: function() {

        }
    });
}

function submitBond(page_num) {
    var data = {};
    data["keyword"] = $("#bond_keywords").val();
    data["type"] = "Bond";

    var options = {};

    options["yearly_income_rate"] = $("#bond_yearly_income_rate").val().split(";");
    options["expiration"] = $("#bond_expiration").val().split(";");
    options["expiration_date"] = $("#bond_expiration_date").val();
    options["state"] = $("#bond_state").val();

    data["options"] = options;
    data["page_num"] = page_num+"";
    console.log(data);

    $.ajax({
        type: "post",
        url: "jsp/searchFilter",
        data: "data="+JSON.stringify(data),
        cache: false,
        success: function(data) {
            //console.log(data);
            $(".result-container").empty();
            $(".result-container").append(data);

            initPageToggle();
        },
        error: function() {

        }
    });
}

function submitFund(page_num) {
    var data = {};
    data["keyword"] = $("#fund_keywords").val();
    data["type"] = "Fund";

    var options = {};

    options["institution_manage"] = $("#fund_institution_manage").val();
    options["type"] = $("#fund_type").val();
    options["state"] = $("#fund_state").val();
    options["net_value"] = $("#fund_net_value").val().split(";");
    options["expiration"] = $("#fund_expiration").val().split(";");

    var is_close_ended = document.getElementById("fund_is_close_ended").checked;
    if(is_close_ended) {
        options["is_close_ended"] = 1;
    }else{
        options["is_close_ended"] = 0;
    }

    var sort_state = document.getElementById("fund_sort_open").checked;
    if(sort_state){
        if(isAscend){
            options["sort_type"] = 1+"";
        }else{
            options["sort_type"] = 2+"";
        }
    }else{
        options["sort_type"] = 0+"";
    }

    data["options"] = options;
    data["page_num"] = page_num+"";
    console.log(data);

    $.ajax({
        type: "post",
        url: "jsp/searchFilter",
        data: "data="+JSON.stringify(data),
        cache: false,
        success: function(data) {
            //console.log(data);
            $(".result-container").empty();
            $(".result-container").append(data);

            initPageToggle();
        },
        error: function() {

        }
    });
}

function submitInsurance(page_num) {
    var data = {};
    data["keyword"] = $("#insurance_keywords").val();
    data["type"] = "Insurance";

    var options = {};
    options["length_of_years"] = $("#insurance_length_of_years").val().split(";");
    options["income_rate"] = $("#insurance_income_rate").val().split(";");
    options["distributor"] = $("#insurance_distributor").val();
    options["price"] = $("#insurance_price").val().split(";");

    data["options"] = options;

    data["page_num"] = page_num+"";
    console.log(data);

    $.ajax({
        type: "post",
        url: "jsp/searchFilter",
        data: "data="+JSON.stringify(data),
        cache: false,
        success: function(data) {
            //console.log(data);
            $(".result-container").empty();
            $(".result-container").append(data);

            initPageToggle();
        },
        error: function() {

        }
    });
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

function initPageToggle() {
    $("#pre_page").on("click", function() {
        if(currentPage-1<1){
            return;
        }
        f_list[type_index](currentPage-1);
    });
    $("#next_page").on("click", function() {
        if(currentPage+1>pageLength){
            return;
        }
        f_list[type_index](currentPage+1);
    });
    $("#first_page").on("click", function() {
        if(currentPage==1){
            return;
        }
        f_list[type_index](1);
    });
    $("#last_page").on("click", function() {
        if(currentPage==pageLength){
            return;
        }
        f_list[type_index](pageLength);
    });
    $(".page-num").on("click", function() {
        var num = $(this).html();
        if(num==currentPage){
            return;
        }
        f_list[type_index](num);
    })
}