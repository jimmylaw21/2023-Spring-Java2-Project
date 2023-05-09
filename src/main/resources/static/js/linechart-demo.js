function initLineChart(container, dataMap, seriesName) {
    let lineChart = echarts.init(container);

    // Extract keys (x-axis labels) and values (data points) from the data map
    let xAxisLabels = Object.keys(dataMap).map(key => parseInt(key));
    let lineChartData = Object.values(dataMap);

    let lineOption = {
        title: {
            text: 'Line Chart Example',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: xAxisLabels,
            boundaryGap: false
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: seriesName,
            type: 'line',
            smooth: true,
            data: lineChartData
        }]
    };
    lineChart.setOption(lineOption);
    return lineChart;
}
