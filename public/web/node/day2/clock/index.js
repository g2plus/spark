window.onload = function () {
    //新年倒计时，每隔 1 秒执行 1 次
    setInterval(() => {
        var now = new Date()
        var end = new Date("2024-02-10 00:00:00").getTime();
        var seconds = parseInt((end - now.getTime()) / 1000);//两个时间点的时间差(秒)
        var d = parseInt(seconds / 3600 / 24);//得到天数
        var h = parseInt(seconds / 3600 % 24);//小时
        var m = parseInt(seconds / 60 % 60);//分钟
        var s = parseInt(seconds % 60);//秒
        // 为页面上的元素赋值
        document.querySelector("#DD").innerHTML = padZero(d)
        document.querySelector('#HH').innerHTML = padZero(h)
        document.querySelector('#mm').innerHTML = padZero(m)
        document.querySelector('#ss').innerHTML = padZero(s)
    }, 1000)
}

// 补零函数
function padZero(n) {
    return n > 9 ? n : '0' + n
}
  