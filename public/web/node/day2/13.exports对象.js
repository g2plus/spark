exports.sayHello = function () {
    console.log('大家好！')
}
let sayHi = function () {
    console.log("Hello world!");
}
module.exports = {
    age: 20,
    username: "zs",
    sayHi:sayHi,
    sayHello:function(){
        console.log("你好!");
    }
}
//自动执行
console.log(exports === module.exports);