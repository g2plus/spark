// 1. yarn add jquery
// 2. public/index.html - 10个li
// 3. 入口处引入jquery
import $ from 'jquery'
// 4. 编写隔行变色的代码 odd奇数
$("#app>li:odd").css('color', 'pink');
$("#app>li:even").css('color', 'red');