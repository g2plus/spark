// console.log(exports)
// console.log(module.exports)



const username = 'zs'

module.exports.username = username
exports.age = 20
exports.sayHello = function() {
    console.log('大家好！')
}

console.log(exports === module.exports) //true
    // 最终，向外共享的结果，永远都是 module.exports 所指向的对象