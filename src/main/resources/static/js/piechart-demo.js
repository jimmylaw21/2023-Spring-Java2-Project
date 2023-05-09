let piechartRaw
    = {"1": 44, "0": 39, "5": 1, "3": 3, "2": 8, "4": 2, "6": 2, "9": 1}

function initPiechart(container, data) {
    let piechart = echarts.init(container);
    let piechartData = [];
    for (const key in data) {
        piechartData.push({
            name: key,
            value: data[key]
        });
    }
    let pieOption = {
        title: {
                    text: 'Problem Count With Corresponding Answer Count',
                    textStyle: {
                        fontStyle: 'oblique',
                        fontSize: 20,
                        color: '#4cc9f0'
                    },
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                grid: {
                    width: '100%',
                    height: '100%'
                },
                legend: {
                    orient: 'vertical',
                    textStyle: {
                        fontSize: 20,
                        color: 'black'
                    },
                    right: 'center',
                    bottom: 'bottom',
                },
                series: [
                    {
                        name: 'Problem Count',
                        type: 'pie',
                        radius: '80%',
                        data: piechartData,
                        color: ['#9400D3', '#FF00FF', '#4169E1', '#3CB371',
                            '#FFA500', '#FF7F50', '#DC143C', '#000080'],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0.1, 0.2, 0, 0.5)',
                            }
                        },
                        itemStyle: {
                            normal: {
                                label: {
                                    textStyle: {
                                        fontSize: 12,
                                        color: 'black'
                                    }
                                }
                            }
                        }
                    }
                ]
    };
    piechart.setOption(pieOption);
    return piechart;
}


