let wordcloudRaw
    = {"rust-cargo": 1822, "borrow-checker": 1597, "lifetime": 1593, "traits": 1541, "generics": 979, "rust-tokio": 940, "iterator": 804, "serde": 775, "multithreading": 606, "string": 552, "closures": 528, "reference": 508, "macros": 506, "ffi": 500, "struct": 495, "vector": 480, "ownership": 452, "actix-web": 430, "enums": 428, "arrays": 405, "types": 381, "asynchronous": 350, "rust-diesel": 350, "webassembly": 337, "async-await": 332, "json": 303, "windows": 302, "c": 290, "rust-macros": 288}

function initWordcloud(container, data) {
    let wordcloud = echarts.init(container);
    let wordcloudData = [];
    for (const key in data) {
        wordcloudData.push({
            name: key,
            value: data[key]
        });
    }
    let wordcloudOption = {
        title: {
                    text: 'Related Tag WordCloud',
                    textStyle: {
                        fontStyle: 'oblique',
                        fontSize: 20,
                        color: '#4cc9f0'
                    },
                    left: 'center'
                },
                tooltip: {},
                series: [{
                    type: 'wordCloud',
                    shape: {
                      cloudGrow: 0.2
                    },
                    sizeRange: [10, 60],
                    rotationRange: [-30, 30],
                    gridSize: 2,
                    drawOutOfBound: false,
                    layoutAnimation: true,
                    keepAspect: true,
                    textStyle: {
                        fontWeight: 'bold',
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    },
                    emphasis: {
                        textStyle: {
                            shadowBlur: 15,
                            shadowColor: '#333'
                        }
                    },
                    data: wordcloudData.sort(function (a, b) {
                        return b.value - a.value;
                    })
                }]
    };
    wordcloud.setOption(wordcloudOption);
    return wordcloud;
}