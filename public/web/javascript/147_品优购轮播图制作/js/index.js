window.addEventListener('load', function() {

    //获取元素
    var arrow_l = document.querySelector('.arrow-l');

    alert(arrow_l);

    var arrow_r = document.querySelector('.arrow-r');

    alert(arrow_r);

    //图片外部的大盒子
    var focus = document.querySelector('.focus');

    var focusWidth = focus.offsetWidth;




    //鼠标进行focus，显示左右按钮
    focus.addEventListener('mouseenter', function() {
        arrow_l.style.display = 'block';
        arrow_r.style.display = 'block';
        //停止图片播放
        clearInterval(timer);
        timer = null; // 清除定时器变量
    });

    //鼠标离开focus，隐藏左右按钮
    focus.addEventListener('mouseleave', function() {
        arrow_l.style.display = 'none';
        arrow_r.style.display = 'none';
        timer = setInterval(function() {
            //手动调用点击事件
            arrow_r.click();
        }, 2000);
    });


    //动态生成小圆圈  有几张图片，我就生成几个小圆圈
    var ul = focus.querySelector('ul');

    var ol = focus.querySelector('.circle');

    console.log(ul.children.length);

    // 把ol里面的第一个小li设置类名为 current
    ol.children[0].className = 'current';

    // 克隆第一张图片(li)放到ul 最后面
    var first = ul.children[0].cloneNode(true);

    ul.appendChild(first);

    // 点击右侧按钮， 图片滚动一张
    var num = 0;

    // circle 控制小圆圈的播放
    var circle = 0;

    // flag 节流阀点击多次，显示一张图片有继续点击才播放下一张图片
    var flag = true;


    //创建小圆圈
    for (var i = 0; i < ul.children.length; i++) {

        // 创建一个小li
        var li = document.createElement('li');

        // 记录当前小圆圈的索引号 通过自定义属性来做
        li.setAttribute('index', i);

        // 把小li插入到ol 里面
        ol.appendChild(li);

        // 4. 小圆圈的排他思想 我们可以直接在生成小圆圈的同时直接绑定点击事件
        li.addEventListener('click', function() {
            // 干掉所有人 把所有的小li 清除 current 类名
            for (var i = 0; i < ol.children.length; i++) {
                ol.children[i].className = '';
            }
            // 留下我自己  当前的小li 设置current 类名
            this.className = 'current';
            // 5. 点击小圆圈，移动图片 当然移动的是 ul 
            // ul 的移动距离 小圆圈的索引号 乘以 图片的宽度 注意是负值
            // 当我们点击了某个小li 就拿到当前小li 的索引号
            var index = this.getAttribute('index');

            // 当我们点击了某个小li 就要把这个li 的索引号给 num
            num = index;

            // 当我们点击了某个小li 就要把这个li 的索引号给 circle  
            circle = index;

            // num = circle = index;
            console.log(focusWidth);
            console.log(index);

            animate(ul, -index * focusWidth); //移动的距离
        })
    }




    //左侧按钮做法
    arrow_l.addEventListener('click', function() {
        if (flag) {
            flag = false;
            if (num == 0) {
                num = ul.children.length - 1;
                ul.style.left = -num * focusWidth + 'px';

            }
            num--;
            animate(ul, -num * focusWidth, function() {
                flag = true;
            });
            // 点击左侧按钮，小圆圈跟随一起变化 可以再声明一个变量控制小圆圈的播放
            circle--;
            // 如果circle < 0  说明第一张图片，则小圆圈要改为第4个小圆圈（3）
            // if (circle < 0) {
            //     circle = ol.children.length - 1;
            // }
            circle = circle < 0 ? ol.children.length - 1 : circle;
            // 调用函数
            circleChange();
        }
    });


    //右侧按钮做法
    arrow_r.addEventListener('click', function() {

        if (flag) {
            flag = false; // 关闭节流阀

            // 如果走到了最后复制的一张图片，此时 我们的ul 要快速复原 left 改为 0
            if (num == ul.children.length - 1) {
                ul.style.left = 0;
                num = 0;
            }

            num++; //num=0 num++ 0 num 1

            animate(ul, -num * focusWidth, function() {
                flag = true; // 打开节流阀
            });

            // 8. 点击右侧按钮，小圆圈跟随一起变化 可以再声明一个变量控制小圆圈的播放
            circle++; //circl++ 0 circle 1

            // 如果circle == 4 说明走到最后我们克隆的这张图片了 我们就复原
            if (circle == ol.children.length) {

                //巧妙设计
                circle = 0; //图片显示最后一张，同第一张图片
            }

            // 调用函数
            circleChange();
        }
    });



    function circleChange() {
        // 排他思想

        // 先清除其余小圆圈的current类名
        for (var i = 0; i < ol.children.length; i++) {
            ol.children[i].className = '';
        }

        // 留下当前的小圆圈的current类名
        ol.children[circle].className = 'current';

    }

    // 自动播放轮播图 setInterval与setTimeOut的区别
    var timer = setInterval(function() {

        // 手动调用点击事件播放下一张图片 时间间隔2s
        arrow_r.click();

    }, 2000);

})