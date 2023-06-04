// 当前这个文件，就是一个用户自定义模块
let date = new Date();
let sayHello = function(){
    console.log("Say Hello!")
}
module.exports = {
    date: date,
    sayHello:sayHello,
}
