//导入自定义的格式化时间的模块
const myUtil = require('./15.dateFormat')
//调用方法进行时间的格式化
const dt = new Date()
//console.log(dt)
const newTime = myUtil.dateFormat(dt);
console.log(newTime);
console.log(typeof newTime);
