let piechartRaw = {"1": 44, "0": 39, "5": 1, "3": 3, "2": 8, "4": 2, "6": 2, "9": 1}

// 创建 ECharts 图表实例
const frequentTagsChart = echarts.init(document.getElementById('frequent-tags-chart'));
const mostUpvotedTagsChart = echarts.init(document.getElementById('most-upvoted-tags-chart'));

// 请求频繁出现的标签数据
$.get('/api/frequent-tags', function(data) {
  // 处理数据并设置 ECharts 图表的配置项
  const frequentTagsOption = {
    // ... 配置项，例如标题、图例等 ...
    series: [{
      // ... 系列配置，例如类型、数据等 ...
      data: Object.entries(data).map(([name, value]) => ({ name, value })),
    }],
  };

  // 设置图表配置并渲染
  frequentTagsChart.setOption(frequentTagsOption);
});

// 请求最高投票标签数据
$.get('/api/most-upvoted-tags', function(data) {
  // 处理数据并设置 ECharts 图表的配置项
  const mostUpvotedTagsOption = {
    // ... 配置项，例如标题、图例等 ...
    series: [{
      // ... 系列配置，例如类型、数据等 ...
      data: Object.entries(data).map(([name, value]) => ({ name, value })),
    }],
  };

  // 设置图表配置并渲染
  mostUpvotedTagsChart.setOption(mostUpvotedTagsOption);
});
