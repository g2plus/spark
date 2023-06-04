// 注意：在使用 require 加载用户自定义模块期间，
// 可以省略 .js 的后缀名(简化操作)
// 导包处理,类似于java里面的import
const m1 = require('./06.m1.js')
console.log(m1)
m1.sayHello();
console.log(m1.date);
