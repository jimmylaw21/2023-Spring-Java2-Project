function fetchDataAndInitCharts() {

    $.get('/api/distribution-of-number-of-answers', function(data) {
        let lineChartContainer = document.getElementById('linechart');
        let lineChartMapData = data;
        let seriesName = 'Example Data';

        initLineChart(lineChartContainer, lineChartMapData, seriesName);
    })
        .fail(function(error) {
            console.error('Error fetching data for wordcloud:', error);
        });

    $.get('/api/distribution-of-question-resolution-time', function(data) {
        let lineChartContainer = document.getElementById('linechart');
        let lineChartMapData = data;
        let seriesName = 'Example Data';

        initLineChart(lineChartContainer, lineChartMapData, seriesName);
    })
        .fail(function(error) {
            console.error('Error fetching data for wordcloud:', error);
        });

    $.get('/api/frequent-tags', function(data) {
        let piechartContainer = document.getElementById('piechart');
        initPiechart(piechartContainer, data);
    })
        .fail(function(error) {
            console.error('Error fetching data for piechart:', error);
        });

    $.get('/api/most-upvoted-tags', function(data) {
        let piechartContainer = document.getElementById('piechart');
        initPiechart(piechartContainer, data);
    })
        .fail(function(error) {
            console.error('Error fetching data for piechart:', error);
        });

    $.get('/api/most-viewed-tags', function(data) {
        let piechartContainer = document.getElementById('piechart');
        initPiechart(piechartContainer, data);
    })
        .fail(function(error) {
            console.error('Error fetching data for piechart:', error);
        });

    $.get('/api/most-active-users', function(data) {
        let lineChartContainer = document.getElementById('linechart');
        let lineChartMapData = data;
        let seriesName = 'Example Data';

        initLineChart(lineChartContainer, lineChartMapData, seriesName);
    })
        .fail(function(error) {
            console.error('Error fetching data for wordcloud:', error);
        });

    $.get('/api/most-used-JavaApi', function(data) {
        let wordcloudContainer = document.getElementById('wordcloud');
        initWordcloud(wordcloudContainer, data);
    })
        .fail(function(error) {
            console.error('Error fetching data for wordcloud:', error);
        });

    $.get('/api/most-used-JavaApi', function(data) {
        let piechartContainer = document.getElementById('piechart');
        initPiechart(piechartContainer, data);
    })
        .fail(function(error) {
            console.error('Error fetching data for piechart:', error);
        });
    $.get('/api/most-used-JavaApi', function(data) {
        let lineChartContainer = document.getElementById('linechart');
        let lineChartMapData = data;
        let seriesName = 'Example Data';

        initLineChart(lineChartContainer, lineChartMapData, seriesName);
    })
        .fail(function(error) {
            console.error('Error fetching data for line chart:', error);
        });
}

