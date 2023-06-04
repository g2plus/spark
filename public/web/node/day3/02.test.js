const itheima = require('./cpc-util')
// 格式化时间的功能
const dtStr = itheima.dateFormat(new Date())
const htmlStr = '<h1 title="abc">这是h1标签<span>123&nbsp;</span></h1>'
const str = itheima.htmlEscape(htmlStr)
console.log(dtStr)
const str2 = itheima.htmlUnEscape(str)
console.log(str2)