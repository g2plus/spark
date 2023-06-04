// 在外界使用 require 导入一个自定义模块的时候，得到的成员，
// 就是 那个模块中，通过 module.exports 指向的那个对象
const m = require('./11.自定义模块')
//时刻谨记，require() 模块时，得到的永远是 module.exports 指向的对象
console.log(m);
console.log(m.age);
console.log(m.username);