// 1. yarn add jquery
// 2. public/index.html - 10个li
// 3. 入口处引入jquery
import $ from 'jquery'
// 4. 编写隔行变色的代码
$("#myUL>li:odd").css('color', 'pink')
$("#myUL>li:even").css('color', 'green')

// 5. 引入css文件
import "./css/index.css"

// 6. 引入less文件
import "./less/index.less"

// 7. 手动引入一个图片文件
// webpack眼里万物皆模块
import imgObj from './assets/1.gif'
let theImg = document.createElement("img")
theImg.src = imgObj
document.body.appendChild(theImg)

// 8. 引入字体图标样式文件
import "./assets/fonts/iconfont.css"
let theI = document.createElement("i")
theI.className = "iconfont icon-qq"
document.body.appendChild(theI)

// 9. 书写高版本的js语法
const fn = () => { console.log("我是一个箭头函数") }
console.log(fn);


// 问题: 每次修改代码, 重新 yarn build 打包, 才能看到最新的效果, 实际工作中, 打包 yarn build 非常费时 (30s - 60s) 
// 原因:
// ⚫ 从0构建依赖
// ⚫ 磁盘读取对应的文件到内存, webpack开始加载
// ⚫ 再用对应的 loader 进行处理
// ⚫ 将处理完的内容, 输出到磁盘指定目录
// // 解决: 起一个开发服务器, 缓存一些已经打包过的内容, 只重新打包修改的文件, 最终运行在内存中给浏览器使用


// 如何使用webpack开发服务器实时打包我们代码呢?
// 1. 确保下载了webpack-dev-server到工程中
// 2. 在package.json-配置自定义命令, 然后启动即可
// 3. webpack-dev-server会给我们一个地址+端口浏览器
// 访问即可看到在内存中打包的index.html页面