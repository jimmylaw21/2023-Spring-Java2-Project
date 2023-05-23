/****** PLACE YOUR CUSTOM STYLES HERE ******/

// 基于准备好的dom，初始化echarts实例
//var myChart4 = echarts.init(document.getElementById('main4'));

getNowFormatDate();



function init_myChart3(data) {

	var uploadCnt = [];
	var updateCnt = [];

	var viewCnt = [];
	if (data.uploadData != null) {
		for (var i = 0; i < data.uploadData.length; i++) {
			uploadCnt.push(data.uploadData[i].count);
		}
	}
	if (data.updateData != null) {
		for (var i = 0; i < data.updateData.length; i++) {
			updateCnt.push(data.updateData[i].count);
		}
	}

	if (data.viewData != null) {
		for (var i = 0; i < data.viewData.length; i++) {
			viewCnt.push(data.viewData[i].count);
		}
	}
	option = {

		tooltip: {
			trigger: 'axis',
			formatter: function (params, ticket, callback) {
				var res = '';
				for (var i = 0, l = params.length; i < l; i++) {
					res += '' + params[i].seriesName + ' : ' + params[i].value + '<br>';
				}
				return res;
			},
			transitionDuration: 0,
			backgroundColor: 'rgba(83,93,105,0.8)',
			borderColor: '#535b69',
			borderRadius: 8,
			borderWidth: 2,
			padding: [5, 10],
			axisPointer: {
				type: 'line',
				lineStyle: {
					type: 'dashed',
					color: '#ffff00'
				}
			}
		},
		legend: {
			icon: 'circle',
			itemWidth: 8,
			itemHeight: 8,
			itemGap: 10,
			top: '16',
			right: '10',
			data: ['数据总量','共享次数','更新量'],
			textStyle: {
				fontSize: 10,
				color: '#a0a8b9'
			}
		},
		grid: {
			top: '46',
			left: '13%',
			right: '10',
			//bottom: '10%',
			containLabel: false
		},
		xAxis: [{
			type: 'category',
			boundaryGap: false,
			axisLabel: {
				interval: 0,
				fontSize:10
			},
			axisLine: {
				show: false,
				lineStyle: {
					color: '#a0a8b9'
				}
			},
			axisTick: {
				show: false
			},
			data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月'],
			offset: 10
		}],
		yAxis: [{
			type: 'value',
			axisLine: {
				show: false,
				lineStyle: {
					color: '#a0a8b9'
				}
			},
			axisLabel: {
				margin: 10,
				textStyle: {
					fontSize: 10
				}
			},
			splitLine: {
				lineStyle: {
					color: '#2b3646'
				}
			},
			axisTick: {
				show: false
			}
		}],
		series: [{
			name: '数据总量',
			type: 'line',
			smooth: true,
			showSymbol: false,
			lineStyle: {
				normal: {
					width: 2
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(137, 189, 27, 0.3)'
					}, {
						offset: 0.8,
						color: 'rgba(137, 189, 27, 0)'
					}], false),
					shadowColor: 'rgba(0, 0, 0, 0.1)',
					shadowBlur: 10
				}
			},
			itemStyle: {
				normal: {
					color: '#1cc840'
				}
			},
			data: uploadCnt
		}, {
			name: '共享次数',
			type: 'line',
			smooth: true,
			showSymbol: false,
			lineStyle: {
				normal: {
					width: 2
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(219, 50, 51, 0.3)'
					}, {
						offset: 0.8,
						color: 'rgba(219, 50, 51, 0)'
					}], false),
					shadowColor: 'rgba(0, 0, 0, 0.1)',
					shadowBlur: 10
				}
			},
			itemStyle: {
				normal: {
					color: '#eb5690'
				}
			},
			data: viewCnt
		},  {
			name: '更新量',
			type: 'line',
			smooth: true,
			showSymbol: false,
			lineStyle: {
				normal: {
					width: 2
				}
			},
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: 'rgba(0, 136, 212, 0.3)'
					}, {
						offset: 0.8,
						color: 'rgba(0, 136, 212, 0)'
					}], false),
					shadowColor: 'rgba(0, 0, 0, 0.1)',
					shadowBlur: 10
				}
			},
			itemStyle: {
				normal: {
					color: '#43bbfb'
				}
			},
			data: updateCnt
		}
		]
	};
	myChart3.setOption(option);
}


//获取当前时间
function getNowFormatDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var Hour =  date.getHours();       // 获取当前小时数(0-23)
    var Minute =  date.getMinutes();     // 获取当前分钟数(0-59)
    var Second = date.getSeconds();     // 获取当前秒数(0-59)
    var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
    var day=date.getDay();
    if (Hour<10) {
        Hour = "0" + Hour;
    }
    if (Minute <10) {
        Minute = "0" + Minute;
    }
    if (Second <10) {
        Second = "0" + Second;
    }
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = '<div><p>'+year + '年' + month +'月' + strDate+'号</p><p>'+show_day[day]+'</p></div>';
    var HMS = Hour + ':' + Minute +':' + Second;
	var temp_time = year+'-'+month+'-'+strDate+' '+HMS;
    $('.nowTime li:nth-child(1)').html(HMS);
    $('.nowTime li:nth-child(2)').html(currentdate);
	//$('.topRec_List li div:nth-child(3)').html(temp_time);
    setTimeout(getNowFormatDate,1000);//每隔1秒重新调用一次该函数
}

/*function resize(){
	window.addEventListener("resize", () => { 
  	this.myChart1.resize;
	this.myChart2.resize;
	this.myChart3.resize;
	this.myChart5.resize;
	this.myChart6.resize;
	this.myChart7.resize;
});
}*/

setInterval(function (){
    window.onresize = function () {
		this.myChart1.resize;
		this.myChart2.resize;
		this.myChart3.resize;
		this.myChart5.resize;
		this.myChart6.resize;
		this.myChart7.resize;
    }
},200)

//myChart7.resize();
