
window.onload = function() {
    initChart("chartdiv1");
    initChart("chartdiv2");
    initChart("chartdiv3");
}

function initChart(chartId) {
    var chartData = generatechartData(chartId);

    var chart = AmCharts.makeChart(chartId, {
        "theme": "light",
        "type": "serial",
        "marginRight": 10,
        "autoMarginOffset": 10,
        "marginTop":0,
        "dataProvider": chartData,
        "valueAxes": [{
            "id": "v1",
            "axisAlpha": 0.1
        }],
        "graphs": [{
            "useNegativeColorIfDown": true,
            "balloonText": "[[category]]<br><b>value: [[value]]</b>",
            "bullet": "round",
            "bulletBorderAlpha": 1,
            "bulletBorderColor": "#FFFFFF",
            "hideBulletsCount": 50,
            "lineThickness": 1,
            "lineColor": "#fdd400",
            "negativeLineColor": "#67b7dc",
            "valueField": "visits"
        }],
        "chartScrollbar": {
            "scrollbarHeight": 5,
            "backgroundAlpha": 0.1,
            "backgroundColor": "#868686",
            "selectedBackgroundColor": "#67b7dc",
            "selectedBackgroundAlpha": 1
        },
        "chartCursor": {
            "valueLineEnabled": false,
            "valueLineBalloonEnabled": false
        },
        "categoryField": "date",
        "categoryAxis": {
            "parseDates": true,
            "axisAlpha": 0,
            "minHorizontalGap": 60
        },
        "export": {
            "enabled": false
        }
    });

    chart.addListener("dataUpdated", zoomChart);
}

//zoomChart();
function zoomChart() {
    if (chart.zoomToIndexes) {
        chart.zoomToIndexes(130, chartData.length - 1);
    }
}

function generatechartData(chartID) {
    var chartData = [];
    var firstDate = new Date();
    firstDate.setDate(firstDate.getDate() - 150);

    for (var i = 0; i < 150; i++) {
        // we create date objects here. In your data, you can have date strings
        // and then set format of your dates using chart.dataDateFormat property,
        // however when possible, use date objects, as this will speed up chart rendering.
        var newDate = new Date(firstDate);
        newDate.setDate(newDate.getDate() + i);

        var visits = Math.round(Math.random() * 100 - 50);

        chartData.push({
            date: newDate,
            visits: visits
        });
    }
    return chartData;
}