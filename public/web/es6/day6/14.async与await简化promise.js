import thenFs from "then-fs"

//async/await 是 ES8（ECMAScript 2017）引入的新语法，用来简化 Promise 异步操作。在 async/await 出
//现之前，开发者只能通过链式 .then() 的方式处理 Promise 异步操作
//如果在 function 中使用了 await，则 function 必须被 async 修饰
//在 async 方法中，第一个 await 之前的代码会同步执行，await 之后的代码会异步执行
console.log("---------------");
async function getAllFiles() {
    console.log("Hello");
    var data1 = await thenFs.readFile("./files/1.txt", "utf8");
    console.log(data1);
    var data2 = await thenFs.readFile("./files/2.txt", "utf8");
    console.log(data2);
    var data3 = await thenFs.readFile("./files/3.txt", "utf8");
    console.log(data3);
}


getAllFiles(); //先执行主线的任务,执行内部方法,await回到主线程,执行任务,执行完继续执行子线程任务
console.log("world!");