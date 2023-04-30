var img = document.getElementById('img');

var jpg = new Array(
    "image/taobao1.jpg",
    "image/taobao2.jpg",
    "image/taobao3.jpg",
    "image/taobao4.jpg",
    "image/taobao5.jpg")

var number = 0;
var temp = 0;

//设置小圆点的样式
function autoplay() {
    img.src = jpg[temp];
    var selector = document.getElementsByTagName("li");
    for (var i = 0; i < selector.length; i++) {
        if (temp == i) {
            selector[i].style.backgroundColor = "orange";
        } else {
            selector[i].style.backgroundColor = "white";
        }
    }
}

//无按键操作下的自动播放
function fun() {
    if (number >= 5) {
        number = 0;
    }
    temp = number++;
    autoplay();
}

//开启定时循环任务
var timer = setInterval(fun, 2000);

var timerBtn = document.getElementById('container');
timerBtn.onmouseover = function() {
    //停止计时
    clearInterval(timer);
}
timerBtn.onmouseout = function() {
    //开始计时
    timer = setInterval(fun, 2000);
}

//下一张图片
var next = document.getElementById("btn1");
next.onclick = function() {
    if (temp <= 4) {
        temp = ++temp;
        if (temp == 5) {
            temp = 0;
        }
        autoplay();
    }

}

//上一张图片
var prev = document.getElementById("btn0");
prev.onclick = function() {
    if (temp <= 4) {
        temp = --temp;
        if (temp == -1) {
            temp = 4;
        }
        autoplay();
    }

}