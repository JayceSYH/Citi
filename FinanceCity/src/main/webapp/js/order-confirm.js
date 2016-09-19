/**
 * Created by Hermit on 16/9/5.
 */

window.onload = function() {
    //inside_tr_list = document.querySelectorAll(".inside-container table tr");
    pre_current = $("tr.current");
    delete_list = document.querySelectorAll("button.delete");
    size_list = document.querySelectorAll("input.size");

    initInsideListener();
    initDeleteListener();
    initSizeListener();
    initButtonListener();
};

//var inside_tr_list;
var pre_current;
function initInsideListener() {
    $(".inside-container table tr").on("click", function() {
        $(pre_current).removeClass("current");
        pre_current = this;
        $(this).addClass("current");
    });
}

var delete_list;
function initDeleteListener() {
    $("button.delete").on("click", function() {
        var index = -1;
        for(var i=0;i<delete_list.length;i++){
            if(this==delete_list[i]){
                index = i;
                break;
            }
        }
        console.log(index);
    });
}

var size_list;
function initSizeListener() {
    $("input.size").bind("blur", function() {
        var index = -1;
        for(var i=0;i<size_list.length;i++){
            if(this==size_list[i]){
                index = i;
                break;
            }
        }
        console.log(index);
    });
}

function initButtonListener() {
    $("#order_confirm").on("click", function() {
        console.log("confirm");
    });
}